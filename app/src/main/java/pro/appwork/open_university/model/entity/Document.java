package pro.appwork.open_university.model.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import pro.appwork.open_university.model.enums.DocumentTypeEnum;

import javax.persistence.*;
import java.io.File;
import java.time.LocalDateTime;

import static javax.persistence.EnumType.STRING;
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
    @Enumerated(STRING)
    private DocumentTypeEnum type;

    @Column
    @Builder.Default
    private LocalDateTime uploadTime = LocalDateTime.now();

    @ManyToOne
    @JoinColumn(name = "teacher_id")
    private Teacher teacher;

    public File getFile() {
        return new File(filePath);
    }
}
