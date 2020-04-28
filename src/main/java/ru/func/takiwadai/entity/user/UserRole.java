package ru.func.takiwadai.entity.user;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;

/**
 * @author func 22.04.2020
 * @project ru.func.takiwadai.Takiwadai
 */
@Getter
@AllArgsConstructor
public enum UserRole implements GrantedAuthority {
    ADMIN("Глав. разработчик", "badge badge-pill badge-danger"),
    DEVELOPER("Разработчик", "badge badge-pill badge-light"),
    TEACHER("Преподаватель", "badge badge-pill badge-warning"),
    STUDENT("Ученик/Студент", "badge badge-pill badge-secondary"),;

    private String description;
    private String colorTag;

    @Override
    public String getAuthority() {
        return name();
    }
}
