package pro.appwork.open_university.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pro.appwork.open_university.model.entity.Document;
import pro.appwork.open_university.model.entity.Teacher;
import pro.appwork.open_university.model.enums.DocumentTypeEnum;

import java.util.List;
import java.util.Optional;

@Repository
public interface DocumentRepository extends JpaRepository<Document, Long> {
    List<Document> findAllByTeacherAndType(Teacher teacher, DocumentTypeEnum type);

    Optional<Document> findByTeacherAndId(Teacher teacher, Long id);

    Optional<Document> findByTeacherAndFilePath(Teacher teacher, String filePath);

    void deleteByTeacherAndId(Teacher teacher, Long id);
}
