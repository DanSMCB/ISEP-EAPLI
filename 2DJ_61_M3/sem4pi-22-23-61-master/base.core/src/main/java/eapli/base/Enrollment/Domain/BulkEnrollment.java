package eapli.base.Enrollment.Domain;

import eapli.base.Course.Domain.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class BulkEnrollment {
    private String csvFilePath;

    public BulkEnrollment(String csvFilePath) {
        this.csvFilePath = csvFilePath;
    }

    public List<Enrollment> bulkEnrollment() {
        List<Enrollment> enrollments = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(csvFilePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");
                if (data.length == 5) {
                    String userName = data[0].trim();
                    Course_Name courseName = new Course_Name(data[1].trim());
                    Maximum_Number_Of_Students max = new Maximum_Number_Of_Students(Integer.parseInt(data[3]));
                    Minimum_Number_Of_Students min = new Minimum_Number_Of_Students(Integer.parseInt(data[4]));
                    String description = data[2].trim();
                    Course course = new Course(courseName, max, min, new Small_Textual_Description(description));
                    Enrollment newEnrollment = new Enrollment(userName, course, description);
                    enrollments.add(newEnrollment);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return enrollments;
    }
}