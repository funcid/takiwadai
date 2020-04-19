package entity.component.execute.simple;

/**
 * @author func 19.04.2020
 * @project Takiwadai
 */
public class PhpRunner extends SimpleRunner {

    public PhpRunner() {
        super("\"{$PATH}\" {$FILE}", "C:\\Program Files\\php\\php.exe");
    }
}
