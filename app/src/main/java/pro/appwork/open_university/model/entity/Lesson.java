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
@NoArgsConstructor
@Table(name = "t_lesson")
@SuperBuilder(toBuilder = true)
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
    @OneToMany
    @JoinColumn(name = "lesson_id")
    private List<Task> tasks;
}
