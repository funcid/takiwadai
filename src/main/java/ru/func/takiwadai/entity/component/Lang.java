package ru.func.takiwadai.entity.component;

import ru.func.takiwadai.entity.component.execute.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import ru.func.takiwadai.entity.component.execute.ScriptRunner;

@Getter
@AllArgsConstructor
public enum Lang {

    RUBY("Ruby", ".rb", "badge-danger",new ScriptRunner("\"{$PATH}\" {$FILE}", "C:\\Program Files\\ruby\\bin\\ruby.exe")),
    PYTHON3("Python 3", ".py", "badge-info", new ScriptRunner("{$ABS_FILE}", "")),
    PHP("PHP", ".php","badge-secondary", new ScriptRunner("\"{$PATH}\" {$FILE}", "C:\\Program Files\\php\\php.exe")),
    JAVA("Java 8", ".java","badge-warning", new JavaRunner()),;
  //  PEARL ("Pearl", new  ScriptRunner());

    private String name;
    private String expansion;
    private String pillColor;
    private Runner runner;
}
