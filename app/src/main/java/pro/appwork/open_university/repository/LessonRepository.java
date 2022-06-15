package pro.appwork.open_university.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pro.appwork.open_university.model.entity.Group;
import pro.appwork.open_university.model.entity.Lesson;
import pro.appwork.open_university.model.entity.Teacher;

import java.util.List;

@Repository
public interface LessonRepository extends CrudRepository<Lesson, Long> {
    List<Lesson> findAllByGroupAndTeacherOrderByName(Group group, Teacher teacher);

    @Query(value = """
            SELECT l.* FROM t_lesson l
            WHERE l.group_id = :groupId
                AND EXISTS (SELECT id FROM t_task t WHERE l.id = t.lesson_id)
            ORDER BY l.name
            """, nativeQuery = true)
    List<Lesson> findAllByGroupAndTasksExists(@Param("groupId") Long groupId);

    List<Lesson> findAllByGroupOrderByName(Group group);
}
