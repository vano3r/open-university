package pro.appwork.open_university.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import pro.appwork.open_university.model.entity.CustomUser;
import pro.appwork.open_university.model.entity.Solution;
import pro.appwork.open_university.model.entity.Subject;
import pro.appwork.open_university.model.entity.Task;
import pro.appwork.open_university.repository.SolutionRepository;
import pro.appwork.open_university.repository.UserRepository;
import pro.appwork.open_university.service.SolutionService;

import javax.transaction.Transactional;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

@Service
@RequiredArgsConstructor
public class DefaultSolutionService implements SolutionService {
    private final SolutionRepository repository;
    private final UserRepository userRepository;

    @Value("${upload-file-path}")
    private String filePathUpload;

    @Override
    @Transactional
    public void createSolution(CustomUser user, Subject subject, Task task, MultipartFile file) {
        String fullPathUpload = filePathUpload + subject.getName() + "/" + task.getName() + "/" + file.getOriginalFilename();

        Solution solution = Solution.builder()
                .task(task)
                .filePath(fullPathUpload)
                .build();

        user.solutions().add(solution);

        try {
            File newFile = new File(fullPathUpload);

            newFile.getParentFile().mkdirs();
            FileOutputStream outputStream = new FileOutputStream(fullPathUpload);

            outputStream.write(file.getBytes());
            outputStream.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        repository.save(solution);
        userRepository.save(user);
    }
}
