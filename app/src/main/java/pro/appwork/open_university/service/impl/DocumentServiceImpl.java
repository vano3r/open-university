package pro.appwork.open_university.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import pro.appwork.open_university.model.entity.Document;
import pro.appwork.open_university.model.entity.Teacher;
import pro.appwork.open_university.model.enums.DocumentTypeEnum;
import pro.appwork.open_university.repository.DocumentRepository;
import pro.appwork.open_university.service.DocumentService;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DocumentServiceImpl implements DocumentService {
    private final static String DOCUMENT_UPLOAD_PATH = "Документы/";
    private final DocumentRepository documentRepository;

    @Override
    public void uploadFile(Teacher teacher, MultipartFile file, DocumentTypeEnum type) {
        StringBuilder uploadPath = new StringBuilder()
                .append(DOCUMENT_UPLOAD_PATH)
                .append(teacher.getShortName())
                .append("/")
                .append(type.getDescription())
                .append("/")
                .append(file.getOriginalFilename());
        try {
            Path path = Path.of(uploadPath.toString());
            Files.createDirectories(path.getParent());

            if (!Files.exists(path)) {
                documentRepository.save(Document.builder()
                        .teacher(teacher)
                        .type(type)
                        .name(file.getOriginalFilename())
                        .filePath(uploadPath.toString())
                        .build());
            }
            Document document = documentRepository.findByTeacherAndFilePath(teacher, path.toString()).orElseThrow();

            Files.copy(file.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
            documentRepository.save(document.toBuilder().uploadTime(LocalDateTime.now()).build());

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public ResponseEntity<InputStreamResource> downloadFile(Teacher teacher, Long id) {
        Document document = documentRepository.findByTeacherAndId(teacher, id).orElseThrow();

        try {

            return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_DISPOSITION,
                            "attachment;filename=" + URLEncoder.encode(
                                    document.getName(), StandardCharsets.UTF_8)
                    )
                    .contentLength(document.getFile().length())
                    .contentType(MediaType.MULTIPART_FORM_DATA)
                    .body(new InputStreamResource(new FileInputStream(document.getFile())));

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Document> getAllByTeacherAndType(Teacher teacher, DocumentTypeEnum type) {
        return documentRepository.findAllByTeacherAndType(teacher, type);
    }

    @Override
    public void deleteById(Teacher teacher, Long id) {
        documentRepository.deleteByTeacherAndId(teacher, id);
    }
}
