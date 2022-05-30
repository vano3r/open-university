package pro.appwork.open_university.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pro.appwork.open_university.model.entity.Student;
import pro.appwork.open_university.security.CustomUserDetails;
import pro.appwork.open_university.service.GroupService;
import pro.appwork.open_university.service.LessonService;

@Controller
@RequiredArgsConstructor
@RequestMapping("/student")
public class StudentController {
    private final GroupService groupService;
    private final LessonService lessonService;

    @GetMapping
    public String viewStudentPage(Model model, Authentication authentication) {
        Student student = (Student) ((CustomUserDetails) authentication.getPrincipal()).user();

        model.addAttribute("mapLesson", lessonService.getAllMapBySemester(student.getGroup()));
        model.addAttribute("group", groupService.getById(student.getGroup().getId()));
        return "student-page";
    }

//    @PostMapping("/{studentId}/subjects/{subjectId}/tasks/{taskId}")
//    public String addSolution(@RequestParam MultipartFile file,
//                              @PathVariable Long studentId,
//                              @PathVariable Long subjectId,
//                              @PathVariable Long taskId,
//                              Model model) {
//
//        CustomUser student = userService.getById(studentId);
//
//        Subject subject = student.group().subjects().stream()
//                .filter(sbj -> sbj.getId().equals(subjectId))
//                .findFirst()
//                .orElseThrow();
//
//        Task task = subject.getTasks().stream()
//                .filter(t -> t.getId().equals(taskId))
//                .findFirst()
//                .orElseThrow();
//
//
//        solutionService.createSolution(student, subject, task, file);
//
//        model.addAttribute("student", student);
//        return "student-subjects-page";
//    }
}
