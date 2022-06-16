package pro.appwork.open_university.service;

import org.springframework.core.io.InputStreamResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;
import pro.appwork.open_university.model.entity.Document;
import pro.appwork.open_university.model.entity.DocumentLabel;
import pro.appwork.open_university.model.entity.Teacher;

import java.util.List;

public interface DocumentService {
    List<DocumentLabel> getAllLabels(Teacher teacher);

    List<Document> getAllDocumentByLabel(Teacher teacher, String labelName);

    void createLabel(Teacher teacher, String labelName);

    void deleteLabel(Teacher teacher, String labelName);

    void createDocument(Teacher teacher, String labelName, MultipartFile file);

    void deleteDocument(Teacher teacher, Long id);

    ResponseEntity<InputStreamResource> downloadFile(Teacher teacher, Long id);
}
