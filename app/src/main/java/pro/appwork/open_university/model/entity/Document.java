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
@Table(name = "t_document")
@SuperBuilder(toBuilder = true)
public class Document {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @Column
    private String name;

    @Column
    private String filePath;


    @Column
    @Builder.Default
    private LocalDateTime uploadTime = LocalDateTime.now();

    @ManyToOne
    @JoinColumn(name = "label_id")
    private DocumentLabel label;
}
