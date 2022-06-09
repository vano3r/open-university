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
import pro.appwork.open_university.security.annotation.IsAdmin;
import pro.appwork.open_university.service.FileStorage;
import pro.appwork.open_university.service.TaskService;

import javax.transaction.Transactional;
import java.nio.file.Path;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class TaskServiceImpl implements TaskService {
    private final TaskTypeRepository taskTypeRepository;
    private final LessonRepository lessonRepository;
    private final TaskRepository taskRepository;
    private final FileStorage fileStorage;

    @IsAdmin
    @Override
    public Task getTask(Long id) {
        return taskRepository.findById(id).orElseThrow();
    }

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


        Path path = createPath(task, fileName);
//            Files.createDirectories(path);
//            Files.copy(file.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);

        fileStorage.upload(path, file);
        taskRepository.save(task.toBuilder().filePath(path.toString()).build());
    }

    private Path createPath(Task task, String fileName) {
        return Path.of(task.getLesson().getSemester().getCourse().toString() + " Курс",
                task.getLesson().getSemester().getDescription(),
                task.getLesson().getGroup().getName(),
                task.getLesson().getName(),
                task.getType().getName(),
                fileName
        );
    }
}
