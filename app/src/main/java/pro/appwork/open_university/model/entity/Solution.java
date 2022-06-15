package pro.appwork.open_university.model.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.time.LocalDateTime;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "t_solution")
@SuperBuilder(toBuilder = true)
public class Solution {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @Column
    private String filePath;

    @Column
    private String fileName;

    @Column
    @Builder.Default
    private LocalDateTime uploadDate = LocalDateTime.now();

    @ManyToOne(fetch = FetchType.LAZY)
    private Task task;

    @ManyToOne(fetch = FetchType.LAZY)
    private Student student;
}
