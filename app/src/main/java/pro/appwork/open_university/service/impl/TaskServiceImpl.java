package pro.appwork.open_university.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pro.appwork.open_university.model.entity.TaskType;
import pro.appwork.open_university.repository.TaskTypeRepository;
import pro.appwork.open_university.service.TaskService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TaskServiceImpl implements TaskService {
    private final TaskTypeRepository taskTypeRepository;

    @Override
    public List<TaskType> getAll() {
        return taskTypeRepository.findAll();
    }

    @Override
    public void addTask(TaskType taskType) {
        taskTypeRepository.save(taskType);
    }

    @Override
    public void delete(Long id) {
        taskTypeRepository.deleteById(id);
    }
}
