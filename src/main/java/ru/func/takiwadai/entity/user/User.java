package ru.func.takiwadai.entity.user;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

/**
 * @author func 18.04.2020
 * @project ru.func.takiwadai.Takiwadai
 */
@Builder
@Getter
@Setter
public class User {

    private String login;
    private String password;
    private String email;
    private boolean activated;
    private UserPost userPost;
    private String activationCode;
    private long registrationTimestamp;
}
