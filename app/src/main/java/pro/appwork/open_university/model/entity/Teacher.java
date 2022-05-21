package pro.appwork.open_university.model.entity;

import lombok.*;
import lombok.experimental.SuperBuilder;
import pro.appwork.open_university.model.enums.UserRole;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Getter
@SuperBuilder
@NoArgsConstructor
@Table(name = "t_teacher")
public class Teacher extends CustomUser {
}
