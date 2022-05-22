package pro.appwork.open_university.service;

import pro.appwork.open_university.model.entity.CustomUser;
import pro.appwork.open_university.model.entity.Group;
import pro.appwork.open_university.model.enums.UserRole;

public interface RegistrationService {
    /**
     * Метод проверяет действительность токена регистрации
     *
     * @param token токен регистрации
     * @return true - токен действительный, false - недействительный
     */
    boolean isValidToken(String token);

    /**
     * Метод выполняет генерацию токена и отправляет приглашение на почту
     *
     * @param email почта для приглашения
     * @param role  роль пользвателя
     * @param group группа для студента
     */
    void sendInvite(String email, UserRole role, Group group);

    void registrationUser(CustomUser user);
}
