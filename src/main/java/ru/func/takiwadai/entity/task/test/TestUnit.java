package ru.func.takiwadai.entity.task.test;

import lombok.*;

import javax.persistence.*;
import java.util.List;

/**
 * @author func 28.04.2020
 * @project Takiwadai
 */
@Builder
@Getter
@Setter
@AllArgsConstructor
@Entity
@NoArgsConstructor
@Table(name = "test")
public class TestUnit {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @ElementCollection
    private List<String> inputLines;
    @ElementCollection
    private List<String> requiredOutput;
}
