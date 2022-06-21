package pro.appwork.open_university.service;

import org.springframework.core.io.InputStreamResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;
import pro.appwork.open_university.model.entity.Student;
import pro.appwork.open_university.model.entity.Task;
import pro.appwork.open_university.model.entity.TaskType;
import pro.appwork.open_university.model.entity.Teacher;

import java.util.List;

public interface TaskService {
    Task getTask(Long id);

    boolean taskNotForStudent(Long id, Student student);

    List<TaskType> getAll();

    void addTask(TaskType taskType);

    void delete(Long id);

    void create(Teacher teacher, Long lessonId, String taskType);

    void uploadFile(Teacher teacher, Long taskId, MultipartFile file) throws RuntimeException;

    ResponseEntity<InputStreamResource> downloadFile(Long id);
}
