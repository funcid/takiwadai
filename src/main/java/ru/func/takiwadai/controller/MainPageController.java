package ru.func.takiwadai.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author func 22.04.2020
 * @project Takiwadai
 */
@Controller
public class MainPageController {

    @RequestMapping("/")
    public String home() {
        return "home";
    }
}
