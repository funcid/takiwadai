package ru.func.takiwadai.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;
import ru.func.takiwadai.entity.user.User;

/**
 * @author func 23.04.2020
 * @project Takiwadai
 */
@Controller
public class ProfileController {

    @GetMapping("/profile")
    public ModelAndView profilePage(@AuthenticationPrincipal User user) {
        ModelAndView profile = new ModelAndView("profile");
        profile.addObject("username", user.getUsername());
        return profile;
    }
}
