package pro.appwork.open_university.service;

import pro.appwork.open_university.model.entity.Task;

import java.util.List;

public interface TaskService {
    List<Task> getAll();

    void addTask(Task task);

    void delete(Long id);
}
