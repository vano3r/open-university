package pro.appwork.open_university;


import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;
import pro.appwork.open_university.model.entity.Teacher;
import pro.appwork.open_university.model.enums.UserRole;
import pro.appwork.open_university.model.enums.UserState;
import pro.appwork.open_university.repository.*;
import pro.appwork.open_university.service.impl.DefaultEmailTokenService;

@SpringBootApplication
public class App {
    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }

    @Bean
    public CommandLineRunner CommandLineRunnerBean(UserRepository userRepository, PasswordEncoder passwordEncoder,
                                                   GroupRepository groupRepository,
                                                   LessonRepository subjectRepository,
                                                   TaskRepository taskRepository,
                                                   DefaultEmailTokenService tokenService,
                                                   TeacherRepository teacherRepository) {
        return (args) -> {
//            CustomUser student1 = new CustomUser();
//            CustomUser student2 = new CustomUser();
//            CustomUser student3 = new CustomUser();
//            CustomUser student4 = new CustomUser();
//
//            CustomUser teacher = new CustomUser();
//
//            Group group1 = new Group();
//            Group group2 = new Group();
//            Group group3 = new Group();
//            Group group4 = new Group();
//
//            Task task1 = Task.builder()
//                    .name("ВКР")
//                    .build();
//            Task task2 = Task.builder()
//                    .name("КР")
//                    .build();
//            Task task3 = Task.builder()
//                    .name("НИР")
//                    .build();
//
//            taskRepository.save(task1);
//            taskRepository.save(task2);
//            taskRepository.save(task3);
//
//            Subject subject1 = new Subject();
//            subject1.setName("ИИС");
//            Subject subject2 = new Subject();
//            subject2.setName("ИАТ");
//            subject1.setTasks(List.of(task1, task2));
//            subject2.setTasks(List.of(task3));
//
//
//            group1
//                    .name("ИВТ-15.04")
//                    .subjects(List.of(subject1, subject2))
//                    .stage(1);
//
//            group2
//                    .name("М.ПИН.РИС-19.04")
//                    .stage(2);
//
//            group3
//                    .name("ИСТ-18.06")
//                    .stage(2);
//
//            group4
//                    .name("ВТА-17.99")
//                    .stage(3);
//
//            student1
//                    .firstName("Иван")
//                    .lastName("Иванов")
//                    .middleName("Иванович")
//                    .email("student1@mail.ru")
//                    .group(group1)
//                    .password(passwordEncoder.encode("student"))
//                    .role(UserRole.STUDENT)
//                    .state(UserState.ACTIVE);
//
//            student2
//                    .firstName("Сергей")
//                    .lastName("Кузнецов")
//                    .email("student2@mail.ru")
//                    .password(passwordEncoder.encode("student"))
//                    .role(UserRole.STUDENT)
//                    .state(UserState.ACTIVE);
//
//            student3
//                    .firstName("Максим")
//                    .lastName("Седов")
//                    .middleName("Олегович")
//                    .email("student3@mail.ru")
//                    .password(passwordEncoder.encode("student"))
//                    .role(UserRole.STUDENT)
//                    .state(UserState.ACTIVE);
//
//            student4
//                    .firstName("Васек")
//                    .lastName("Пупкин")
//                    .middleName("Попович")
//                    .email("vasya.pupkin@test.ru")
//                    .password(passwordEncoder.encode("123"))
//                    .role(UserRole.STUDENT)
//                    .state(UserState.ACTIVE);
//
//            teacher
//                    .firstName("Александр")
//                    .lastName("Орлов")
//                    .middleName("Николаевич")
//                    .email("teacher@mail.ru")
//                    .password(passwordEncoder.encode("teacher"))
//                    .role(UserRole.TEACHER)
//                    .state(UserState.ACTIVE);
//
//            subjectRepository.save(subject1);
//            subjectRepository.save(subject2);
//
//            groupRepository.save(group1);
//            groupRepository.save(group2);
//            groupRepository.save(group3);
//            groupRepository.save(group4);
//
//            userRepository.save(student1);
//            userRepository.save(student2);
//            userRepository.save(student3);
//            userRepository.save(student4);
//
//            userRepository.save(teacher);
            tokenService.generate("test@mail.ru", UserRole.STUDENT, null);
            Teacher teacher = Teacher.builder()
                    .firstName("Иван")
                    .lastName("Иванов")
                    .middleName("Иванович")
                    .email("teacher@mail.ru")
                    .password(passwordEncoder.encode("12345"))
                    .role(UserRole.TEACHER)
                    .state(UserState.ACTIVE)
                    .build();

            teacherRepository.save(teacher);
        };
    }
}
