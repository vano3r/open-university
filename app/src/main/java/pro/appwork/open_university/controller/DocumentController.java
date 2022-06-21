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
import pro.appwork.open_university.service.DocumentService;

@IsTeacher
@Controller
@RequiredArgsConstructor
@RequestMapping("/documents")
public class DocumentController {
    private final DocumentService documentService;

    @GetMapping
    public String viewDocumentPage(Model model,
                                   Authentication authentication,
                                   @RequestParam(required = false) String labelName) {

        Teacher teacher = (Teacher) ((CustomUserDetails) authentication.getPrincipal()).user();

        model.addAttribute("labelList", documentService.getAllLabels(teacher));

        if (labelName != null) {
            model.addAttribute("documentListByLabel", documentService.getAllDocumentByLabel(teacher, labelName));
            model.addAttribute("currentLabelName", labelName);
        }

        return "document-list";
    }

    @PostMapping("/create")
    public String createDocument(Authentication authentication,
                                 @RequestParam String labelName,
                                 @RequestParam MultipartFile file,
                                 @RequestHeader("Referer") String referer) {

        Teacher teacher = (Teacher) ((CustomUserDetails) authentication.getPrincipal()).user();

        documentService.createDocument(teacher, labelName, file);
        return "redirect:" + referer;
    }

    @PostMapping("/delete")
    public String deleteDocument(Authentication authentication,
                                 @RequestParam String labelName,
                                 @RequestParam String documentName,
                                 @RequestHeader("Referer") String referer) {

        Teacher teacher = (Teacher) ((CustomUserDetails) authentication.getPrincipal()).user();

        documentService.deleteDocument(teacher, labelName, documentName);

        return "redirect:" + referer;
    }

    @PostMapping("/labels/create")
    public String createLabel(Authentication authentication,
                              @RequestParam String labelName) {
        Teacher teacher = (Teacher) ((CustomUserDetails) authentication.getPrincipal()).user();

        documentService.createLabel(teacher, labelName);
        return "";
    }

    @PostMapping("/labels/delete")
    public String deleteLabel(Authentication authentication,
                              @RequestParam String labelName) {
        Teacher teacher = (Teacher) ((CustomUserDetails) authentication.getPrincipal()).user();

        documentService.deleteLabel(teacher, labelName);
        return "";
    }
}
