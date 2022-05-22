package pro.appwork.open_university.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import pro.appwork.open_university.model.entity.CustomUser;
import pro.appwork.open_university.model.entity.Group;
import pro.appwork.open_university.model.entity.RegistrationToken;
import pro.appwork.open_university.model.enums.UserRole;
import pro.appwork.open_university.repository.RegistrationTokenRepository;
import pro.appwork.open_university.service.MailSender;
import pro.appwork.open_university.service.RegistrationService;
import pro.appwork.open_university.util.EmailTemplate;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class RegistrationServiceImpl implements RegistrationService {
    private final MailSender mailSender;
    private final RegistrationTokenRepository registrationTokenRepository;

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
    public boolean isValidToken(String token) {
        var validToken = registrationTokenRepository.findByToken(token);

        if (validToken.isEmpty()) {
            return false;
        }
        return !validToken.get().getExpiredDate().isBefore(LocalDateTime.now());
    }

    @Override
    public void sendInvite(String email, UserRole role, Group group) {
        //Дополнительная проверка, у преподователя не может быть группы
        if (role.equals(UserRole.TEACHER)){
            group = null;
        }

        var token = generateToken(email, role, group);
        mailSender.send(email,
                EmailTemplate.EMAIL_SUBJECT_INVITE,
                EmailTemplate.EMAIL_TEXT_INVITE.formatted(serverHost, token));
    }

    @Override
    public void registrationUser(CustomUser user) {

    }
}
