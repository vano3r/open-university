package pro.appwork.open_university.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import pro.appwork.open_university.service.GroupService;
import pro.appwork.open_university.service.LessonService;

@Controller
@RequiredArgsConstructor
@RequestMapping("/teacher")
@PreAuthorize("hasAnyAuthority('ADMIN', 'TEACHER')")
public class TeacherController {
    private final GroupService groupService;
    private final LessonService lessonService;

    @GetMapping("/groups")
    public String viewGroupsPage(Model model) {
        model.addAttribute("groups", groupService.getAll());

        return "groups-page";
    }

    @GetMapping("/groups/{id}")
    public String viewGroupPage(@PathVariable Long id, Model model) {

//        List<CourseGroup> courseGroupList = courseGroupService.getAllByGroupId(id);
//        Map<Integer, List<Integer>> listMap = courseGroupService.getAllByGroupId(id);
//
//        listMap.keySet().forEach(
//                s -> listMap.get(s).forEach(System.out::println)
//        );
//        List<Lesson> lessons = lessonService.getAllByCourseGroupId(id);

//        model.addAttribute("courses", listMap);
//        model.addAttribute("lessons", lessons);
        model.addAttribute("group", groupService.getById(id));

        return "group-page";
    }
}
