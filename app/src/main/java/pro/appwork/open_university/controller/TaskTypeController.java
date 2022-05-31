package pro.appwork.open_university.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pro.appwork.open_university.service.TaskTypeService;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
@RequestMapping("/taskTypes")
@PreAuthorize("hasAuthority('ADMIN')")
public class TaskTypeController {

    private final TaskTypeService typeService;

    @GetMapping
    public String getAll(Model model) {
        model.addAttribute("typeList", typeService.getAll());

        return "task-type-list";

    }

    @PostMapping("/create")
    public String create(HttpServletRequest request,
                         @RequestParam String name) {

        String referer = Optional.of(request.getHeader("Referer"))
                .orElse("/");

        typeService.createByName(name);

        return "redirect:" + referer;
    }

    @PostMapping("/delete/{id}")
    public String delete(HttpServletRequest request,
                         @PathVariable Long id) {

        String referer = Optional.of(request.getHeader("Referer"))
                .orElse("/");

        typeService.deleteById(id);

        return "redirect:" + referer;
    }

    @PostMapping("/update/{id}")
    public String update(HttpServletRequest request,
                         @PathVariable Long id,
                         @RequestParam String name) {

        String referer = Optional.of(request.getHeader("Referer"))
                .orElse("/");

        typeService.updateById(id, name);

        return "redirect:" + referer;
    }
}
