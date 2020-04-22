package entity.user;

import lombok.AllArgsConstructor;

/**
 * @author func 22.04.2020
 * @project Takiwadai
 */
@AllArgsConstructor
public enum UserPost {
    ADMIN("Глав. разработчик", "purple"),
    DEVELOPER("Разработчик", "pink"),
    TEACHER("Преподаватель", "orange"),
    STUDENT("Ученик/Студент", "yellow"),;

    private String description;
    private String colorTag;
}
