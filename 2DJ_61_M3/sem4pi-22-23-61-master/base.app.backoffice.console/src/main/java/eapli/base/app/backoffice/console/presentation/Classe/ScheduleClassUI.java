package eapli.base.app.backoffice.console.presentation.Classe;

import eapli.base.Classe.aplication.ClassController;
import eapli.base.Classe.domain.*;
import eapli.base.Student_Teacher.Teacher.Domain.Acronym;
import eapli.base.Student_Teacher.Teacher.Domain.Teacher;
import eapli.base.Student_Teacher.Teacher.Repository.TeacherRepository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashSet;
import java.util.Optional;
import java.util.Scanner;
import java.util.Set;


public class ScheduleClassUI{
    private ClassController classController;
    public ScheduleClassUI(ClassController classController) {
        this.classController = classController;
    }
    public void run() throws Exception {
        Scanner scanner = new Scanner(System.in);

        // Get input for the new class details
        System.out.println("Enter class ID: ");
        Long id = scanner.nextLong();
        scanner.nextLine();

        System.out.println("Enter class title: ");
        Classe_Title title = Classe_Title.valueOf(scanner.nextLine());

        System.out.println("Enter class start time (HH:mm): ");
        Classe_Start_Time userInputStartTime = Classe_Start_Time.valueOf(LocalTime.parse(scanner.nextLine()));

        System.out.println("Enter class finish time (HH:mm): ");
        Classe_Finish_Time userInputEndTime = Classe_Finish_Time.valueOf(LocalTime.parse(scanner.nextLine()));

        System.out.println("Enter class start date (YYYY-MM-DD): ");
        LocalDate startDate = LocalDate.parse(scanner.nextLine());
        Classe_Start_Date start_date = new Classe_Start_Date(startDate);

        System.out.println("Enter class finish date (YYYY-MM-DD): ");
        LocalDate finishDate = LocalDate.parse(scanner.nextLine());
        Classe_Finish_Date finish_date = new Classe_Finish_Date(finishDate);

        System.out.println("Enter day of the week (1-7): ");
        int dayOfWeek = scanner.nextInt();
        DayOfWeek userInputDayOfWeek = DayOfWeek.valueOf(dayOfWeek);

        System.out.println("Enter teacher Acronym: ");
        Acronym Acronym = eapli.base.Student_Teacher.Teacher.Domain.Acronym.valueOf(scanner.nextLine());
        Acronym teacherAcronym = Acronym.valueOf(scanner.nextLine());

       /* Optional<Teacher> teacherOptional = TeacherRepository.findTeacherByAcronym(teacherAcronym);
        if (teacherOptional.isEmpty()) {
            throw new IllegalArgumentException("No teacher found with provided acronym");
        }
        Set<Teacher> teachers = new HashSet<>();
        teachers.add(teacherOptional.get());


        Teacher teacher = new Teacher(teacherAcronym);
        Set<Teacher> teachers = new HashSet<>();
        teachers.add(teacher);

        */

        boolean hasConflict = classController.checkClassConflict(new Classe(id, title, start_date, finish_date, userInputStartTime, userInputEndTime, userInputDayOfWeek, teacherAcronym));
        if (hasConflict) {
            System.out.println("Class conflicts with an existing class.");
            // Handle class conflict, display an error message or take any appropriate action
        } else {
            // Proceed with scheduling the class
            classController.scheduleClass(id, title, userInputStartTime, userInputEndTime, start_date, finish_date, userInputDayOfWeek, teacherAcronym);
            System.out.println("Class scheduled successfully.");
        }
    }
}
