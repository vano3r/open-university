package pro.appwork.open_university.model.entity;

import lombok.*;
import lombok.experimental.SuperBuilder;
import pro.appwork.open_university.model.enums.UserRole;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Getter
@SuperBuilder
@NoArgsConstructor
@Table(name = "t_registration_token")
public class RegistrationToken {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @Builder.Default
    @Column(unique = true)
    private String token = UUID.randomUUID().toString();

    @Column
    private String email;

    @ManyToOne
    @JoinColumn(name = "group_id")
    private Group group;

    @Column
    @Enumerated(EnumType.STRING)
    private UserRole role;

    @Column
    @Builder.Default
    private LocalDateTime createdDate = LocalDateTime.now();

    @Column
    @Builder.Default
    private LocalDateTime expiredDate = LocalDateTime.now().plusDays(3);
}
