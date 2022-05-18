package pro.appwork.open_university.service;

import pro.appwork.open_university.model.entity.EmailToken;

public interface EmailTokenService {
    EmailToken generate(String email, Long groupId);

    EmailToken getByToken(String token);

    void removeToken(String token);
}
