package pro.appwork.open_university.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import pro.appwork.open_university.model.entity.CustomUser;
import pro.appwork.open_university.model.entity.Student;
import pro.appwork.open_university.model.entity.Task;
import pro.appwork.open_university.model.entity.Teacher;
import pro.appwork.open_university.security.CustomUserDetails;
import pro.appwork.open_university.security.annotation.IsAny;
import pro.appwork.open_university.security.annotation.IsTeacher;
import pro.appwork.open_university.service.LessonService;
import pro.appwork.open_university.service.SolutionService;
import pro.appwork.open_university.service.TaskService;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

@IsTeacher
@Controller
@RequiredArgsConstructor
@RequestMapping("/tasks")
public class TaskController {
    private final TaskService taskService;
    private final SolutionService solutionService;

    @IsAny
    @GetMapping("/{id}")
    public String getTask(@PathVariable Long id,
                          Authentication authentication,
                          Model model) {

        CustomUser user = ((CustomUserDetails) authentication.getPrincipal()).user();
        if (user instanceof Student student && taskService.taskNotForStudent(id, student)) {
            //Если студент пытается открыть задания не для его группы,
            //то отправляем его на страницу своей группы
            return "redirect:/groups/" + student.getGroup().getId();
        }

        Task task = taskService.getTask(id);

        if (user instanceof Teacher) {
            model.addAttribute("solutionList", solutionService.getAllForTask(task));
        }
        model.addAttribute("task", task);


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

    @PostMapping("/delete/{id}")
    public String delete(HttpServletRequest request,
                         Authentication authentication,
                         @PathVariable Long id) {
        String referer = Optional.of(request.getHeader("Referer"))
                .orElse("/");

        taskService.delete(id);

        return "redirect:" + referer;
    }

    @PostMapping("/uploadFile")
    public String uploadFile(Authentication authentication,
                             @RequestHeader("Referer") String referer,
                             @RequestParam Long taskId,
                             @RequestParam MultipartFile file,
                             RedirectAttributes redirectAttrs) {

        Teacher teacher = (Teacher) ((CustomUserDetails) authentication.getPrincipal()).user();

        taskService.uploadFile(teacher, taskId, file);
        redirectAttrs.addFlashAttribute("message", "Файл успешно загружен!");

        return "redirect:" + referer;
    }

    @IsAny
    @ResponseBody
    @GetMapping("/download/{id}")
    public ResponseEntity<InputStreamResource> downloadFile(@PathVariable Long id,
                                                            Authentication authentication) {

        CustomUser user = ((CustomUserDetails) authentication.getPrincipal()).user();
        if (user instanceof Student student && taskService.taskNotForStudent(id, student)) {
            //Если студент пытается скачать задания не для его группы,
            return ResponseEntity.noContent().build();
        }


        return taskService.downloadFile(id);
    }
}
