package ru.func.takiwadai.entity.component;

import lombok.*;
import ru.func.takiwadai.entity.task.Task;
import ru.func.takiwadai.entity.user.User;

import javax.persistence.*;
import java.io.File;

@Builder
@Getter
@Setter
@AllArgsConstructor
@Entity
@NoArgsConstructor
@Table(name = "component")
public class Component {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn
    private User author;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn
    private Task task;
    private Lang lang;
    private String fileName;
    private String content;
    private File file;
    private Boolean crash;
    private Long runtimeDuration;
    private Long memoryUsed;
    private Long bootTimestamp;
}
