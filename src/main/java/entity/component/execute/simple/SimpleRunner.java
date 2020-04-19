package entity.component.execute.simple;

import entity.component.Component;
import entity.component.execute.Runner;
import lombok.AllArgsConstructor;
import lombok.Setter;

import java.io.File;

/**
 * @author func 19.04.2020
 * @project Takiwadai
 */
@AllArgsConstructor
@Setter
public class SimpleRunner implements Runner {

    private String commandFormat;
    private String langPath;

    @Override
    public Runner execute(Component component) throws Exception {
        cmd.command("cmd.exe", "/c", commandFormat
                .replace("{$PATH}", langPath)
                .replace("{$ABS_FILE}", component.getFile().getAbsolutePath())
                .replace("{$FILE}", component.getFile().getName())
        ).directory(new File(PATH))
                .redirectInput(new File(PATH, "input.txt"))
                .redirectOutput(new File(PATH, "output.txt"))
                .redirectError(new File(PATH, "runtime_error.txt"))
                .start()
                .waitFor();
        return this;
    }
}
