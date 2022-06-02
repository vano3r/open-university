package pro.appwork.open_university.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import pro.appwork.open_university.model.entity.Teacher;
import pro.appwork.open_university.security.CustomUserDetails;
import pro.appwork.open_university.security.annotation.IsTeacher;
import pro.appwork.open_university.service.TaskService;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

@IsTeacher
@Controller
@RequiredArgsConstructor
@RequestMapping("/tasks")
public class TaskController {
    private final TaskService taskService;

    @PostMapping("/create")
    public String create(HttpServletRequest request,
                         Authentication authentication,
                         @RequestParam String taskType,
                         @RequestParam Long lessonId) {

        String referer = Optional.of(request.getHeader("Referer"))
                .orElse("/");
        Teacher teacher = (Teacher) ((CustomUserDetails) authentication.getPrincipal()).user();

        taskService.create(teacher, lessonId, taskType);

        return "redirect:" + referer;
    }

    @PostMapping("/uploadFile")
    public String uploadFile(HttpServletRequest request,
                             Authentication authentication,
                             @RequestParam Long taskId,
                             @RequestParam("file") MultipartFile file) {

        String referer = Optional.of(request.getHeader("Referer"))
                .orElse("/");
        Teacher teacher = (Teacher) ((CustomUserDetails) authentication.getPrincipal()).user();

        taskService.uploadFile(teacher, taskId, file);

        return "redirect:" + referer;
    }
}
