package entity.component.execute.simple;

/**
 * @author func 19.04.2020
 * @project Takiwadai
 */
public class RubyRunner extends SimpleRunner {

    public RubyRunner() {
        super("\"{$PATH}\" {$FILE}", "C:\\Program Files\\ruby\\bin\\ruby.exe");
    }
}
