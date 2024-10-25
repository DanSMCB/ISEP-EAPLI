package eapli.base.Enrollment.Presentation;

import java.util.Scanner;

public class RequestEnrollmentUI {

    public static void main() {
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        while (running) {

            System.out.println("----- Request Enrollment UI -----");
            System.out.println("1. Request Enrollment");
            System.out.println("2. Exit");
            System.out.print("Enter your choice: ");

            int option = scanner.nextInt();

            switch (option) {
                case 1:
                    System.out.println("Please choose one course from the following ones:");
                    //Iterable<Course> courseList = CourseRepository.findAll();
                    //Passar os dados para o controller
                    //Enrollment enrollment = new Enrollment(actualUser,choosenCourse);(factory)
                    //Criar o enrollment e persisti-lo
                    break;
                case 2:
                    System.out.println("Exiting...");
                    running = false;
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
            System.out.println();
        }
        scanner.close();
    }
}

