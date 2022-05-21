package pro.appwork.open_university.model.entity;

import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;

@Entity
@Getter
@SuperBuilder(toBuilder = true)
@NoArgsConstructor
@Table(name = "t_student")
public class Student extends CustomUser {
    @ManyToOne
    @JoinColumn(name = "group_id")
    Group group;
}
