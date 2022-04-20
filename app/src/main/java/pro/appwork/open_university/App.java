package pro.appwork.open_university;


import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;
import pro.appwork.open_university.model.entity.CustomUser;
import pro.appwork.open_university.model.enums.UserRole;
import pro.appwork.open_university.model.enums.UserState;
import pro.appwork.open_university.repository.UserRepository;

@SpringBootApplication
public class App {
    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }

    @Bean
    public CommandLineRunner CommandLineRunnerBean(UserRepository repository, PasswordEncoder passwordEncoder) {
        return (args) -> {
            CustomUser user = new CustomUser(null, "Иван", "Воробьёв", "Вячеславович", "ivan@mail.com", passwordEncoder.encode("pass"), UserRole.STUDENT, UserState.ACTIVE, null, null);
            repository.save(user);
        };
    }
}
