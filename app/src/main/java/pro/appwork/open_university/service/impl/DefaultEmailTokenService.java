package pro.appwork.open_university.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pro.appwork.open_university.model.entity.Group;
import pro.appwork.open_university.model.entity.RegistrationToken;
import pro.appwork.open_university.model.enums.UserRole;
import pro.appwork.open_university.repository.EmailTokenRepository;
import pro.appwork.open_university.service.RegistrationTokenRepository;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class DefaultEmailTokenService implements RegistrationTokenRepository {
    private final EmailTokenRepository repository;

    @Override
    public RegistrationToken generate(String email, UserRole role, Group group) {
        RegistrationToken token = RegistrationToken.builder()
                .email(email)
                .role(role)
                .group(group)
                .build();

        repository.save(token);

        return token;
    }

    @Override
    public RegistrationToken getByToken(String token) {
        return repository.findByToken(token).orElseThrow(
                () -> new RuntimeException("Not found token")
        );
    }

    @Override
    @Transactional
    public void removeToken(String token) {
        repository.deleteByToken(token);
    }
}
