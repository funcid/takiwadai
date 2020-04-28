package ru.func.takiwadai.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;
import ru.func.takiwadai.entity.component.Lang;
import ru.func.takiwadai.entity.task.Task;
import ru.func.takiwadai.repository.TaskRepository;

/**
 * @author func 27.04.2020
 * @project Takiwadai
 */
@Controller
public class TaskController {

    @Autowired
    public TaskRepository taskRepository;

    @GetMapping("/task/{task}")
    public ModelAndView task(@PathVariable Task task) {
        ModelAndView modelAndView = new ModelAndView("task");
        modelAndView.addObject("langs",
                task.getAccessibleLanguages().isEmpty() ?
                        Lang.values() :
                        task.getAccessibleLanguages()
        );
        modelAndView.addObject("title", task.getHeading());
        modelAndView.addObject("pp", task.getPp());
        modelAndView.addObject("author", task.getAuthor().getUsername());
        modelAndView.addObject("content", task.getContent());
        return modelAndView;
    }
}
