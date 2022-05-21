package pro.appwork.open_university.model.entity;

import lombok.*;
import lombok.experimental.SuperBuilder;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import pro.appwork.open_university.model.enums.UserRole;
import pro.appwork.open_university.model.enums.UserState;

import javax.persistence.*;
import javax.validation.constraints.Email;
import java.util.Set;

@Entity
@Getter
@SuperBuilder
@NoArgsConstructor
@Table(name = "t_user")
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class CustomUser {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column
    String firstName;

    @Column
    String lastName;

    @Column
    String middleName;

    @Email
    @Column(unique = true)
    String email;

    @Column
    String password;
    @Column
    @Enumerated(EnumType.STRING)
    UserRole role;

    @Column
    @Enumerated(EnumType.STRING)
    UserState state;

    public Set<SimpleGrantedAuthority> getAuthorities() {
        return Set.of(new SimpleGrantedAuthority(role.name()));
    }
}
