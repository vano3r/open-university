package pro.appwork.open_university.model.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import pro.appwork.open_university.model.enums.RoleEnum;

import javax.persistence.*;

import static javax.persistence.EnumType.STRING;
import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "t_role")
@SuperBuilder(toBuilder = true)
public class Role {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @Column
    @Enumerated(STRING)
    private RoleEnum name;
}
