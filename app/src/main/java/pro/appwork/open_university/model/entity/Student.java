package pro.appwork.open_university.model.entity;

import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Getter
@SuperBuilder
@NoArgsConstructor
@Table(name = "t_student")
public class Student extends CustomUser {
    @ManyToOne
    @JoinColumn(name = "group_id")
    Group group;
}
