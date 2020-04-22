package ru.func.takiwadai.entity.task;

import ru.func.takiwadai.entity.component.Lang;
import ru.func.takiwadai.entity.user.User;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * @author func 22.04.2020
 * @project ru.func.takiwadai.Takiwadai
 */
@Builder
@Getter
@Setter
public class Task {

    private long id;
    private User author;
    private String heading;
    private String condition;
    private List<String> inputLines;
    private List<String> requiredOutput;
    private List<TaskTag> tags;
    private long memoryLimit;
    private long timeLimit;
    private long createTimestamp;
    private List<Lang> accessibleLanguages;
}
