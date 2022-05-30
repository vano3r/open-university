package pro.appwork.open_university.util;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import pro.appwork.open_university.model.entity.*;
import pro.appwork.open_university.model.enums.Semester;
import pro.appwork.open_university.model.enums.UserRole;
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
    private final TaskRepository taskRepository;
    private final TaskLessonRepository taskLessonRepository;
    private final SolutionRepository solutionRepository;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        log.info("Starting initialize database");
        Group group1 = Group.builder().name("ИВТ-1504").build();
        Group group2 = Group.builder().name("М.ПИН.РИС-1904").build();

        groupRepository.saveAll(List.of(group1, group2));

        Teacher teacher = Teacher.builder()
                .firstName("Сергей")
                .lastName("Кузнецов")
                .middleName("Евгеньевич")
                .email("teacher@mail.ru")
                .password(passwordEncoder.encode("teacher"))
                .role(UserRole.TEACHER)
                .state(UserState.ACTIVE)
                .build();

        Teacher admin = Teacher.builder()
                .firstName("Алексей")
                .lastName("Громов")
                .middleName("Евгеньевич")
                .email("admin@mail.ru")
                .password(passwordEncoder.encode("admin"))
                .role(UserRole.ADMIN)
                .state(UserState.ACTIVE)
                .build();

        teacherRepository.saveAll(List.of(teacher, admin));

        Lesson lesson1 = Lesson.builder().semester(Semester.FIRST).group(group1).name("ИАТ").teacher(teacher).build();
        Lesson lesson2 = Lesson.builder().semester(Semester.SECOND).group(group1).name("ИИС").teacher(teacher).build();
        Lesson lesson3 = Lesson.builder().semester(Semester.FIRST).group(group2).name("ТПР").teacher(teacher).build();

        lessonRepository.saveAll(List.of(lesson1, lesson2, lesson3));


        Task task1 = Task.builder().name("ВКР").build();
        Task task2 = Task.builder().name("Лабораторная работа").build();
        Task task3 = Task.builder().name("КР").build();

        taskRepository.saveAll(List.of(task1, task2, task3));

        TaskLesson taskLesson1 = TaskLesson.builder().task(task1).lesson(lesson1).filePath("file").build();
        TaskLesson taskLesson2 = TaskLesson.builder().task(task2).lesson(lesson1).filePath("file").build();
        TaskLesson taskLesson3 = TaskLesson.builder().task(task3).lesson(lesson1).filePath("file").build();
        TaskLesson taskLesson4 = TaskLesson.builder().task(task2).lesson(lesson2).filePath("file").build();
        TaskLesson taskLesson5 = TaskLesson.builder().task(task3).lesson(lesson3).filePath("file").build();
        TaskLesson taskLesson6 = TaskLesson.builder().task(task2).lesson(lesson3).filePath("file").build();

        taskLessonRepository.saveAll(
                List.of(taskLesson1, taskLesson2, taskLesson3, taskLesson4, taskLesson5, taskLesson6)
        );

        Student student1 = Student.builder()
                .firstName("Иван")
                .lastName("Иванов")
                .middleName("Иванович")
                .email("student1@mail.ru")
                .group(group1)
                .password(passwordEncoder.encode("student"))
                .role(UserRole.STUDENT)
                .state(UserState.ACTIVE)
                .build();

        Student student2 = Student.builder()
                .firstName("Степан")
                .lastName("Звонарь")
                .email("student2@mail.ru")
                .group(group1)
                .password(passwordEncoder.encode("student"))
                .role(UserRole.STUDENT)
                .state(UserState.ACTIVE)
                .build();

        Student student3 = Student.builder()
                .firstName("Михаил")
                .lastName("Шурупов")
                .middleName("Алексеевич")
                .email("student3@mail.ru")
                .group(group2)
                .password(passwordEncoder.encode("student"))
                .role(UserRole.STUDENT)
                .state(UserState.ACTIVE)
                .build();

        studentRepository.saveAll(List.of(student1, student2, student3));


        Solution solution1 = Solution.builder().task(taskLesson1).student(student1).filePath("file").version(1).build();
        Solution solution2 = Solution.builder().task(taskLesson2).student(student1).filePath("file").version(1).build();
        Solution solution3 = Solution.builder().task(taskLesson3).student(student2).filePath("file").version(1).build();
        Solution solution4 = Solution.builder().task(taskLesson3).student(student3).filePath("file").version(1).build();
        Solution solution5 = Solution.builder().task(taskLesson3).student(student3).filePath("file").version(2).build();

        solutionRepository.saveAll(List.of(solution1, solution2, solution3, solution4, solution5));

        log.info("Success initialize database");
    }
}
