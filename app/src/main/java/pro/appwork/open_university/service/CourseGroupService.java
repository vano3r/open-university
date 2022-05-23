package pro.appwork.open_university.service;

import pro.appwork.open_university.model.entity.CourseGroup;

import java.util.List;

public interface CourseGroupService {
    List<CourseGroup> getAllByGroupId(Long id);
}
