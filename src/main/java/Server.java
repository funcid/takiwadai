import entity.component.Component;
import entity.component.Lang;

import java.io.File;

/**
 * @author func 17.04.2020
 * @project MetaTesting
 */
public class Server {
    public static void main(String[] args) throws Exception {
        Component component = Component.builder()
                .crash(false)
                .lang(Lang.JAVA)
                .id(1)
                .file(new File("C:\\Users\\func\\Desktop\\MetaTesting\\src\\main\\resources\\Test.java"))
                .build();
        Lang.JAVA.getAct().execute(component);
    }
}
