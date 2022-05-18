package pro.appwork.open_university.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pro.appwork.open_university.model.entity.EmailToken;
import pro.appwork.open_university.repository.EmailTokenRepository;
import pro.appwork.open_university.service.EmailTokenService;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class DefaultEmailTokenService implements EmailTokenService {
    private final EmailTokenRepository repository;

    @Override
    public EmailToken generate(String email, Long groupId) {
        EmailToken token = EmailToken.builder()
                .email(email)
                .groupId(groupId)
                .token(UUID.randomUUID().toString())
                .createdDate(LocalDateTime.now())
                .expiredDate(LocalDateTime.now().plusDays(3))
                .build();

        repository.save(token);

        return token;
    }

    @Override
    public EmailToken getByToken(String token) {
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
