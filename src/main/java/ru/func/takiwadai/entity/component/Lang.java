package ru.func.takiwadai.entity.component;

import ru.func.takiwadai.entity.component.execute.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import ru.func.takiwadai.entity.component.execute.ScriptRunner;

@Getter
@AllArgsConstructor
public enum Lang {

    RUBY("Ruby", new ScriptRunner("\"{$PATH}\" {$FILE}", "C:\\Program Files\\ruby\\bin\\ruby.exe")),
    PYTHON3("Python 3", new ScriptRunner("{$ABS_FILE}", "")),
    PHP("PHP", new ScriptRunner("\"{$PATH}\" {$FILE}", "C:\\Program Files\\php\\php.exe")),
    JAVA("Java 8", new JavaRunner()),;
  //  PEARL ("Pearl", new  ScriptRunner());

    private String name;
    private Runner runner;
}
