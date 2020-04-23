package ru.func.takiwadai.entity.comment;

import ru.func.takiwadai.entity.task.Task;
import ru.func.takiwadai.entity.user.User;

/**
 * @author func 22.04.2020
 * @project ru.func.takiwadai.Takiwadai
 */
public class Comment {

    private User author;
    private Task task;
    private int id;
    private String content;
    private long timestamp;
}
