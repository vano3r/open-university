package pro.appwork.open_university.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
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

    @GetMapping("/{id}")
    public String getTask(@PathVariable Long id, Model model) {
        model.addAttribute("task", taskService.getTask(id));
        return "task";
    }

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
    public String uploadFile(Authentication authentication,
                             @RequestHeader("Referer") String referer,
                             @RequestParam Long taskId,
                             @RequestParam MultipartFile file) {

        Teacher teacher = (Teacher) ((CustomUserDetails) authentication.getPrincipal()).user();

        taskService.uploadFile(teacher, taskId, file);

        return "redirect:" + referer;
    }
}
