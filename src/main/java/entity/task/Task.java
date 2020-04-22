package entity.task;

import entity.component.Lang;
import entity.user.User;

import java.util.List;

/**
 * @author func 22.04.2020
 * @project Takiwadai
 */
public class Task {

    private long id;
    private User author;
    private String heading;
    private String condition;
    private String input;
    private String requiredOutput;
    private long memoryLimit;
    private long timeLimit;
    private long createTimestamp;
    private List<Lang> accessibleLanguages;
}
