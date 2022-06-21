package pro.appwork.open_university.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.ContentDisposition;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import pro.appwork.open_university.model.entity.*;
import pro.appwork.open_university.repository.LessonRepository;
import pro.appwork.open_university.repository.TaskRepository;
import pro.appwork.open_university.repository.TaskTypeRepository;
import pro.appwork.open_university.service.FileStorage;
import pro.appwork.open_university.service.TaskService;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TaskServiceImpl implements TaskService {
    private final TaskTypeRepository taskTypeRepository;
    private final LessonRepository lessonRepository;
    private final TaskRepository taskRepository;
    private final FileStorage fileStorage;

    @Override
    public Task getTask(Long id) {
        return taskRepository.findById(id).orElseThrow();
    }

    @Override
    public boolean taskNotForStudent(Long id, Student student) {
        return !taskRepository.checkTaskForStudent(id, student.getGroup().getId());
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
    public void uploadFile(Teacher teacher, Long taskId, MultipartFile file) throws RuntimeException {
        Task task = taskRepository.findById(taskId).orElseThrow(
                EntityNotFoundException::new
        );

        fileStorage.deleteIfExists(Path.of(task.getFilePath()));

        Path path = createPath(task, file.getOriginalFilename());
        fileStorage.upload(path, file);

        taskRepository.save(task.toBuilder()
                .filePath(path.toString())
                .fileName(file.getOriginalFilename())
                .uploadDate(LocalDateTime.now())
                .build()
        );

    }

    @Override
    public ResponseEntity<InputStreamResource> downloadFile(Long id) {
        Task task = taskRepository.findById(id).orElseThrow();
        try {
            Resource file = fileStorage.download(Path.of(task.getFilePath()));
            return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_DISPOSITION,
                            ContentDisposition.attachment()
                                    .filename(task.getFileName(), StandardCharsets.UTF_8)
                                    .build().toString()
                    )
                    .contentLength(file.contentLength())
                    .contentType(MediaType.MULTIPART_FORM_DATA)
                    .body(new InputStreamResource(file.getInputStream()));

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private Path createPath(Task task, String fileName) {
        return Path.of("Учебный процесс",
                task.getLesson().getGroup().getLearningStartDate().format(DateTimeFormatter.ofPattern("yyyy")),
                task.getLesson().getGroup().getName(),
                task.getLesson().getSemester().getDescription(),
                task.getLesson().getName(),
                task.getType().getName(),
                fileName
        );
    }
}
