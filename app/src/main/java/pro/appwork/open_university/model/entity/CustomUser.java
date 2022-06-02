package pro.appwork.open_university.model.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Singular;
import lombok.experimental.SuperBuilder;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import pro.appwork.open_university.model.enums.RoleEnum;
import pro.appwork.open_university.model.enums.UserState;

import javax.persistence.*;
import javax.validation.constraints.Email;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "t_user")
@SuperBuilder(toBuilder = true)
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

    @Singular
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "t_user_role",
            joinColumns = @JoinColumn(name = "user_id",
                    referencedColumnName = "id"
            ),
            inverseJoinColumns = @JoinColumn(name = "role_id",
                    referencedColumnName = "id"
            )
    )
    Set<Role> roles;

    @Column
    @Enumerated(EnumType.STRING)
    UserState state;

    public Set<SimpleGrantedAuthority> getAuthorities() {
        return roles.stream()
                .map(r -> new SimpleGrantedAuthority(r.getName().name()))
                .collect(Collectors.toSet());
    }

    public boolean rolesContains(RoleEnum name) {
        return roles.stream().anyMatch(f -> f.getName().equals(name));
    }
}
