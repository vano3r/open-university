package pro.appwork.open_university.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import pro.appwork.open_university.model.entity.Lesson;
import pro.appwork.open_university.model.entity.Task;
import pro.appwork.open_university.model.entity.TaskType;
import pro.appwork.open_university.model.entity.Teacher;
import pro.appwork.open_university.repository.LessonRepository;
import pro.appwork.open_university.repository.TaskRepository;
import pro.appwork.open_university.repository.TaskTypeRepository;
import pro.appwork.open_university.service.TaskService;

import javax.transaction.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TaskServiceImpl implements TaskService {
    private final TaskTypeRepository taskTypeRepository;
    private final LessonRepository lessonRepository;
    private final TaskRepository taskRepository;

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

    @Override
    @Transactional
    public void create(Teacher teacher, Long lessonId, String taskType) {
        Lesson lesson = lessonRepository.findById(lessonId).orElseThrow();
        TaskType type = TaskType.builder().name(taskType).build();
        taskTypeRepository.save(type);
        taskRepository.save(Task.builder().type(type).lesson(lesson).build());
    }

    @Override
    public void uploadFile(Teacher teacher, Long taskId, MultipartFile file) {
        System.out.println("GOOD");
    }
}
