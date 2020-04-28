package ru.func.takiwadai.entity.component;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author func 28.04.2020
 * @project Takiwadai
 */
@AllArgsConstructor
@Getter
public enum ComponentStatus {

    PREPARE("Подготовка к запуску", ""),
    START("Компиляция/Запуск", ""),
    WORKING("Проверка решений", ""),
    CRASHED("Ошибка запуска", ""),
    DONE("Завершено успешно", ""),;

    private String status;
    private String color;
}
