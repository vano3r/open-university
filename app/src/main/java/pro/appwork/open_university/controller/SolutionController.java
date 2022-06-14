package pro.appwork.open_university.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import pro.appwork.open_university.security.annotation.IsTeacher;

@IsTeacher
@Controller
@RequiredArgsConstructor
@RequestMapping("/solutions")
public class SolutionController {
}
