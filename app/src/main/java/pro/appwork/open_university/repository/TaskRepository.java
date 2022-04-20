package pro.appwork.open_university.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pro.appwork.open_university.model.entity.Task;

@Repository
public interface TaskRepository extends CrudRepository<Task, Long> {
}
