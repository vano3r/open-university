package pro.appwork.open_university.model.entity;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import javax.validation.constraints.Email;
import java.time.LocalDateTime;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "t_email_token")
public class EmailToken {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    Long id;

    @Column
    String token;

    @Column
    String email;

    @Column
    LocalDateTime createdDate;

    @Column
    LocalDateTime expiredDate;
}
