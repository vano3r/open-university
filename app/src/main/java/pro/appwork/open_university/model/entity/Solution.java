package pro.appwork.open_university.model.entity;

import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Getter
@SuperBuilder
@NoArgsConstructor
@Table(name = "t_solution")
public class Solution {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @Column
    private String filePath;

    @Column
    private Integer version;

    @ManyToOne(fetch = FetchType.LAZY)
    private TaskLesson task;

    @ManyToOne(fetch = FetchType.LAZY)
    private Student student;
}
