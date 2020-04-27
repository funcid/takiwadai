package ru.func.takiwadai.entity.task;

import lombok.*;
import ru.func.takiwadai.entity.component.Lang;
import ru.func.takiwadai.entity.user.User;

import javax.persistence.*;
import java.util.List;

/**
 * @author func 22.04.2020
 * @project ru.func.takiwadai.Takiwadai
 */
@Builder
@Getter
@Setter
@AllArgsConstructor
@Entity
@NoArgsConstructor
@Table(name = "task")
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User author;
    private String heading;
    private String content;
    @ElementCollection
    private List<String> inputLines;
    @ElementCollection
    private List<String> requiredOutput;
    @ElementCollection
    private List<TaskTag> tags;
    private Long memoryLimit;
    private Long timeLimit;
    private Long createTimestamp;
    @ElementCollection
    private List<Lang> accessibleLanguages;
    private Long pp;
}
