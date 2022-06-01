package pro.appwork.open_university.service;

import pro.appwork.open_university.model.entity.TaskType;

import java.util.List;

public interface TaskTypeService {
    List<TaskType> getAll();

    TaskType getById(Long id);

    void createByName(String name);

    void deleteById(Long id);

    void updateById(Long id, String name);
}
