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
            CustomUser student1 = new CustomUser();
            CustomUser student2 = new CustomUser();
            CustomUser student3 = new CustomUser();

            CustomUser teacher = new CustomUser();

            student1
                    .firstName("Иван")
                    .lastName("Иванов")
                    .middleName("Иванович")
                    .email("student1@mail.ru")
                    .password(passwordEncoder.encode("student"))
                    .role(UserRole.STUDENT)
                    .state(UserState.ACTIVE);

            student2
                    .firstName("Сергей")
                    .lastName("Кузнецов")
                    .email("student2@mail.ru")
                    .password(passwordEncoder.encode("student"))
                    .role(UserRole.STUDENT)
                    .state(UserState.ACTIVE);

            student3
                    .firstName("Максим")
                    .lastName("Седов")
                    .middleName("Олегович")
                    .email("student3@mail.ru")
                    .password(passwordEncoder.encode("student"))
                    .role(UserRole.STUDENT)
                    .state(UserState.ACTIVE);

            teacher
                    .firstName("Александр")
                    .lastName("Орлов")
                    .middleName("Николаевич")
                    .email("teacher@mail.ru")
                    .password(passwordEncoder.encode("teacher"))
                    .role(UserRole.TEACHER)
                    .state(UserState.ACTIVE);

            repository.save(student1);
            repository.save(student2);
            repository.save(student3);
            repository.save(teacher);
        };
    }
}
