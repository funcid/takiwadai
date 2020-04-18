package entity.component.execute;

import entity.component.Component;

import java.io.*;

/**
 * @author func 17.04.2020
 * @project Takiwadai
 */
public class JavaRunner implements Runner {

    private static final String JAVAC_PATH = "C:\\Program Files\\Java\\jdk1.8.0_241\\bin\\javac.exe";
    private static final String JAVA_PATH = "C:\\Program Files\\Java\\jdk1.8.0_241\\bin\\java.exe";

    @Override
    public Runner execute(Component component) throws Exception {
        Process process = cmd.command("cmd.exe", "/c", "\"" + JAVAC_PATH + "\" " + component.getFile().getAbsolutePath())
                .redirectError(new File(PATH, "compile_error.txt"))
                .start();

        StringBuilder stringBuilder = new StringBuilder("Ошибка! ");

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(process.getErrorStream()))) {
            String line;
            while ((line = reader.readLine()) != null) {
                stringBuilder.append(line).append("\n");
            }
            if (stringBuilder.length() > 8) {
                component.setCrash(true);
                return this;
            }
        }

        component.setFile(new File(component.getFile().getAbsolutePath().replace(".java", ".class")));
        process.waitFor();

        cmd.command("cmd.exe", "/c", "\"" + JAVA_PATH + "\" " + component.getFile().getName().split("\\.")[0])
                .directory(new File(PATH))
                .redirectInput(new File(PATH, "input.txt"))
                .redirectOutput(new File(PATH, "output.txt"))
                .redirectError(new File(PATH, "runtime_error.txt"))
                .start()
                .waitFor();
        return this;
    }
}