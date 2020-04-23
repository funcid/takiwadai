package ru.func.takiwadai.entity.user;

import lombok.AllArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

/**
 * @author func 22.04.2020
 * @project ru.func.takiwadai.Takiwadai
 */
@AllArgsConstructor
public enum UserRole implements GrantedAuthority {
    ADMIN("Глав. разработчик", "purple"),
    DEVELOPER("Разработчик", "pink"),
    TEACHER("Преподаватель", "orange"),
    STUDENT("Ученик/Студент", "yellow"),;

    private String description;
    private String colorTag;

    @Override
    public String getAuthority() {
        return name();
    }
}
