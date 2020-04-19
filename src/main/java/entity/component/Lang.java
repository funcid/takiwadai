package entity.component;

import entity.component.execute.*;
import entity.component.execute.simple.PhpRunner;
import entity.component.execute.simple.Python3Runner;
import entity.component.execute.simple.RubyRunner;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Lang {

    RUBY("Ruby", new RubyRunner()),
    PYTHON3("Python 3", new Python3Runner()),
    PHP("PHP", new PhpRunner()),
    JAVA("Java 8", new JavaRunner()),;

    private String name;
    private Runner runner;
}
