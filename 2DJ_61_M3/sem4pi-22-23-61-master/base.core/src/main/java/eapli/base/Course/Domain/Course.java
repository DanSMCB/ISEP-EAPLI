package eapli.base.Course.Domain;

import eapli.base.Student_Teacher.Student.domain.Student;
import eapli.base.Student_Teacher.Teacher.Domain.Teacher;
import eapli.framework.domain.model.AggregateRoot;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "COURSE")
public class Course implements AggregateRoot<Long> {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Embedded
    @Column(unique = true)
    private Course_Name courseName;

    private Maximum_Number_Of_Students maximumNumberOfStudents;
    private Minimum_Number_Of_Students minimumNumberOfStudents;
    private Small_Textual_Description smallTextualDescription;

    private boolean enrollmentOpen;
    private boolean isOpen;


    @ManyToOne
    @JoinColumn(name = "teacher_in_charge_id")
    private Teacher teacherInCharge;

    @ManyToMany
    @JoinTable(
            name = "course_student",
            joinColumns = @JoinColumn(name = "course_id"),
            inverseJoinColumns = @JoinColumn(name = "student_id"))
    private List<Student> students;

    public void addStudent(Student student){
        students.add(student);
    }


    public void setIsOpen(boolean isOpen) {
        this.isOpen = isOpen;
    }

    @Override
    public boolean sameAs(Object other) {
        return false;
    }

    @Override
    public Long identity() {
        return id;
    }

    public Course(Course_Name courseName, Maximum_Number_Of_Students maximumNumberOfStudents, Minimum_Number_Of_Students minimumNumberOfStudents, Small_Textual_Description smallTextualDescription){
        this.courseName=courseName;
        this.maximumNumberOfStudents=maximumNumberOfStudents;
        this.minimumNumberOfStudents=minimumNumberOfStudents;

        this.isOpen=false;
        this.enrollmentOpen=false;
    }
}
