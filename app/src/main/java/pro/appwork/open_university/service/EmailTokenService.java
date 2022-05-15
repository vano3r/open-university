package pro.appwork.open_university.service;

import pro.appwork.open_university.model.entity.EmailToken;

import java.util.Optional;

public interface EmailTokenService {
    EmailToken generate(String email);

    EmailToken getByToken(String token);
}
