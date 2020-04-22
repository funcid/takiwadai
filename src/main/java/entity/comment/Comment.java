package entity.comment;

import entity.task.Task;
import entity.user.User;

/**
 * @author func 22.04.2020
 * @project Takiwadai
 */
public class Comment {

    private User author;
    private Task task;
    private int id;
    private String content;
    private long timestamp;
}
