package pro.appwork.open_university.model.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Singular;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.util.List;

import static javax.persistence.GenerationType.IDENTITY;

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

    @Singular
    @OneToMany(mappedBy = "group")
    private List<Student> students;
}
