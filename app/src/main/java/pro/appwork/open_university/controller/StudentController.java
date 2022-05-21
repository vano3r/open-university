package pro.appwork.open_university.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import pro.appwork.open_university.model.entity.CustomUser;
import pro.appwork.open_university.model.entity.Task;
import pro.appwork.open_university.service.GroupService;
import pro.appwork.open_university.service.MailService;
import pro.appwork.open_university.service.SolutionService;
import pro.appwork.open_university.service.UserService;

@Controller
@RequiredArgsConstructor
@RequestMapping("/students")
public class StudentController {
    private final MailService mailService;
    private final GroupService groupService;
    private final UserService userService;
    private final SolutionService solutionService;
//    private final SubjectService subjectService;

    @GetMapping()
    public String viewAddNewStudentPage(Model model) {
        model.addAttribute("groups", groupService.getAll());
        return "add-new-student-page";
    }

    @PostMapping
    public String addNewStudent(@RequestParam String email, @RequestParam Long groupId, Model model) {
        mailService.send(email, null,null);
        model.addAttribute("groups", groupService.getAll());
        return "add-new-student-page";
    }

    @GetMapping("/{studentId}/subjects")
    public String viewStudentSubjectPage(@PathVariable Long studentId, Model model) {
        CustomUser student = userService.getById(studentId);
        model.addAttribute("student", student);

        return "student-subjects-page";
    }

    @PostMapping("/{studentId}/subjects/{subjectId}/tasks/{taskId}")
    public String addSolution(@RequestParam MultipartFile file,
                              @PathVariable Long studentId,
                              @PathVariable Long subjectId,
                              @PathVariable Long taskId,
                              Model model) {

        CustomUser student = userService.getById(studentId);

//        Subject subject = student.group().subjects().stream()
//                .filter(sbj -> sbj.getId().equals(subjectId))
//                .findFirst()
//                .orElseThrow();
//
//        Task task = subject.getTasks().stream()
//                .filter(t -> t.getId().equals(taskId))
//                .findFirst()
//                .orElseThrow();


//        solutionService.createSolution(student, subject, task, file);

        model.addAttribute("student", student);
        return "student-subjects-page";
    }
}
