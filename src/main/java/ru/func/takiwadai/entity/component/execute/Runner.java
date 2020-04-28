package ru.func.takiwadai.entity.component.execute;

import ru.func.takiwadai.entity.component.Component;

import java.io.File;
import java.util.List;
import java.util.Scanner;

public interface Runner {
    // Изменить как в application.properties
    String PATH = "C:/Users/func/Desktop/component/uploads";
    ProcessBuilder CMD = new ProcessBuilder();

    boolean execute(Component component, List<String> requiredLines);

    default boolean checkCorrect(File output, List<String> lines) {
        try {
            Scanner scanner = new Scanner(output);
            boolean incorrent = false;
            int counter = 0;
            while (scanner.hasNextLine() && !incorrent) {
                incorrent = !lines.get(counter).equals(scanner.nextLine());
                counter++;
            }
            scanner.close();
            return !incorrent;
        } catch (Exception e) {
            return false;
        }
    }
}
