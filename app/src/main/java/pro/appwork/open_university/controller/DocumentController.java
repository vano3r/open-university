package pro.appwork.open_university.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import pro.appwork.open_university.model.entity.Teacher;
import pro.appwork.open_university.model.enums.DocumentTypeEnum;
import pro.appwork.open_university.security.CustomUserDetails;
import pro.appwork.open_university.security.annotation.IsTeacher;
import pro.appwork.open_university.service.DocumentService;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

@IsTeacher
@Controller
@RequiredArgsConstructor
@RequestMapping("/documents")
public class DocumentController {
    private final DocumentService documentService;

    @GetMapping
    public String viewDocumentPage(Model model,
                                   Authentication authentication,
                                   @RequestParam String type) {

        Teacher teacher = (Teacher) ((CustomUserDetails) authentication.getPrincipal()).user();

        model.addAttribute("documentList",
                documentService.getAllByTeacherAndType(teacher, DocumentTypeEnum.lowerCaseOf(type)));
        model.addAttribute("type", DocumentTypeEnum.lowerCaseOf(type));

        return "document-list";
    }

    @PostMapping("/upload")
    public String uploadFile(Authentication authentication,
                             HttpServletRequest request,
                             @RequestParam MultipartFile file,
                             @RequestParam String type) {

        String referer = Optional.of(request.getHeader("Referer"))
                .orElse("/");
        Teacher teacher = (Teacher) ((CustomUserDetails) authentication.getPrincipal()).user();

        documentService.uploadFile(teacher, file, DocumentTypeEnum.lowerCaseOf(type));

        return "redirect:" + referer;
    }

    @ResponseBody
    @GetMapping("/download/{id}")
    public ResponseEntity<InputStreamResource> downloadFile(Authentication authentication,
                                                            @PathVariable Long id) {

        Teacher teacher = (Teacher) ((CustomUserDetails) authentication.getPrincipal()).user();

        return documentService.downloadFile(teacher, id);
    }

    @PostMapping("/delete/{id}")
    public void delete(Authentication authentication,
                       @PathVariable Long id) {

        Teacher teacher = (Teacher) ((CustomUserDetails) authentication.getPrincipal()).user();
        documentService.deleteById(teacher, id);
    }
}
