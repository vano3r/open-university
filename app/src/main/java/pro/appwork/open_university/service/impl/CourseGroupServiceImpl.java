package pro.appwork.open_university.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pro.appwork.open_university.model.entity.Course;
import pro.appwork.open_university.model.entity.CourseGroup;
import pro.appwork.open_university.repository.CourseGroupRepository;
import pro.appwork.open_university.service.CourseGroupService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CourseGroupServiceImpl implements CourseGroupService {
    private final CourseGroupRepository courseGroupRepository;

    @Override
    public Map<Integer, List<Integer>> getAllByGroupId(Long id) {
        List<CourseGroup> courseGroup = courseGroupRepository.findAllByGroupId(id);
        Set<Course> courseList = courseGroup.stream().map(CourseGroup::getCourse).collect(Collectors.toSet());
        Map<Integer, List<Integer>> map = new HashMap<>();

        for (Course course : courseList) {
            map.put(course.getStage(),
                    courseGroup.stream()
                            .filter(f -> f.getCourse().getId().equals(course.getId()))
                            .map(CourseGroup::getSemester)
                            .distinct()
                            .collect(Collectors.toList())
            );
        }

        return map;
    }
}
