package pro.appwork.open_university.service;

import org.springframework.core.io.InputStreamResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;
import pro.appwork.open_university.model.entity.Solution;
import pro.appwork.open_university.model.entity.Student;
import pro.appwork.open_university.model.entity.Task;

import java.util.List;

public interface SolutionService {

    void uploadFile(Student student, Long taskId, MultipartFile file);

    List<Solution> getAllForTask(Task task);

    ResponseEntity<InputStreamResource> downloadFile(Long id);
}
