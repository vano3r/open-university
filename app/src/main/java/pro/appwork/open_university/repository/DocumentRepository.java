package pro.appwork.open_university.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pro.appwork.open_university.model.entity.Document;
import pro.appwork.open_university.model.entity.DocumentLabel;

import java.util.List;
import java.util.Optional;

@Repository
public interface DocumentRepository extends JpaRepository<Document, Long> {
    List<Document> findAllByLabel(DocumentLabel label);

    Optional<Document> findByLabelAndName(DocumentLabel label, String name);

    Optional<Document> findByLabelAndId(DocumentLabel label, Long id);
}
