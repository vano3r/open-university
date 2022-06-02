package pro.appwork.open_university.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pro.appwork.open_university.model.entity.Group;
import pro.appwork.open_university.model.entity.Lesson;
import pro.appwork.open_university.model.entity.Teacher;

import java.util.List;

@Repository
public interface LessonRepository extends CrudRepository<Lesson, Long> {
    List<Lesson> findAllByGroupAndTeacher(Group group, Teacher teacher);

    List<Lesson> findAllByGroup(Group group);
}
