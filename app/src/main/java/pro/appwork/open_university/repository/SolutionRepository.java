package pro.appwork.open_university.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pro.appwork.open_university.model.entity.Solution;
import pro.appwork.open_university.model.entity.Student;
import pro.appwork.open_university.model.entity.Task;

import java.util.List;
import java.util.Optional;

@Repository
public interface SolutionRepository extends CrudRepository<Solution, Long> {
    List<Solution> findAllByTask(Task task);

    Optional<Solution> findFirstByStudentAndTask(Student student, Task task);
}
