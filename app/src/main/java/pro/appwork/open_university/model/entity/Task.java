package pro.appwork.open_university.model.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "t_task")
@SuperBuilder(toBuilder = true)
public class Task {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String filePath;

    @Column
    private String fileName;

    @Column
    @Builder.Default
    private LocalDateTime uploadDate = LocalDateTime.now();

    @ManyToOne
    private TaskType type;

    @ManyToOne
    private Lesson lesson;
}
