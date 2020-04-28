package ru.func.takiwadai.controller;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import ru.func.takiwadai.entity.user.User;
import ru.func.takiwadai.entity.user.UserRole;
import ru.func.takiwadai.repository.UserRepository;

import java.util.Date;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.stream.Stream;

/**
 * @author func 23.04.2020
 * @project Takiwadai
 */
@Controller
public class RegistrationController {
    @Autowired
    protected UserRepository userRepo;

    @GetMapping("/registration")
    public String registration() {
        return "registration";
    }

    @PostMapping("/registration")
    public ModelAndView addUser(User user) {
        ModelAndView modelAndView = new ModelAndView("registration");

        AtomicBoolean haveError = new AtomicBoolean(false);
        User finalUser = user;
        Stream.of(Checkers.values())
                .filter(error -> error.getChecker().check(finalUser, userRepo))
                .forEach(error -> {
                    haveError.set(true);
                            modelAndView.addObject(
                                    "message",
                                    "<div class=\"alert alert-danger h5\" role=\"alert\"><b>Ошибка регистрации!</b> " + error.getBadResponse() + ".</div>");
                        }
                );
        if (haveError.get())
            return modelAndView;

        user.setPerfectPoints(0L);
        user.setActivationCode("none");
        user.setActive(true);
        user.setRegistrationTimestamp(new Date().getTime());
        user.setUserRole(UserRole.STUDENT);

        userRepo.save(user);
        return new ModelAndView("login");
    }

    @FunctionalInterface
    interface CheckPassword {
        boolean check(User user, UserRepository userRepository);
    }

    @AllArgsConstructor
    @Getter
    enum Checkers {
        USER_EXISTS((user, userRepository) -> userRepository.findByUsername(user.getUsername()).isPresent(), "Данный логин занят"),
        ILLEGAL_SIZE((user, userRepository) -> user.getPassword().length() < 6 || user.getPassword().length() > 42, "Пароль должен быть длиннее 6 и короче 42 символов"),
        CONTAINS_SYMBOL((user, userRepository) -> user.getPassword().toUpperCase().equals(user.getPassword()), "Пароль должен содержать хотя бы один символ в нижнем регистре"),;

        private CheckPassword checker;
        private String badResponse;
    }
}