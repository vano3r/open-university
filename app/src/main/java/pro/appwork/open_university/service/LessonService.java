package pro.appwork.open_university.service;

import pro.appwork.open_university.model.entity.Group;
import pro.appwork.open_university.model.entity.Lesson;
import pro.appwork.open_university.model.entity.Teacher;
import pro.appwork.open_university.model.enums.Semester;

import java.util.List;
import java.util.Map;

public interface LessonService {
    void create(Teacher teacher, Long groupId, Semester semester, String lessonName);

    void deleteById(Long id, Teacher teacher);

    void updateById(Long id, Teacher teacher, String name);

    Map<Semester, List<Lesson>> getAllMapBySemester(Group group, Teacher teacher);

    Map<Semester, List<Lesson>> getAllMapBySemester(Group group);
}
