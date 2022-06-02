package pro.appwork.open_university.service;

import org.springframework.web.multipart.MultipartFile;
import pro.appwork.open_university.model.entity.TaskType;
import pro.appwork.open_university.model.entity.Teacher;

import java.util.List;

public interface TaskService {
    List<TaskType> getAll();

    void addTask(TaskType taskType);

    void delete(Long id);

    void create(Teacher teacher, Long lessonId, String taskType);

    void uploadFile(Teacher teacher, Long taskId, MultipartFile file);
}
