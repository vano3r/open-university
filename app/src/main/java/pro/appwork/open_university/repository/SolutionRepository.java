package pro.appwork.open_university.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pro.appwork.open_university.model.entity.Solution;
import pro.appwork.open_university.model.entity.Task;

import java.util.List;

@Repository
public interface SolutionRepository extends CrudRepository<Solution, Long> {
    List<Solution> findAllByTask(Task task);
}
