package pro.appwork.open_university.model.entity;

import lombok.*;
import org.springframework.stereotype.Controller;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "t_task")
public class Task {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;
    @Column
    private String name;
    @Column
    private String filePath;
//
//    @OneToMany
//    @JoinColumn(name = "task_id")
//    private List<Solution> solutions = new ArrayList<>();
}
