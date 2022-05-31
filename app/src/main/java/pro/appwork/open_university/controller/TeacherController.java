package pro.appwork.open_university.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import pro.appwork.open_university.model.entity.Group;
import pro.appwork.open_university.model.entity.Teacher;
import pro.appwork.open_university.security.CustomUserDetails;
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
        return "learning-groups-page";
    }

    @GetMapping("/groups/{id}")
    public String viewGroupPage(@PathVariable Long id, Model model, Authentication authentication) {
        Teacher teacher = (Teacher) ((CustomUserDetails) authentication.getPrincipal()).user();
        Group group = groupService.getById(id);

        model.addAttribute("group", group);
        model.addAttribute("lessons", lessonService.getAllMapBySemester(group, teacher));
        return "learning-page";
    }
}
