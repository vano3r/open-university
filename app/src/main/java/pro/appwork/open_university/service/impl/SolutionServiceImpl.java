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
import pro.appwork.open_university.model.entity.Solution;
import pro.appwork.open_university.model.entity.Student;
import pro.appwork.open_university.model.entity.Task;
import pro.appwork.open_university.repository.SolutionRepository;
import pro.appwork.open_university.repository.TaskRepository;
import pro.appwork.open_university.service.FileStorage;
import pro.appwork.open_university.service.SolutionService;

import javax.persistence.EntityNotFoundException;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SolutionServiceImpl implements SolutionService {
    private final SolutionRepository solutionRepository;
    private final TaskRepository taskRepository;
    private final FileStorage fileStorage;

    @Override
    public void uploadFile(Student student, Long taskId, MultipartFile file) {
        Task task = taskRepository.findById(taskId).orElseThrow(
                EntityNotFoundException::new
        );

        Optional<Solution> optSolution = solutionRepository.findFirstByStudentAndTask(student, task);

        Path path = createPath(student, task, file.getOriginalFilename());
        fileStorage.upload(path, file);

        if (optSolution.isPresent()) {
            fileStorage.delete(Path.of(optSolution.get().getFilePath()));

            solutionRepository.save(optSolution.get().toBuilder()
                    .filePath(path.toString())
                    .fileName(file.getOriginalFilename())
                    .uploadDate(LocalDateTime.now())
                    .build()
            );
        } else {
            solutionRepository.save(Solution.builder()
                    .task(task)
                    .student(student)
                    .fileName(file.getOriginalFilename())
                    .filePath(path.toString())
                    .build()
            );
        }
    }

    @Override
    public List<Solution> getAllForTask(Task task) {
        return solutionRepository.findAllByTask(task);
    }

    @Override
    public ResponseEntity<InputStreamResource> downloadFile(Long id) {
        Solution solution = solutionRepository.findById(id).orElseThrow();

        try {
            Resource file = fileStorage.download(Path.of(solution.getFilePath()));
            return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_DISPOSITION,
                            ContentDisposition.attachment()
                                    .filename(solution.getFileName(), StandardCharsets.UTF_8)
                                    .build().toString()
                    )
                    .contentLength(file.contentLength())
                    .contentType(MediaType.MULTIPART_FORM_DATA)
                    .body(new InputStreamResource(file.getInputStream()));

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private Path createPath(Student student, Task task, String fileName) {
        return Path.of("Учебный процесс",
                task.getLesson().getGroup().getLearningStartDate().format(DateTimeFormatter.ofPattern("yyyy")),
                task.getLesson().getGroup().getName(),
                task.getLesson().getSemester().getDescription(),
                task.getLesson().getName(),
                task.getType().getName(),
                student.getShortName(),
                fileName
        );
    }
}
