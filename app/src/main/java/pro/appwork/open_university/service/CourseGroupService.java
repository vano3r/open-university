package pro.appwork.open_university.service;

import java.util.List;
import java.util.Map;

public interface CourseGroupService {
    Map<Integer, List<Integer>> getAllByGroupId(Long id);
}
