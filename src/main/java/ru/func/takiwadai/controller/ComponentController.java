package ru.func.takiwadai.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;
import ru.func.takiwadai.entity.component.Component;
import ru.func.takiwadai.entity.user.User;

/**
 * @author func 28.04.2020
 * @project Takiwadai
 */
@Controller
public class ComponentController {
    @GetMapping("/component/{component}")
    public ModelAndView component(
            @PathVariable Component component,
            @AuthenticationPrincipal User user
    ) {
        ModelAndView modelAndView = new ModelAndView("component");
        modelAndView.addObject("access", user.equals(component.getAuthor()));
        modelAndView.addObject("statusName", component.getStatus().getStatus());
        modelAndView.addObject("passed", component.getTestPassed());
        modelAndView.addObject("count", component.getTestCount());
        modelAndView.addObject("crashed", component.getCrash());
        return modelAndView;
    }
}
