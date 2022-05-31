package pro.appwork.open_university.service;

import pro.appwork.open_university.model.entity.TaskType;

import java.util.List;

public interface TaskService {
    List<TaskType> getAll();

    void addTask(TaskType taskType);

    void delete(Long id);
}
