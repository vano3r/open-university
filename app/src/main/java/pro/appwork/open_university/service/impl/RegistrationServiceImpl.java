package pro.appwork.open_university.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pro.appwork.open_university.model.entity.Group;
import pro.appwork.open_university.model.entity.RegistrationToken;
import pro.appwork.open_university.model.enums.UserRole;
import pro.appwork.open_university.repository.RegistrationTokenRepository;
import pro.appwork.open_university.service.MailSender;
import pro.appwork.open_university.service.RegistrationService;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class RegistrationServiceImpl implements RegistrationService {
    private final MailSender mailSender;
    private final RegistrationTokenRepository registrationTokenRepository;

    @Override
    public String generateToken(String email, UserRole role, Group group) {
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
//        mailSender.send();
    }
}
