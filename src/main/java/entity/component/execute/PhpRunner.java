package entity.component.execute;

import entity.component.Component;

import java.io.File;

/**
 * @author func 19.04.2020
 * @project Takiwadai
 */
public class PhpRunner implements Runner {

    private static final String PHP_PATH = "C:\\Program Files\\php\\php.exe";

    @Override
    public Runner execute(Component component) throws Exception {
        cmd.command("cmd.exe", "/c", "\"" + PHP_PATH + "\" " + component.getFile().getName())
                .directory(new File(PATH))
                .redirectInput(new File(PATH, "input.txt"))
                .redirectOutput(new File(PATH, "output.txt"))
                .redirectError(new File(PATH, "runtime_error.txt"))
                .start()
                .waitFor();
        return this;
    }
}
