package pro.appwork.open_university.util;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import pro.appwork.open_university.model.entity.*;
import pro.appwork.open_university.model.enums.RoleEnum;
import pro.appwork.open_university.model.enums.Semester;
import pro.appwork.open_university.model.enums.UserState;
import pro.appwork.open_university.repository.*;

import java.util.List;

@Slf4j
@Component
@Profile("dev")
@RequiredArgsConstructor
public class DatabaseInitializer implements ApplicationRunner {
    private final PasswordEncoder passwordEncoder;
    private final StudentRepository studentRepository;
    private final GroupRepository groupRepository;
    private final TeacherRepository teacherRepository;
    private final LessonRepository lessonRepository;
    private final TaskTypeRepository taskTypeRepository;
    private final TaskRepository taskRepository;
    private final SolutionRepository solutionRepository;
    private final RoleRepository roleRepository;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        log.info("Starting initialize database");
        Group group1 = Group.builder().name("ИВТ-1504").build();
        Group group2 = Group.builder().name("М.ПИН.РИС-1904").build();

        groupRepository.saveAll(List.of(group1, group2));

        Role studentRole = Role.builder().name(RoleEnum.STUDENT).build();
        Role teacherRole = Role.builder().name(RoleEnum.TEACHER).build();
        Role adminRole = Role.builder().name(RoleEnum.ADMIN).build();

        roleRepository.saveAll(List.of(studentRole, teacherRole, adminRole));

        Teacher teacher = Teacher.builder()
                .firstName("Сергей")
                .lastName("Кузнецов")
                .middleName("Евгеньевич")
                .email("teacher@mail.ru")
                .password(passwordEncoder.encode("teacher"))
                .role(teacherRole)
                .state(UserState.ACTIVE)
                .build();

        Teacher admin = Teacher.builder()
                .firstName("Алексей")
                .lastName("Громов")
                .middleName("Евгеньевич")
                .email("admin@mail.ru")
                .password(passwordEncoder.encode("admin"))
                .role(teacherRole)
                .role(adminRole)
                .state(UserState.ACTIVE)
                .build();

        teacherRepository.saveAll(List.of(teacher, admin));

        Lesson lesson1 = Lesson.builder().semester(Semester.FIRST).group(group1).name("ИАТ").teacher(teacher).build();
        Lesson lesson2 = Lesson.builder().semester(Semester.SECOND).group(group1).name("ИИС").teacher(teacher).build();
        Lesson lesson3 = Lesson.builder().semester(Semester.FIRST).group(group2).name("ТПР").teacher(teacher).build();

        lessonRepository.saveAll(List.of(lesson1, lesson2, lesson3));


        TaskType type1 = TaskType.builder().name("ВКР").build();
        TaskType type2 = TaskType.builder().name("Лабораторная работа").build();
        TaskType type3 = TaskType.builder().name("КР").build();

        taskTypeRepository.saveAll(List.of(type1, type2, type3));

        Task task1 = Task.builder().type(type1).lesson(lesson1).filePath("file").build();
        Task task2 = Task.builder().type(type2).lesson(lesson1).filePath("file").build();
        Task task3 = Task.builder().type(type3).lesson(lesson1).filePath("file").build();
        Task task4 = Task.builder().type(type2).lesson(lesson2).filePath("file").build();
        Task task5 = Task.builder().type(type3).lesson(lesson3).filePath("file").build();
        Task task6 = Task.builder().type(type2).lesson(lesson3).filePath("file").build();

        taskRepository.saveAll(
                List.of(task1, task2, task3, task4, task5, task6)
        );

        Student student1 = Student.builder()
                .firstName("Иван")
                .lastName("Иванов")
                .middleName("Иванович")
                .email("student1@mail.ru")
                .group(group1)
                .password(passwordEncoder.encode("student"))
                .role(studentRole)
                .state(UserState.ACTIVE)
                .build();

        Student student2 = Student.builder()
                .firstName("Степан")
                .lastName("Звонарь")
                .email("student2@mail.ru")
                .group(group1)
                .password(passwordEncoder.encode("student"))
                .role(studentRole)
                .state(UserState.ACTIVE)
                .build();

        Student student3 = Student.builder()
                .firstName("Михаил")
                .lastName("Шурупов")
                .middleName("Алексеевич")
                .email("student3@mail.ru")
                .group(group2)
                .password(passwordEncoder.encode("student"))
                .role(studentRole)
                .state(UserState.ACTIVE)
                .build();

        studentRepository.saveAll(List.of(student1, student2, student3));


        Solution solution1 = Solution.builder().task(task1).student(student1).filePath("file").version(1).build();
        Solution solution2 = Solution.builder().task(task2).student(student1).filePath("file").version(1).build();
        Solution solution3 = Solution.builder().task(task3).student(student2).filePath("file").version(1).build();
        Solution solution4 = Solution.builder().task(task3).student(student3).filePath("file").version(1).build();
        Solution solution5 = Solution.builder().task(task3).student(student3).filePath("file").version(2).build();

        solutionRepository.saveAll(List.of(solution1, solution2, solution3, solution4, solution5));

        log.info("Success initialize database");
    }
}
