package pro.appwork.open_university.model.entity;

import lombok.*;
import lombok.experimental.Accessors;
import lombok.experimental.FieldDefaults;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import pro.appwork.open_university.model.enums.UserRole;
import pro.appwork.open_university.model.enums.UserState;

import javax.persistence.*;
import javax.validation.constraints.Email;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static javax.persistence.EnumType.STRING;
import static javax.persistence.GenerationType.IDENTITY;
import static lombok.AccessLevel.PRIVATE;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "t_user")
@Accessors(fluent = true)
@FieldDefaults(level = PRIVATE)
@ToString(onlyExplicitlyIncluded = true)
public class CustomUser {
    @Id
    @ToString.Include
    @Column(nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @ToString.Include
    String firstName;
    @ToString.Include
    String lastName;
    @ToString.Include
    String middleName;
    @Email
    @ToString.Include
    @Column(unique = true)
    String email;
    String password;

    @ToString.Include
    @Enumerated(EnumType.STRING)
    UserRole role;
    @ToString.Include
    @Enumerated(EnumType.STRING)
    UserState state;

    @ManyToOne
    Group group;

    @OneToMany
    @JoinColumn(name = "teacher_id")
    List<Subject> subjects = new ArrayList<>();

    @OneToMany
    @JoinColumn(name = "student_id")
    List<Solution> solutions = new ArrayList<>();

    public Set<SimpleGrantedAuthority> getAuthorities() {
        return Set.of(new SimpleGrantedAuthority(role.name()));
    }
}
