package pro.appwork.open_university.service;

import pro.appwork.open_university.model.entity.Group;
import pro.appwork.open_university.model.enums.UserRole;

public interface RegistrationService {
    /**
     * Метод генерации токена регистрации
     *
     * @param email адрес электронной почты
     * @param role  роль пользователя
     * @param group группа, если роль студента
     * @return сгенерированный токен
     */
    String generateToken(String email, UserRole role, Group group);

    /**
     * Метод проверяет действительность токена регистрации
     *
     * @param token токен регистрации
     * @return true - токен действительный, false - недействительный
     */
    boolean isValidToken(String token);

    void sendInvite(String email, UserRole role, Group group);
}
