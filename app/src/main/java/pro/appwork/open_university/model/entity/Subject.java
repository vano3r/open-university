package pro.appwork.open_university.model.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "t_subject")
public class Subject {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    Long id;
    String name;

    @OneToMany
    @JoinColumn(name = "subject_id")
    List<Task> tasks = new ArrayList<>();
}
