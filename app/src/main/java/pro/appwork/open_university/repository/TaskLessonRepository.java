package pro.appwork.open_university.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pro.appwork.open_university.model.entity.TaskLesson;

@Repository
public interface TaskLessonRepository extends JpaRepository<TaskLesson, Long> {
}
