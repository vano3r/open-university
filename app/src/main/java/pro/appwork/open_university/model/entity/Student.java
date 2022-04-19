package pro.appwork.open_university.model.entity;

import lombok.*;

import javax.persistence.*;

import static javax.persistence.GenerationType.IDENTITY;


@Entity
@Getter
@Builder
@NoArgsConstructor
public class Student extends DefaultUser {
}
