package pro.appwork.open_university.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pro.appwork.open_university.model.entity.CustomUser;
import pro.appwork.open_university.model.entity.Group;
import pro.appwork.open_university.model.entity.Student;
import pro.appwork.open_university.model.entity.Teacher;
import pro.appwork.open_university.model.enums.UserRole;
import pro.appwork.open_university.security.CustomUserDetails;
import pro.appwork.open_university.service.GroupService;
import pro.appwork.open_university.service.LessonService;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
@RequestMapping("/groups")
@PreAuthorize("hasAuthority('ADMIN')")
public class GroupController {
    private final GroupService groupService;
    private final LessonService lessonService;

    @GetMapping
    @PreAuthorize("hasAnyAuthority('TEACHER', 'ADMIN')")
    public String getAll(Model model) {
        model.addAttribute("groupList", groupService.getAll());

        return "group-list";

    }

    @GetMapping("/{id}")
    @PreAuthorize("permitAll()")
    public String getById(Model model,
                          Authentication authentication,
                          @PathVariable Long id) {

        CustomUser user = ((CustomUserDetails) authentication.getPrincipal()).user();
        if (user instanceof Student student) {
            //Если студент пытается открыть чужую группу,
            //то отправляем его на свою
            if (!student.getGroup().getId().equals(id)) {
                return "redirect:/groups/" + student.getGroup().getId();
            }

            model.addAttribute("group", groupService.getById(student.getGroup().getId()));
            model.addAttribute("lessonMap", lessonService.getAllMapBySemester(student.getGroup()));

            return "group";
        }

        Teacher teacher = (Teacher) user;
        Group group = groupService.getById(id);
        model.addAttribute("group", group);

        if (teacher.getRole().equals(UserRole.ADMIN)) {
            model.addAttribute("lessonMap", lessonService.getAllMapBySemester(group));
            return "group";
        }

        model.addAttribute("lessonMap", lessonService.getAllMapBySemester(group, teacher));

        return "group";
    }

    @PostMapping("/create")
    public String create(HttpServletRequest request,
                         @RequestParam String name) {

        String referer = Optional.of(request.getHeader("Referer"))
                .orElse("/");

        groupService.createByName(name);

        return "redirect:" + referer;
    }

    @PostMapping("/delete/{id}")
    public String delete(HttpServletRequest request,
                         @PathVariable Long id) {

        String referer = Optional.of(request.getHeader("Referer"))
                .orElse("/");

        groupService.deleteById(id);

        return "redirect:" + referer;
    }

    @PostMapping("/update/{id}")
    public String update(HttpServletRequest request,
                         @PathVariable Long id,
                         @RequestParam String name) {

        String referer = Optional.of(request.getHeader("Referer"))
                .orElse("/");

        groupService.updateById(id, name);

        return "redirect:" + referer;
    }
}
