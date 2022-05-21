package pro.appwork.open_university.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import pro.appwork.open_university.model.entity.Group;
import pro.appwork.open_university.model.enums.UserRole;
import pro.appwork.open_university.service.MailService;
import pro.appwork.open_university.service.RegistrationTokenRepository;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@Service
@RequiredArgsConstructor
public class DefaultMailService implements MailService {
    private final JavaMailSender mailSender;
    private final RegistrationTokenRepository tokenService;

    private static final String TEXT = """
            <hr>
            Для продолжения регистрации пройдите по ссылке:
            <br>
            http://localhost:8080/registration/student/%s
            <hr>
            """;
    private static final String SUBJECT = """
            Приглашение в группу ТвГТУ
            """;


    @Value("${spring.mail.username}")
    private String from;

    @Async
    @Override
    public void send(String to, UserRole role, Group group) {
        try {
            MimeMessage mimeMessage = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, "utf-8");

            helper.setText(TEXT.formatted(tokenService.generate(to, role, group).getToken()), true);
            helper.setTo(to);
            helper.setFrom(from);
            helper.setSubject(SUBJECT);

            mailSender.send(mimeMessage);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
}
