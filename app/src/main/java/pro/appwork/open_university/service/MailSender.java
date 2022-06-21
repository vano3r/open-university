package pro.appwork.open_university.service;

public interface MailSender {
    /**
     * Метод отправки сообщения
     *
     * @param to      получатель
     * @param subject тема письма
     * @param text    текст письма
     */
    void send(String to, String subject, String text);
}
