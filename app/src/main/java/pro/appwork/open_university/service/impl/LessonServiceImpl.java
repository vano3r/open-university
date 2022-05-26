package pro.appwork.open_university.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pro.appwork.open_university.model.entity.Lesson;
import pro.appwork.open_university.repository.LessonRepository;
import pro.appwork.open_university.service.LessonService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LessonServiceImpl implements LessonService {
    private final LessonRepository lessonRepository;

    @Override
    public Lesson getById(Long id) {
        return null;
    }

    @Override
    public List<Lesson> getAllByCourseGroupId(Long id) {
        return lessonRepository.findAllByCourseGroupId(id);
    }
}
