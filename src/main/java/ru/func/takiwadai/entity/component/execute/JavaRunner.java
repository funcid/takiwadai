package ru.func.takiwadai.entity.component.execute;

import ru.func.takiwadai.entity.component.Component;

import java.io.*;
import java.util.List;

/**
 * @author func 17.04.2020
 * @project ru.func.takiwadai.Takiwadai
 */
public class JavaRunner implements Runner {

    private static final String JAVAC_PATH = "C:\\Program Files\\Java\\jdk1.8.0_241\\bin\\javac.exe";
    private static final String JAVA_PATH = "C:\\Program Files\\Java\\jdk1.8.0_241\\bin\\java.exe";

    @Override
    public boolean execute(Component component, List<String> requiredLines) {
        try {
            // Компилирование: .java -> .class
            String userComponentPath = PATH + "/" + component.getAuthor().getUsername();
            File file = new File(component.getPath());
            Process process = CMD.command("cmd.exe", "/c", "\"" + JAVAC_PATH + "\" " + file.getAbsolutePath())
                    .redirectError(new File(userComponentPath, "compile_error.txt"))
                    .start();

            try (BufferedReader reader = new BufferedReader(new InputStreamReader(process.getErrorStream()))) {
                component.setCrash(reader.readLine() == null);
            }

            component.setPath(component.getPath().replace(".java", ".class"));
            process.waitFor();

            // Запуск .class файла
            CMD.command("cmd.exe", "/c", "\"" + JAVA_PATH + "\" " + component.getName().split("\\.")[0])
                    .directory(new File(userComponentPath))
                    .redirectInput(new File(userComponentPath, "input.txt"))
                    .redirectOutput(new File(userComponentPath, "output.txt"))
                    .redirectError(new File(userComponentPath, "runtime_error.txt"))
                    .start()
                    .waitFor();

            // Удаление .class файла
            if (new File(component.getPath()).delete())
                component.setPath(file.getAbsolutePath());
            return checkCorrect(new File(userComponentPath, "output.txt"), requiredLines);
        } catch (Exception e) {
            return false;
        }
    }
}