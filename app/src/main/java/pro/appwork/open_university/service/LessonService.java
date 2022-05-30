package pro.appwork.open_university.service;

import pro.appwork.open_university.model.entity.Group;
import pro.appwork.open_university.model.entity.Lesson;
import pro.appwork.open_university.model.enums.Semester;

import java.util.List;
import java.util.Map;

public interface LessonService {
    Lesson getById(Long id);

    List<Lesson> getAllByCourseGroupId(Long id);

    Map<Semester, List<Lesson>> getAllMapBySemester(Group group);
}
