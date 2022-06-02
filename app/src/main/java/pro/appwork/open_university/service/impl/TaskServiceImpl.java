package pro.appwork.open_university.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
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
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.Objects;

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
        String fileName = StringUtils.cleanPath(Objects.requireNonNull(file.getOriginalFilename()));

        Task task = taskRepository.findById(taskId).orElseThrow();

        StringBuilder builderPath = new StringBuilder()
                .append("data/")
                .append(task.getLesson().getSemester().getCourse())
                .append(" Курс")
                .append("/")
                .append(task.getLesson().getSemester().getDescription())
                .append("/")
                .append(task.getLesson().getGroup().getName())
                .append("/")
                .append(task.getLesson().getName())
                .append("/")
                .append(task.getType().getName())
                .append("/")
                .append(fileName);

        try {
            Files.createDirectories(Path.of(builderPath.toString()));
            Path path = Paths.get(builderPath.toString());
            Files.copy(file.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);

            taskRepository.save(task.toBuilder().filePath(builderPath.toString()).build());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
