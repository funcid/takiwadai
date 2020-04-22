package entity.component;

import entity.component.execute.*;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Lang {

    RUBY("Ruby", new ScriptRunner("\"{$PATH}\" {$FILE}", "C:\\Program Files\\ruby\\bin\\ruby.exe")),
    PYTHON3("Python 3", new ScriptRunner("{$ABS_FILE}", "")),
    PHP("PHP", new ScriptRunner("\"{$PATH}\" {$FILE}", "C:\\Program Files\\php\\php.exe")),
    JAVA("Java 8", new JavaRunner()),
    PERL("Perl", new ScriptRunner("\"{$PATH}\" {$FILE}", "C:\\Perl64\\bin\\perl.exe")),;

    private String name;
    private Runner runner;
}
