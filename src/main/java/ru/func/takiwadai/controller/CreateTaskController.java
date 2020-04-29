package ru.func.takiwadai.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import ru.func.takiwadai.entity.task.Task;
import ru.func.takiwadai.entity.user.User;
import ru.func.takiwadai.entity.user.UserRole;
import ru.func.takiwadai.repository.TaskRepository;

import java.util.Date;

/**
 * @author func 29.04.2020
 * @project Takiwadai
 */
@Controller
@PreAuthorize("hasAnyAuthority('ADMIN', 'DEVELOPER', 'TEACHER')")
public class CreateTaskController {
    @Autowired
    private TaskRepository taskRepository;

    @GetMapping("/createTask")
    public ModelAndView show(@AuthenticationPrincipal User user) {
        if (user.getUserRole().equals(UserRole.STUDENT))
            return new ModelAndView("home");
        ModelAndView modelAndView = new ModelAndView("createTask");
        modelAndView.addObject("auth", user.getId() != null);
        return modelAndView;
    }
    @PostMapping("/createTask")
    public void create(
            @AuthenticationPrincipal User user,
            @RequestParam String title,
            @RequestParam String content
    ) {
        taskRepository.save(Task.builder()
                .author(user)
                .content(content)
                .heading(title)
                .createTimestamp(new Date().getTime())
                .build()
        );
    }
}
