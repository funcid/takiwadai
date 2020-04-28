package ru.func.takiwadai.entity.component.execute;

import ru.func.takiwadai.entity.component.Component;

@FunctionalInterface
public interface Runner {
    String PATH = "C:/Users/func/Desktop/component/uploads";
    ProcessBuilder CMD = new ProcessBuilder();

    Runner execute(Component component) throws Exception;
}
