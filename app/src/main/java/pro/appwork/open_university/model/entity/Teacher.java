package pro.appwork.open_university.model.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Singular;
import lombok.experimental.SuperBuilder;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;

@Entity
@Getter
@SuperBuilder(toBuilder = true)
@NoArgsConstructor
@Table(name = "t_teacher")
public class Teacher extends CustomUser {
    @Singular
    @OneToMany(mappedBy = "teacher")
    List<Document> documents;
}
