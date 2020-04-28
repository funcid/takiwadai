package ru.func.takiwadai.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import ru.func.takiwadai.entity.component.Component;
import ru.func.takiwadai.entity.component.Lang;
import ru.func.takiwadai.entity.task.Task;
import ru.func.takiwadai.entity.user.User;
import ru.func.takiwadai.repository.TaskRepository;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;
import java.util.stream.Stream;

/**
 * @author func 27.04.2020
 * @project Takiwadai
 */
@Controller
public class TaskController {

    @Autowired
    private TaskRepository taskRepository;

    @Value("${upload.path}")
    private String uploadPath;

    @GetMapping("/task/{task}")
    public ModelAndView task(
            @PathVariable Task task,
            @AuthenticationPrincipal User user
    ) {
        ModelAndView modelAndView = new ModelAndView("task");
        modelAndView.addObject("auth", user == null);
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

    @PostMapping("/task/{task}")
    public String loadSolve(
            @PathVariable Task task,
            @AuthenticationPrincipal User user,
            @RequestParam("file") MultipartFile file
    ) {
        if (file != null && file.getOriginalFilename() != null && !file.getOriginalFilename().isEmpty()) {
            File root = new File(uploadPath + "/" + user.getUsername());

            if (!root.exists())
                root.mkdirs();

            Stream.of(Lang.values())
                    .filter(lang -> file.getOriginalFilename().split("\\.")[1].equals(lang.getExpansion()))
                    .forEach(lang -> {
                        try {
                            new File(root.getAbsolutePath() + "/compile_error.txt").createNewFile();
                            new File(root.getAbsolutePath() + "/runtime_error.txt").createNewFile();
                            File input = new File(root.getAbsolutePath() + "/input.txt");
                            BufferedWriter writer = new BufferedWriter(new FileWriter(input));
                            task.getInputLines().forEach(line -> {
                                try {
                                    writer.write(line);
                                    writer.flush();
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                            });
                            writer.close();
                            input.createNewFile();
                            new File(root.getAbsolutePath() + "/output.txt").createNewFile();
                            file.transferTo(new File(
                                    uploadPath + "/" +
                                            user.getUsername() + "/" +
                                            file.getOriginalFilename()
                            ));
                            Component component = Component.builder()
                                    .author(user)
                                    .crash(false)
                                    .path(root.getAbsolutePath() + "/" + file.getOriginalFilename())
                                    .lang(lang)
                                    .name(file.getOriginalFilename())
                                    .task(task)
                                    .bootTimestamp(new Date().getTime())
                                    .build();
                            component.getLang().getRunner().execute(component);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    });
        }
        return "archive";
    }
}
