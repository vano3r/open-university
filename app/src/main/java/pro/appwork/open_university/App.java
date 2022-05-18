package pro.appwork.open_university;


import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;
import pro.appwork.open_university.model.entity.CustomUser;
import pro.appwork.open_university.model.entity.Group;
import pro.appwork.open_university.model.enums.UserRole;
import pro.appwork.open_university.model.enums.UserState;
import pro.appwork.open_university.repository.GroupRepository;
import pro.appwork.open_university.repository.UserRepository;

@SpringBootApplication
public class App {
    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }

    @Bean
    public CommandLineRunner CommandLineRunnerBean(UserRepository userRepository, PasswordEncoder passwordEncoder,
                                                   GroupRepository groupRepository) {
        return (args) -> {
            CustomUser student1 = new CustomUser();
            CustomUser student2 = new CustomUser();
            CustomUser student3 = new CustomUser();

            CustomUser teacher = new CustomUser();

            Group group1 = new Group();
            Group group2 = new Group();
            Group group3 = new Group();
            Group group4 = new Group();

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

            group1
                    .name("ИВТ-15.04")
                    .stage(1);

            group2
                    .name("М.ПИН.РИС-19.04")
                    .stage(2);

            group3
                    .name("ИСТ-18.06")
                    .stage(2);

            group4
                    .name("ВТА-17.99")
                    .stage(3);

            userRepository.save(student1);
            userRepository.save(student2);
            userRepository.save(student3);

            userRepository.save(teacher);

            groupRepository.save(group1);
            groupRepository.save(group2);
            groupRepository.save(group3);
            groupRepository.save(group4);
        };
    }
}
