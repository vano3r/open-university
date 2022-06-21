package pro.appwork.open_university.service;

import pro.appwork.open_university.model.entity.Group;
import pro.appwork.open_university.model.enums.AcademicDegreeEnum;
import pro.appwork.open_university.model.enums.GroupStatusEnum;

import java.util.List;

public interface GroupService {
    List<Group> getAll();

    Group getById(Long id);

    void create(String name, Integer year, AcademicDegreeEnum degree);

    void deleteById(Long id);

    void updateById(Long id, String name, Integer year, AcademicDegreeEnum degree, GroupStatusEnum status);
}
