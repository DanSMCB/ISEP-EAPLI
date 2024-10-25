package eapli.base.app.backoffice.console.presentation.Classe;

import eapli.base.Classe.domain.DayOfWeek;
import eapli.base.ExtraClasse.domain.ExtraClasse_Day;
import eapli.base.ExtraClasse.aplication.ExtraClassController;
import eapli.base.ExtraClasse.domain.ExtraClasse;
import eapli.base.ExtraClasse.domain.ExtraClasse_Finish_Time;
import eapli.base.ExtraClasse.domain.ExtraClasse_Start_Time;
import eapli.base.ExtraClasse.domain.ExtraClasse_Title;
import eapli.base.Student_Teacher.Teacher.Domain.Acronym;

import java.time.LocalTime;
import java.util.Scanner;

public class ScheduleExtraClassUI {
    private final ExtraClassController extraClassController;

    public ScheduleExtraClassUI(ExtraClassController extraClassController) {
        this.extraClassController = extraClassController;
    }

    public void run() throws Exception {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter class ID: ");
        Long id = scanner.nextLong();
        scanner.nextLine();

        System.out.println("Enter class title: ");
        ExtraClasse_Title title = ExtraClasse_Title.from(scanner.nextLine());

        System.out.println("Enter class start time (hh:mm): ");
        String startTimeInput = scanner.nextLine();
        ExtraClasse_Start_Time startTime = ExtraClasse_Start_Time.from(LocalTime.parse(startTimeInput));

        System.out.println("Enter class finish time (hh:mm): ");
        String finishTimeInput = scanner.nextLine();
        ExtraClasse_Finish_Time finishTime = ExtraClasse_Finish_Time.from(LocalTime.parse(finishTimeInput));

        System.out.println("Enter day of the week (1-7): ");
        int dayOfWeek = scanner.nextInt();
        DayOfWeek dayInput = DayOfWeek.valueOf(dayOfWeek);


        System.out.println("Enter teacher acronym: ");
        String teacherAcronymInput = scanner.nextLine();
        Acronym teacherAcronym = Acronym.valueOf(teacherAcronymInput);

        boolean hasConflict = extraClassController.checkClassConflict(new ExtraClasse(id, title, startTime, finishTime, (ExtraClasse_Day) dayInput, teacherAcronym));
        if (hasConflict) {
            System.out.println("Class conflicts with an existing class.");
            // Handle class conflict, display an error message or take any appropriate action
        } else {
            // Proceed with scheduling the class
            extraClassController.scheduleExtraClass(id, title, startTime, finishTime, (ExtraClasse_Day) dayInput, teacherAcronym);
            System.out.println("Class scheduled successfully.");
        }
    }
}
