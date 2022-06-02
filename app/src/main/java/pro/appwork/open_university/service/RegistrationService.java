package pro.appwork.open_university.service;

import pro.appwork.open_university.model.dto.RegistrationDto;
import pro.appwork.open_university.model.entity.Group;
import pro.appwork.open_university.model.entity.RegistrationToken;
import pro.appwork.open_university.model.enums.RoleEnum;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

public interface RegistrationService {
    /**
     * Метод проверяет действительность токена регистрации
     *
     * @param token токен регистрации
     * @return true - токен действительный, false - недействительный
     */
    Optional<RegistrationToken> getValidToken(String token);

    /**
     * Метод выполняет генерацию токена и отправляет приглашение на почту
     *
     * @param email почта для приглашения
     * @param role  роль пользвателя
     * @param group группа для студента
     */
    void sendInvite(HttpServletRequest request, String email, RoleEnum role, Group group);

    void registrationUser(RegistrationDto dto);
}
