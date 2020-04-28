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
import org.springframework.web.servlet.view.RedirectView;
import ru.func.takiwadai.entity.component.Component;
import ru.func.takiwadai.entity.component.ComponentStatus;
import ru.func.takiwadai.entity.component.Lang;
import ru.func.takiwadai.entity.task.Task;
import ru.func.takiwadai.entity.task.test.TestUnit;
import ru.func.takiwadai.entity.user.User;
import ru.func.takiwadai.repository.ComponentRepository;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * @author func 27.04.2020
 * @project Takiwadai
 */
@Controller
public class TaskController {

    @Autowired
    private ComponentRepository componentRepository;

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
    public RedirectView loadSolve(
            @PathVariable Task task,
            @AuthenticationPrincipal User user,
            @RequestParam("file") MultipartFile file
    ) {
        // Создание решения
        Component component = componentRepository.save(Component.builder()
                .author(user)
                .testCount(task.getTests().size())
                .testPassed(0)
                .task(task)
                .crash(false)
                .status(ComponentStatus.PREPARE)
                .bootTimestamp(new Date().getTime())
                .build()
        );

        if (file != null && file.getOriginalFilename() != null && !file.getOriginalFilename().isEmpty()) {
            // Создание корневой папки, где будет тестирование, по формату ROOT/${USER_NAME}
            File root = new File(uploadPath + "/" + user.getUsername());

            // Содание каталога
            if (!root.exists())
                root.mkdirs();

            // Перебор всех разрешшенных языков
            List<Lang> langs = task.getAccessibleLanguages().isEmpty() ? Arrays.asList(Lang.values()) : task.getAccessibleLanguages();
            for (Lang currentLang : langs) {
                // Если расширение файла совпадает с расширением языка
                if (file.getOriginalFilename().split("\\.")[1].equals(currentLang.getExpansion())) {
                    try {
                        // Сохранения файла в ROOT/${USER_NAME}/
                        file.transferTo(new File(
                                uploadPath + "/" +
                                        user.getUsername() + "/" +
                                        file.getOriginalFilename()
                        ));
                        // Уточнение компонента
                        component.setPath(root.getAbsolutePath() + "/" + file.getOriginalFilename());
                        component.setLang(currentLang);
                        component.setName(file.getOriginalFilename());
                        component.setStatus(ComponentStatus.START);
                        // Сохранение программы
                        componentRepository.save(component);
                        // Перебор всех тестов задачи
                        for (TestUnit test : task.getTests()) {
                            // Создаение текстовых файлов
                            new File(root.getAbsolutePath() + "/compile_error.txt").createNewFile();
                            new File(root.getAbsolutePath() + "/runtime_error.txt").createNewFile();
                            File input = new File(root.getAbsolutePath() + "/input.txt");
                            // Заполнение input.txt, входными строками из теста
                            BufferedWriter writer = new BufferedWriter(new FileWriter(input));
                            for (String line : test.getInputLines()) {
                                writer.write(line);
                                writer.flush();
                            }
                            writer.close();
                            input.createNewFile();
                            new File(root.getAbsolutePath() + "/output.txt").createNewFile();
                            // Если программа выполниласи на ура, добавить решенную задачу
                            if (component.getLang().getRunner().execute(component, test.getRequiredOutput())) {
                                component.setTestPassed(component.getTestPassed() + 1);
                                component.setStatus(ComponentStatus.WORKING);

                            } else
                                component.setStatus(ComponentStatus.CRASHED);
                            // Обновление и сохранение статуса запуска
                            componentRepository.save(component);
                        }
                        if(!component.getCrash())
                            component.setStatus(ComponentStatus.DONE);
                    } catch (Exception e) {
                        component.setCrash(true);
                        component.setStatus(ComponentStatus.CRASHED);
                    }
                    // Сохранение программы
                    componentRepository.save(component);
                }
            }
        }
        return new RedirectView("/component/" + component.getId());
    }
}
