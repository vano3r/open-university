package pro.appwork.open_university.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pro.appwork.open_university.model.entity.Group;
import pro.appwork.open_university.model.entity.Lesson;
import pro.appwork.open_university.model.enums.Semester;
import pro.appwork.open_university.repository.LessonRepository;
import pro.appwork.open_university.service.LessonService;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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
        return null;
    }

    @Override
    public Map<Semester, List<Lesson>> getAllMapBySemester(Group group) {
        return lessonRepository.findAllByGroup(group)
                .stream()
                .sorted(Comparator.comparing(Lesson::getSemester))
                .collect(Collectors.groupingBy(Lesson::getSemester));
    }
}
