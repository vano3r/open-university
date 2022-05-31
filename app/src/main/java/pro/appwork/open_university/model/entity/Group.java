package pro.appwork.open_university.model.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Singular;
import lombok.experimental.SuperBuilder;
import pro.appwork.open_university.model.enums.Semester;

import javax.persistence.*;
import java.util.List;

import static javax.persistence.GenerationType.IDENTITY;
import static pro.appwork.open_university.model.enums.Semester.FIRST;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "t_group")
@SuperBuilder(toBuilder = true)
public class Group {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @Column
    private String name;

    @Column
    @Builder.Default
    private Semester actualSemester = FIRST;

    @Singular
    @OneToMany(mappedBy = "group")
    private List<Student> students;
}
