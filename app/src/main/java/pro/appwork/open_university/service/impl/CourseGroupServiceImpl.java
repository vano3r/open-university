package pro.appwork.open_university.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pro.appwork.open_university.model.entity.CourseGroup;
import pro.appwork.open_university.repository.CourseGroupRepository;
import pro.appwork.open_university.service.CourseGroupService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CourseGroupServiceImpl implements CourseGroupService {
    private final CourseGroupRepository courseGroupRepository;

    @Override
    public List<CourseGroup> getAllByGroupId(Long id) {
        return courseGroupRepository.findAllByGroupId(id);
    }
}
