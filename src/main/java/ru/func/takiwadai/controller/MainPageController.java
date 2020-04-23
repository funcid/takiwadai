package ru.func.takiwadai.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import ru.func.takiwadai.entity.user.User;

/**
 * @author func 22.04.2020
 * @project Takiwadai
 */
@Controller
public class MainPageController {

    @RequestMapping("/")
    public ModelAndView home(@AuthenticationPrincipal User user) {
        ModelAndView modelAndView = new ModelAndView("home");
        modelAndView.addObject("auth", user != null);
        return modelAndView;
    }
}
