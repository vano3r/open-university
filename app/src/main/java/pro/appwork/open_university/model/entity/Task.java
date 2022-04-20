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
@Table(name = "t_task")
public class Task {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    Long id;
    String name;
    String filePath;

    @OneToMany
    @JoinColumn(name = "task_id")
    List<Solution> solutions = new ArrayList<>();
}
