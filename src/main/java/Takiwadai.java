import entity.component.Component;
import entity.component.Lang;

import java.io.File;

/**
 * @author func 17.04.2020
 * @project Takiwadai
 */
public class Takiwadai {
    public static void main(String[] args) throws Exception {
        Component component = Component.builder()
                .crash(false)
                .lang(Lang.PERL)
                .id(1)
                .file(new File("C:\\Projects\\Takiwadai\\src\\main\\resources\\main.pl"))
                .build();
        Lang.PHP.getRunner().execute(component);

    }
}
