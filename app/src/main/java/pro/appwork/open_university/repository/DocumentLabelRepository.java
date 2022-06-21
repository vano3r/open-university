package pro.appwork.open_university.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pro.appwork.open_university.model.entity.DocumentLabel;
import pro.appwork.open_university.model.entity.Teacher;

import java.util.List;
import java.util.Optional;

@Repository
public interface DocumentLabelRepository extends JpaRepository<DocumentLabel, Long> {
    List<DocumentLabel> findAllByTeacher(Teacher teacher);

    Optional<DocumentLabel> findByTeacherAndName(Teacher teacher, String name);

    void deleteByTeacherAndName(Teacher teacher, String name);
}
