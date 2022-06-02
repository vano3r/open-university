# Open University
Проект для управления и хранения заданий для студентов, а также их решений по предметам

### Технологии в проекте

- Java 17
- Spring Boot
- Spring MVC
- Spring Security
- Spring Data JPA
- Spring Mail
- Thymeleaf
- Liquibase
- Lombok
- PostgreSQL

## Локальное развертывание проекта

Для локального запуска проекта необходимы база PostgreSQL и SMTP сервер для отправки почты.
Удобнее всего запустить все в Docker через [docker-compose.yaml](https://github.com/vano3r/open-university/blob/develop/docker-compose.yaml)

## Развертывание на сервере

1. Скачать нужный релиз [open-university.jar](https://github.com/vano3r/open-university/releases)
2. Скачать [JRE 17](https://bell-sw.com/pages/downloads/#/java-17-lts) для запуска проекта
3. Рядом с open-university.jar положить файл application.properties, минимальный конфиг для запуска:
```properties
spring.datasource.url={POSTGRES_DB}
spring.datasource.username={POSTGRES_USER}
spring.datasource.password={POSTGRES_PASSWORD}

#Пример для подключения почты Яндекс
spring.mail.host=smtp.yandex.ru
spring.mail.port=465
spring.mail.username={EMAIL}
spring.mail.password={PASSWORD}
spring.mail.properties.mail.smtp.ssl.enable=true
```
4. Запуск сервера производится командой:
```console
java -jar open-university.jar
```
___
#### Вопросы по проекту присылать на почту <ivan.vorobiev@java-spring.ru>
