package pro.appwork.open_university.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pro.appwork.open_university.model.entity.CustomUser;
import pro.appwork.open_university.model.entity.Group;
import pro.appwork.open_university.model.entity.Student;
import pro.appwork.open_university.model.entity.Teacher;
import pro.appwork.open_university.model.enums.AcademicDegreeEnum;
import pro.appwork.open_university.model.enums.GroupStatusEnum;
import pro.appwork.open_university.security.CustomUserDetails;
import pro.appwork.open_university.security.annotation.IsAdmin;
import pro.appwork.open_university.security.annotation.IsAny;
import pro.appwork.open_university.security.annotation.IsTeacher;
import pro.appwork.open_university.service.GroupService;
import pro.appwork.open_university.service.LessonService;
import pro.appwork.open_university.service.TaskTypeService;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

@IsAdmin
@Controller
@RequiredArgsConstructor
@RequestMapping("/groups")
public class GroupController {
    private final GroupService groupService;
    private final LessonService lessonService;
    private final TaskTypeService typeService;

    @IsTeacher
    @GetMapping
    public String getAll(Model model) {
        model.addAttribute("groupList", groupService.getAll());

        return "group-list";
    }

    @IsAny
    @GetMapping("/{id}")
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
        model.addAttribute("taskTypeList", typeService.getAll());
        model.addAttribute("lessonMap", lessonService.getAllMapBySemester(group, teacher));

        return "group";
    }

    @PostMapping("/create")
    public String create(HttpServletRequest request,
                         @RequestParam String name,
                         @RequestParam Integer year,
                         @RequestParam AcademicDegreeEnum degree) {

        String referer = Optional.of(request.getHeader("Referer"))
                .orElse("/");

        groupService.create(name, year, degree);

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
                         @RequestParam String name,
                         @RequestParam Integer year,
                         @RequestParam AcademicDegreeEnum degree,
                         @RequestParam GroupStatusEnum status) {

        String referer = Optional.of(request.getHeader("Referer"))
                .orElse("/");

        groupService.updateById(id, name, year, degree, status);

        return "redirect:" + referer;
    }
}
