package pro.appwork.open_university.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pro.appwork.open_university.model.entity.TaskType;
import pro.appwork.open_university.repository.TaskRepository;
import pro.appwork.open_university.service.TaskService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TaskServiceImpl implements TaskService {
    private final TaskRepository taskRepository;

    @Override
    public List<TaskType> getAll() {
        return taskRepository.findAll();
    }

    @Override
    public void addTask(TaskType taskType) {
        taskRepository.save(taskType);
    }

    @Override
    public void delete(Long id) {
        taskRepository.deleteById(id);
    }
}
