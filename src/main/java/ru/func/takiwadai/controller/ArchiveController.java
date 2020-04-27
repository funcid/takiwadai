package ru.func.takiwadai.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;
import ru.func.takiwadai.entity.user.User;
import ru.func.takiwadai.repository.TaskRepository;


@Controller
public class ArchiveController {

    @Autowired
    private TaskRepository taskRepository;

    @GetMapping("/archive")
    public ModelAndView archive(@AuthenticationPrincipal User user) {
        ModelAndView modelAndView = new ModelAndView("archive");
        modelAndView.addObject("tasks", taskRepository.findAll());
        modelAndView.addObject("auth", user != null);
        return modelAndView;
    }
}
