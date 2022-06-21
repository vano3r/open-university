package pro.appwork.open_university.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pro.appwork.open_university.model.entity.Group;
import pro.appwork.open_university.model.entity.Lesson;
import pro.appwork.open_university.model.entity.Teacher;
import pro.appwork.open_university.model.enums.RoleEnum;
import pro.appwork.open_university.model.enums.Semester;
import pro.appwork.open_university.repository.GroupRepository;
import pro.appwork.open_university.repository.LessonRepository;
import pro.appwork.open_university.service.LessonService;

import javax.persistence.EntityNotFoundException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class LessonServiceImpl implements LessonService {
    private final LessonRepository lessonRepository;
    private final GroupRepository groupRepository;

    @Override
    public void create(Teacher teacher, Long groupId, Semester semester, String lessonName) {
        Group group = groupRepository.findById(groupId).orElseThrow();

        Lesson newLesson = Lesson.builder()
                .teacher(teacher)
                .group(group)
                .semester(semester)
                .name(lessonName)
                .build();

        lessonRepository.save(newLesson);
    }

    @Override
    public void deleteById(Long id, Teacher teacher) {
        Lesson lesson = lessonRepository.findById(id).orElseThrow(
                EntityNotFoundException::new
        );
        if (!lesson.getTeacher().equals(teacher)) {
            throw new RuntimeException();
        }

        lessonRepository.delete(lesson);
    }

    @Override
    public void updateById(Long id, Teacher teacher, String name) {
        Lesson lesson = lessonRepository.findById(id).orElseThrow(
                EntityNotFoundException::new
        );
        if (!lesson.getTeacher().equals(teacher)) {
            throw new RuntimeException();
        }

        Lesson newLesson = lesson.toBuilder().name(name).build();

        lessonRepository.save(newLesson);
    }

    @Override
    public Map<Semester, List<Lesson>> getAllMapBySemester(Group group, Teacher teacher) {
        Map<Semester, List<Lesson>> map;
        if (teacher.rolesContains(RoleEnum.ADMIN)) {
            map = createMapBySemester(
                    lessonRepository.findAllByGroupOrderByName(group)
            );
        } else {
            map = createMapBySemester(
                    lessonRepository.findAllByGroupAndTeacherOrderByName(group, teacher)
            );
        }

        for (var semester : Semester.getAll()) {
            if (!map.containsKey(semester)) {
                map.put(semester, List.of());
            }
        }

        return map;
    }

    @Override
    public Map<Semester, List<Lesson>> getAllMapBySemester(Group group) {
        var map = createMapBySemester(
                lessonRepository.findAllByGroupAndTasksExists(group.getId())
        );

        if (map.isEmpty()) {
            map.put(Semester.FIRST, List.of());
        }

        return map;
    }

    private Map<Semester, List<Lesson>> createMapBySemester(List<Lesson> list) {
        return list.stream()
                .collect(Collectors.groupingBy(Lesson::getSemester))
                .entrySet()
                .stream()
                .sorted(Map.Entry.comparingByKey())
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        (e1, e2) -> e1,
                        LinkedHashMap::new
                ));
    }
}
