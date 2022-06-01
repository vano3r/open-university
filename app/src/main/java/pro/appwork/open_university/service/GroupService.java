package pro.appwork.open_university.service;

import pro.appwork.open_university.model.entity.Group;

import java.util.List;

public interface GroupService {
    List<Group> getAll();

    Group getById(Long id);

    void createByName(String name);

    void deleteById(Long id);

    void updateById(Long id, String name);
}
