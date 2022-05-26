package pro.appwork.open_university.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pro.appwork.open_university.model.entity.Lesson;
import pro.appwork.open_university.service.CourseGroupService;
import pro.appwork.open_university.service.GroupService;
import pro.appwork.open_university.service.LessonService;

import java.util.List;
import java.util.Map;

@Controller
@RequiredArgsConstructor
@RequestMapping("/student")
public class StudentController {
    private final GroupService groupService;
    private final CourseGroupService courseGroupService;
    private final LessonService lessonService;

    @GetMapping
    public String viewStudentPage(Model model) {
        Map<Integer, List<Integer>> listMap = courseGroupService.getAllByGroupId(1L);
        listMap.keySet().forEach(
                s -> listMap.get(s).forEach(System.out::println)
        );
        List<Lesson> lessons = lessonService.getAllByCourseGroupId(1L);

        model.addAttribute("courses", listMap);
        model.addAttribute("lessons", lessons);
        model.addAttribute("group", groupService.getById(1L));
        return "student-page";
    }
}
