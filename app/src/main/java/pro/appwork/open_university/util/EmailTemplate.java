package pro.appwork.open_university.util;

public class EmailTemplate {
    public static final String EMAIL_SUBJECT_INVITE = "Приглашение в ТвГТУ";
    public static final String EMAIL_TEXT_INVITE = """
            <h2>Приглашение в приложение ТвГТУ</h2>
            <p>Для продолжения регистрации перейдите по <a href='http://%s/registration/%s'>ссылке</a></p>
            """;
}
