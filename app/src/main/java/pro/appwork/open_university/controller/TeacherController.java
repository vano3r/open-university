package pro.appwork.open_university.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import pro.appwork.open_university.service.CourseGroupService;
import pro.appwork.open_university.service.GroupService;

@Controller
@RequiredArgsConstructor
@RequestMapping("/teacher")
@PreAuthorize("hasAnyAuthority('ADMIN', 'TEACHER')")
public class TeacherController {
    private final GroupService groupService;
    private final CourseGroupService courseGroupService;

    @GetMapping("/groups")
    public String viewGroupsPage(Model model) {
        model.addAttribute("groups", groupService.getAll());

        return "groups-page";
    }

    @GetMapping("/groups/{id}")
    public String viewGroupPage(@PathVariable Long id, Model model) {
        model.addAttribute("group", groupService.getById(id));
        model.addAttribute("courses", courseGroupService.getAllByGroupId(id));

        return "group-page";
    }
}
