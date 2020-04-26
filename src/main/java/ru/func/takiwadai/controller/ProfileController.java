package ru.func.takiwadai.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;
import ru.func.takiwadai.entity.user.User;
import ru.func.takiwadai.service.UserService;

/**
 * @author func 23.04.2020
 * @project Takiwadai
 */
@Controller
public class ProfileController {

    @Autowired
    private UserService userService;

    @GetMapping("/profile")
    public ModelAndView profilePage(@AuthenticationPrincipal User user) {
        if (user == null)
            return new ModelAndView("login");
        ModelAndView profile = new ModelAndView("profile");
        profile.addObject("username", user.getUsername());
        profile.addObject("role", user.getUserRole().getColorTag());
        profile.addObject("role_name", user.getUserRole().getDescription());
        profile.addObject("pp", user.getPerfectPoints() + "PP");
        profile.addObject("website", user.getWebsite());
        profile.addObject("location", user.getLocation());
        profile.addObject("place", "TOP#" + userService.getWorldPlace(user));
        return profile;
    }
}
