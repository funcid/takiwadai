import entity.component.Component;
import entity.component.Lang;

import java.io.File;

/**
 * @author func 17.04.2020
 * @project Takiwadai
 */
public class Server {
    public static void main(String[] args) throws Exception {
        Component component = Component.builder()
                .crash(false)
                .lang(Lang.PYTHON3)
                .id(1)
                .file(new File("C:\\Users\\func\\Desktop\\Takiwadai\\src\\main\\resources\\main.py"))
                .build();
        Lang.PYTHON3.getRunner().execute(component);
    }
}
