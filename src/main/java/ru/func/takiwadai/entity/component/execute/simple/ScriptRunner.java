package ru.func.takiwadai.entity.component.execute.simple;

import ru.func.takiwadai.entity.component.Component;
import ru.func.takiwadai.entity.component.execute.Runner;
import lombok.AllArgsConstructor;
import lombok.Setter;

import java.io.File;

/**
 * @author func 19.04.2020
 * @project ru.func.takiwadai.Takiwadai
 */
@AllArgsConstructor
@Setter
public class ScriptRunner implements Runner {

    private String commandPattern;
    private String executorPath;

    @Override
    public Runner execute(Component component) throws Exception {
        CMD.command("cmd.exe", "/c", commandPattern
                .replace("{$PATH}", executorPath)
                .replace("{$ABS_FILE}", component.getFile().getAbsolutePath())
                .replace("{$FILE}", component.getFile().getName())
        ).directory(new File(PATH))
                .redirectInput(new File(PATH, "run/input.txt"))
                .redirectOutput(new File(PATH, "run/output.txt"))
                .redirectError(new File(PATH, "run/runtime_error.txt"))
                .start()
                .waitFor();
        return this;
    }
}
