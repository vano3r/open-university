package pro.appwork.open_university.service;

import pro.appwork.open_university.model.entity.Lesson;

import java.util.List;

public interface LessonService {
    Lesson getById(Long id);

    List<Lesson> getAllByCourseGroupId(Long id);
}
