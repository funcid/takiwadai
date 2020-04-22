package ru.func.takiwadai.entity.component;

import ru.func.takiwadai.entity.task.Task;
import ru.func.takiwadai.entity.user.User;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.io.File;

@Builder
@Getter
@Setter
public class Component {

    private User author;
    private Task task;
    private long id;
    private Lang lang;
    private String fileName;
    private String content;
    private File file;
    private boolean crash;
    private long runtimeDuration;
    private long memoryUsed;
    private long bootTimestamp;
}
