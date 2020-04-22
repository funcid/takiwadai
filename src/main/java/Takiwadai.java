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
                .lang(Lang.PHP)
                .id(1)
                .file(new File("C:\\Users\\func\\Desktop\\Takiwadai\\src\\main\\resources\\index.php"))
                .build();
        Lang.PHP.getRunner().execute(component);
    }
}
