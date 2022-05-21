package pro.appwork.open_university.service;

import pro.appwork.open_university.model.entity.Group;
import pro.appwork.open_university.model.enums.UserRole;

public interface MailService {
    void send(String to, UserRole role, Group group);
}
