package ru.func.takiwadai.entity.component.execute;

import lombok.AllArgsConstructor;
import lombok.Setter;
import ru.func.takiwadai.entity.component.Component;

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
        String userComponentPath = PATH + "/" + component.getAuthor().getUsername();
        CMD.command("cmd.exe", "/c", commandPattern
                .replace("{$PATH}", executorPath)
                .replace("{$ABS_FILE}", component.getPath())
                .replace("{$FILE}", component.getName())
        ).directory(new File(userComponentPath))
                .redirectInput(new File(userComponentPath, "run/input.txt"))
                .redirectOutput(new File(userComponentPath, "run/output.txt"))
                .redirectError(new File(userComponentPath, "run/runtime_error.txt"))
                .start()
                .waitFor();
        return this;
    }
}
