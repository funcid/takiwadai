package entity.component;

import entity.component.execute.Python3Runner;
import entity.component.execute.Runner;
import entity.component.execute.JavaRunner;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Lang {

    PYTHON3("Python 3", new Python3Runner()),
    JAVA("Java 8", new JavaRunner()),;

    private String name;
    private Runner runner;
}
