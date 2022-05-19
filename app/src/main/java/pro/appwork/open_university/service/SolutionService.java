package pro.appwork.open_university.service;

import org.springframework.web.multipart.MultipartFile;
import pro.appwork.open_university.model.entity.CustomUser;
import pro.appwork.open_university.model.entity.Solution;
import pro.appwork.open_university.model.entity.Subject;
import pro.appwork.open_university.model.entity.Task;

public interface SolutionService {
    void createSolution(CustomUser user, Subject subject, Task task, MultipartFile file);
}
