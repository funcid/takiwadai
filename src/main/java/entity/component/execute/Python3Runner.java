package entity.component.execute;

import entity.component.Component;

import java.io.File;

/**
 * @author func 19.04.2020
 * @project Takiwadai
 */
public class Python3Runner implements Runner {
    @Override
    public Runner execute(Component component) throws Exception {
        cmd.command("cmd.exe", "/c", component.getFile().getAbsolutePath())
                .redirectInput(new File(PATH, "input.txt"))
                .redirectOutput(new File(PATH, "output.txt"))
                .redirectError(new File(PATH, "runtime_error.txt"))
                .start()
                .waitFor();
        return null;
    }
}
