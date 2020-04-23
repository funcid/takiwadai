package ru.func.takiwadai.entity.component.execute;

import ru.func.takiwadai.entity.component.Component;

import java.io.*;

/**
 * @author func 17.04.2020
 * @project ru.func.takiwadai.Takiwadai
 */
public class JavaRunner implements Runner {

    private static final String JAVAC_PATH = "C:\\Program Files\\Java\\jdk1.8.0_241\\bin\\javac.exe";
    private static final String JAVA_PATH = "C:\\Program Files\\Java\\jdk1.8.0_241\\bin\\java.exe";

    @Override
    public Runner execute(Component component) throws Exception {

        // Компилирование: .java -> .class
        File file = new File(component.getFile().getAbsolutePath());
        Process process = CMD.command("cmd.exe", "/c", "\"" + JAVAC_PATH + "\" " + file)
                .redirectError(new File(PATH, "run/compile_error.txt"))
                .start();

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(process.getErrorStream()))) {
            component.setCrash(reader.readLine() == null);
        }

        component.setFile(new File(component.getFile().getAbsolutePath().replace(".java", ".class")));
        process.waitFor();

        // Запуск .class файла
        CMD.command("cmd.exe", "/c", "\"" + JAVA_PATH + "\" " + component.getFile().getName().split("\\.")[0])
                .directory(new File(PATH))
                .redirectInput(new File(PATH, "run/input.txt"))
                .redirectOutput(new File(PATH, "run/output.txt"))
                .redirectError(new File(PATH, "run/runtime_error.txt"))
                .start()
                .waitFor();

        // Удаление .class файла
        if (component.getFile().delete())
            component.setFile(file);
        return this;
    }
}