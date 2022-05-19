package pro.appwork.open_university.service;

import pro.appwork.open_university.model.entity.CustomUser;

public interface UserService {
    void createNew(CustomUser user);

    CustomUser getById(Long id);
}
