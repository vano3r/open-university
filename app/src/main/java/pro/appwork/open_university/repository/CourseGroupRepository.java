package pro.appwork.open_university.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pro.appwork.open_university.model.entity.CourseGroup;

@Repository
public interface CourseGroupRepository extends JpaRepository<CourseGroup, Long> {
}