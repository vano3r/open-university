package pro.appwork.open_university.model.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import javax.validation.constraints.Email;
import java.util.ArrayList;
import java.util.List;

import static javax.persistence.GenerationType.IDENTITY;
import static lombok.AccessLevel.PRIVATE;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "t_user")
@FieldDefaults(level = PRIVATE)
public class DefaultUser {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    Long id;
    String firstName;
    String lastName;
    String middleName;
    @Email
    @Column(unique = true)
    String email;
    String password;
    String role;

    @OneToMany
    @JoinColumn(name = "teacher_id")
    List<Subject> subjects = new ArrayList<>();

    @OneToMany
    @JoinColumn(name = "student_id")
    List<Solution> solutions = new ArrayList<>();
}
