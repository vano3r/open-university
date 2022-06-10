package pro.appwork.open_university.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import pro.appwork.open_university.model.entity.Document;
import pro.appwork.open_university.model.entity.DocumentLabel;
import pro.appwork.open_university.model.entity.Teacher;
import pro.appwork.open_university.repository.DocumentLabelRepository;
import pro.appwork.open_university.repository.DocumentRepository;
import pro.appwork.open_university.service.DocumentService;
import pro.appwork.open_university.service.FileStorage;

import java.nio.file.Path;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DocumentServiceImpl implements DocumentService {
    private final DocumentLabelRepository labelRepository;
    private final DocumentRepository documentRepository;
    private final FileStorage fileStorage;

    @Override
    public List<DocumentLabel> getAllLabels(Teacher teacher) {
        return labelRepository.findAllByTeacher(teacher);
    }

    @Override
    public List<Document> getAllDocumentByLabel(Teacher teacher, String labelName) {
        DocumentLabel label = labelRepository.findByTeacherAndName(teacher, labelName)
                .orElseThrow();

        return documentRepository.findAllByLabel(label);
    }

    @Override
    public void createLabel(Teacher teacher, String labelName) {
        DocumentLabel newLabel = DocumentLabel.builder()
                .teacher(teacher)
                .name(labelName)
                .build();

        labelRepository.save(newLabel);
    }

    @Override
    public void deleteLabel(Teacher teacher, String labelName) {
        labelRepository.deleteByTeacherAndName(teacher, labelName);
    }

    @Override
    public void createDocument(Teacher teacher, String labelName, MultipartFile file) {
        DocumentLabel label = labelRepository.findByTeacherAndName(teacher, labelName).orElseThrow();

        var optDocument = documentRepository.findByLabelAndName(label, file.getOriginalFilename());
        Path path = createPath(teacher, labelName, file.getOriginalFilename());

        if (optDocument.isEmpty()) {
            Document newDocument = Document.builder()
                    .name(file.getOriginalFilename())
                    .filePath(path.toString())
                    .label(label)
                    .build();

            documentRepository.save(newDocument);
        } else {
            documentRepository.save(optDocument.get().toBuilder().uploadTime(LocalDateTime.now()).build());
        }

        fileStorage.upload(path, file);
    }

    @Override
    public void deleteDocument(Teacher teacher, String labelName, String documentName) {
        DocumentLabel label = labelRepository.findByTeacherAndName(teacher, labelName).orElseThrow();
        Document document = documentRepository.findByLabelAndName(label, documentName).orElseThrow();

        Path path = Path.of(document.getFilePath());
        if (fileStorage.notExists(path)) {
            throw new RuntimeException();
        }

        documentRepository.delete(document);
        fileStorage.delete(path);
    }

    private Path createPath(Teacher teacher, String labelName, String fileName) {
        return Path.of("Документы", teacher.getShortName(), labelName, fileName);
    }
}
