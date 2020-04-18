package entity.component.execute;

import entity.component.Component;

import java.io.File;

/**
 * @author func 19.04.2020
 * @project Takiwadai
 */
public class RubyRunner implements Runner {

    private static final String RUBY_PATH = "C:\\Program Files\\ruby\\bin\\ruby.exe";

    @Override
    public Runner execute(Component component) throws Exception {
        cmd.command("cmd.exe", "/c", "\"" + RUBY_PATH + "\" " + component.getFile().getName())
                .directory(new File(PATH))
                .redirectInput(new File(PATH, "input.txt"))
                .redirectOutput(new File(PATH, "output.txt"))
                .redirectError(new File(PATH, "runtime_error.txt"))
                .start()
                .waitFor();
        return this;
    }
}
