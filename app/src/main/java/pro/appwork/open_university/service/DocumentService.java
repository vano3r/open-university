package pro.appwork.open_university.service;

import org.springframework.core.io.InputStreamResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;
import pro.appwork.open_university.model.entity.Document;
import pro.appwork.open_university.model.entity.Teacher;
import pro.appwork.open_university.model.enums.DocumentTypeEnum;

import java.util.List;

public interface DocumentService {

    void uploadFile(Teacher teacher, MultipartFile file, DocumentTypeEnum type);

    ResponseEntity<InputStreamResource> downloadFile(Teacher teacher, Long fileId);

    List<Document> getAllByTeacherAndType(Teacher teacher, DocumentTypeEnum type);

    void deleteById(Teacher teacher, Long id);
}
