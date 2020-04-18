package entity.component.execute;

import entity.component.Component;

@FunctionalInterface
public interface Runner {
    String PATH = "C:\\Users\\func\\Desktop\\Takiwadai\\src\\main\\resources";
    ProcessBuilder cmd = new ProcessBuilder();

    Runner execute(Component component) throws Exception;
}
