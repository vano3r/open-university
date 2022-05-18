package pro.appwork.open_university.model.entity;

import lombok.*;

import javax.persistence.*;
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

    @Column(unique = true)
    String token;

    @Column
    String email;

    @Column
    Long groupId;

    @Column
    LocalDateTime createdDate;

    @Column
    LocalDateTime expiredDate;
}
