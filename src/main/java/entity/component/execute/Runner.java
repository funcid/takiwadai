package entity.component.execute;

import entity.component.Component;

@FunctionalInterface
public interface Runner {
    ProcessBuilder cmd = new ProcessBuilder();

    Runner execute(Component component) throws Exception;
}
