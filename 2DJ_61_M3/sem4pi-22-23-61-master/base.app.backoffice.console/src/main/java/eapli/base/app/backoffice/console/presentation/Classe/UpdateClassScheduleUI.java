package eapli.base.app.backoffice.console.presentation.Classe;

import eapli.base.Classe.aplication.ClassController;
import eapli.base.Classe.domain.*;

import java.time.LocalTime;
import java.util.List;
import java.util.Scanner;

public class UpdateClassScheduleUI {
    private final ClassController classController;

    public UpdateClassScheduleUI(ClassController classController) {
        this.classController = classController;

    }

    public class NoClassesException extends Exception {
        public NoClassesException(String message) {
            super(message);
        }
    }

    public void displayAllClasses() throws NoClassesException {
        List<Classe> allClasses = classController.getAllClasses();

        if (allClasses.isEmpty()) {
            throw new NoClassesException("No classes found!");
        }

        for (Classe classe : allClasses) {
            System.out.println(classe);
        }
    }


    public void run() throws Exception {
        Scanner scanner = new Scanner(System.in);
        try {
            System.out.println("Existing classes: ");
            displayAllClasses();


            System.out.println("Enter class ID: ");
            Long id = scanner.nextLong();

            scanner.nextLine(); // Consume newline left-over

            System.out.println("Enter new class start time (HH:mm): ");
            Classe_Start_Time newStartTime = Classe_Start_Time.valueOf(LocalTime.parse(scanner.nextLine()));

            System.out.println("Enter new class finish time (HH:mm): ");
            Classe_Finish_Time newFinishTime = Classe_Finish_Time.valueOf(LocalTime.parse(scanner.nextLine()));

            classController.updateClassSchedule(id, newStartTime, newFinishTime);

            System.out.println("Class schedule updated successfully.");
            // Display all classes after updating
            System.out.println("Updated classes: ");
            displayAllClasses();

        } catch (NoClassesException e) {
            System.err.println(e.getMessage());
        } catch (Exception e) {
        }
    }
}