package ru.func.takiwadai.controller;


import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;
import ru.func.takiwadai.entity.user.User;

@Controller
public class CalendarController {
    @GetMapping("/calendar")
    public ModelAndView calendar(@AuthenticationPrincipal User user) {
        ModelAndView modelAndView = new ModelAndView("calendar");
        modelAndView.addObject("auth", user != null);
        return modelAndView;
    }
}