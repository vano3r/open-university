package pro.appwork.open_university.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import pro.appwork.open_university.model.dto.RegistrationDto;
import pro.appwork.open_university.model.entity.Group;
import pro.appwork.open_university.model.entity.RegistrationToken;
import pro.appwork.open_university.model.entity.Student;
import pro.appwork.open_university.model.entity.Teacher;
import pro.appwork.open_university.model.enums.UserRole;
import pro.appwork.open_university.model.enums.UserState;
import pro.appwork.open_university.repository.RegistrationTokenRepository;
import pro.appwork.open_university.repository.StudentRepository;
import pro.appwork.open_university.repository.TeacherRepository;
import pro.appwork.open_university.service.MailSender;
import pro.appwork.open_university.service.RegistrationService;
import pro.appwork.open_university.util.EmailTemplate;

import javax.persistence.EntityNotFoundException;
import java.time.LocalDateTime;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RegistrationServiceImpl implements RegistrationService {
    private final MailSender mailSender;
    private final RegistrationTokenRepository registrationTokenRepository;
    private final StudentRepository studentRepository;
    private final TeacherRepository teacherRepository;
    private final PasswordEncoder passwordEncoder;

    @Value("${server.host-for-smtp}")
    private String serverHost;

    private String generateToken(String email, UserRole role, Group group) {
        RegistrationToken token = RegistrationToken.builder()
                .email(email)
                .role(role)
                .group(group)
                .build();

        registrationTokenRepository.save(token);

        return token.getToken();
    }

    @Override
    public Optional<RegistrationToken> getValidToken(String token) {
        var validToken = registrationTokenRepository.findByToken(token)
                .orElseThrow(() -> new EntityNotFoundException("Token %s not found".formatted(token)));

        if (validToken.getExpiredDate().isBefore(LocalDateTime.now())) {
            registrationTokenRepository.delete(validToken);
            return Optional.empty();
        }

        return Optional.of(validToken);
    }

    @Override
    public void sendInvite(String email, UserRole role, Group group) {
        //Дополнительная проверка, у преподователя не может быть группы
        if (role.equals(UserRole.TEACHER)) {
            group = null;
        }

        var token = generateToken(email, role, group);
        mailSender.send(email,
                EmailTemplate.EMAIL_SUBJECT_INVITE,
                EmailTemplate.EMAIL_TEXT_INVITE.formatted(serverHost, token)
        );
    }

    @Override
    public void registrationUser(RegistrationDto dto) {
        if (dto.getRole().equals(UserRole.STUDENT)) {
            Student student = Student.builder()
                    .email(dto.getEmail())
                    .password(passwordEncoder.encode(dto.getPassword()))
                    .group(dto.getGroup())
                    .role(dto.getRole())
                    .state(UserState.ACTIVE)
                    .firstName(dto.getFirstName())
                    .lastName(dto.getLastName())
                    .middleName(dto.getMiddleName())
                    .build();

            studentRepository.save(student);
        } else {
            Teacher teacher = Teacher.builder()
                    .email(dto.getEmail())
                    .password(passwordEncoder.encode(dto.getPassword()))
                    .role(dto.getRole())
                    .state(UserState.ACTIVE)
                    .firstName(dto.getFirstName())
                    .lastName(dto.getLastName())
                    .middleName(dto.getMiddleName())
                    .build();

            teacherRepository.save(teacher);
        }
    }
}