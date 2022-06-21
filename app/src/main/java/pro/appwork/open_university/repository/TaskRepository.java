package pro.appwork.open_university.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pro.appwork.open_university.model.entity.Task;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
    @Query(value = """
            SELECT COUNT(t.id) > 0
            FROM t_task t
                     JOIN t_lesson l on t.lesson_id = l.id
            WHERE t.id = :taskId
              AND l.group_id = :groupId
            """, nativeQuery = true)
    boolean checkTaskForStudent(Long taskId, Long groupId);
}
