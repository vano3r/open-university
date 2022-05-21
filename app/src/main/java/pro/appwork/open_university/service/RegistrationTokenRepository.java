package pro.appwork.open_university.service;

import pro.appwork.open_university.model.entity.Group;
import pro.appwork.open_university.model.entity.RegistrationToken;
import pro.appwork.open_university.model.enums.UserRole;

public interface RegistrationTokenRepository {
    RegistrationToken generate(String email, UserRole role, Group group);

    RegistrationToken getByToken(String token);

    void removeToken(String token);
}
