package pro.appwork.open_university.model.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Singular;
import lombok.experimental.SuperBuilder;
import pro.appwork.open_university.model.enums.Semester;

import javax.persistence.*;
import java.util.List;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Getter
@SuperBuilder
@NoArgsConstructor
@Table(name = "t_lesson")
public class Lesson {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @Column
    private String name;

    @Column
    @Enumerated
    private Semester semester;

    @ManyToOne
    private Teacher teacher;

    @ManyToOne
    private Group group;

    @Singular
    @OneToMany(
            mappedBy = "task",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<TaskLesson> tasks;
}
