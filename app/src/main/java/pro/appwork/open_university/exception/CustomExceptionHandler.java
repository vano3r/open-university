package pro.appwork.open_university.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@ControllerAdvice
public class CustomExceptionHandler {

    @Value("${spring.servlet.multipart.max-file-size}")
    private String maxFileSize;

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String defaultErrorHandler(Model model, Exception e) {
        log.error("Ошибка", e);
        model.addAttribute("error", e.getMessage());

        return "error-page";
    }

    @ExceptionHandler(MaxUploadSizeExceededException.class)
    public String maxFileSizeErrorHandler(RedirectAttributes redirectAttributes,
                                          HttpServletRequest request) {

        redirectAttributes.addFlashAttribute("error",
                "Файл не может быть больше " + maxFileSize + "!");

        return "redirect:" + request.getHeader("Referer");
    }
}
