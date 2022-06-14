package pro.appwork.open_university.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import pro.appwork.open_university.model.entity.Student;
import pro.appwork.open_university.security.CustomUserDetails;
import pro.appwork.open_university.security.annotation.IsStudent;
import pro.appwork.open_university.security.annotation.IsTeacher;
import pro.appwork.open_university.service.SolutionService;

@Controller
@RequiredArgsConstructor
@RequestMapping("/solutions")
public class SolutionController {
    private final SolutionService solutionService;

    @IsStudent
    @PostMapping("/uploadFile")
    public String uploadFile(Authentication authentication,
                             @RequestHeader("Referer") String referer,
                             @RequestParam Long taskId,
                             @RequestParam MultipartFile file,
                             RedirectAttributes redirectAttrs) {

        Student student = (Student) ((CustomUserDetails) authentication.getPrincipal()).user();
        solutionService.uploadFile(student, taskId, file);

        redirectAttrs.addFlashAttribute("message", "Файл успешно загружен!");

        return "redirect:" + referer;
    }

    @IsTeacher
    @ResponseBody
    @GetMapping("/download/{id}")
    public ResponseEntity<InputStreamResource> downloadFile(@PathVariable Long id) {
        return solutionService.downloadFile(id);
    }
}
