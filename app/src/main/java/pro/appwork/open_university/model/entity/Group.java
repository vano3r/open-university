package pro.appwork.open_university.model.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Accessors(fluent = true)
@Table(name = "t_group")
public class Group {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    Long id;
    String name;
    Integer stage;

    @OneToMany
    @JoinColumn(name = "group_id")
    List<Subject> subjects = new ArrayList<>();

    @OneToMany
    @JoinColumn(name = "group_id")
    List<CustomUser> students = new ArrayList<>();
}
