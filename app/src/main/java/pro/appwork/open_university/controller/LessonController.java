package pro.appwork.open_university.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pro.appwork.open_university.model.entity.Teacher;
import pro.appwork.open_university.security.CustomUserDetails;
import pro.appwork.open_university.security.annotation.IsTeacher;
import pro.appwork.open_university.service.LessonService;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

@IsTeacher
@Controller
@RequiredArgsConstructor
@RequestMapping("/lessons")
public class LessonController {
    private final LessonService lessonService;

    @PostMapping("/create")
    public String create(HttpServletRequest request,
                         Authentication authentication,
                         @RequestParam String name) {

        String referer = Optional.of(request.getHeader("Referer"))
                .orElse("/");
        Teacher teacher = (Teacher) ((CustomUserDetails) authentication.getPrincipal()).user();

        lessonService.createByName(name, teacher);

        return "redirect:" + referer;
    }

    @PostMapping("/delete/{id}")
    public String delete(HttpServletRequest request,
                         Authentication authentication,
                         @PathVariable Long id) {

        String referer = Optional.of(request.getHeader("Referer"))
                .orElse("/");
        Teacher teacher = (Teacher) ((CustomUserDetails) authentication.getPrincipal()).user();

        lessonService.deleteById(id, teacher);

        return "redirect:" + referer;
    }

    @PostMapping("/update/{id}")
    public String update(HttpServletRequest request,
                         Authentication authentication,
                         @PathVariable Long id,
                         @RequestParam String name) {

        String referer = Optional.of(request.getHeader("Referer"))
                .orElse("/");
        Teacher teacher = (Teacher) ((CustomUserDetails) authentication.getPrincipal()).user();

        lessonService.updateById(id, teacher, name);

        return "redirect:" + referer;
    }
}
