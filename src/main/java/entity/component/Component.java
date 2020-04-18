package entity.component;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.io.File;

@Builder
@Getter
@Setter
public class Component {

    private long id;
    private Lang lang;
    private File file;
    private boolean crash;
}
