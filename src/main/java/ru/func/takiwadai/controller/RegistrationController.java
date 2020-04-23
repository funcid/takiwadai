package ru.func.takiwadai.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import ru.func.takiwadai.entity.user.User;
import ru.func.takiwadai.entity.user.UserRole;
import ru.func.takiwadai.repository.UserRepository;

import java.util.Date;
import java.util.Optional;

/**
 * @author func 23.04.2020
 * @project Takiwadai
 */
@Controller
public class RegistrationController {
    @Autowired
    private UserRepository userRepo;

    @GetMapping("/registration")
    public String registration() {
        return "registration";
    }

    @PostMapping("/registration")
    public ModelAndView addUser(@RequestParam String username,
                                @RequestParam String password) {
        ModelAndView modelAndView = new ModelAndView("registration");
        Optional<User> userFromDb = userRepo.findByUsername(username);

        if (!userFromDb.isPresent()) {
            modelAndView.addObject("message", "Имя занято!");
            return modelAndView;
        }

        userRepo.save(User.builder()
                .password(password)
                .email("none")
                .activationCode("none")
                .active(true)
                .registrationTimestamp(new Date().getTime())
                .username(username)
                .userRole(UserRole.ADMIN)
                .build()
        );
        return new ModelAndView("login");
    }
}