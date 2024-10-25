package eapli.base.Enrollment.Domain;

import eapli.base.Course.Domain.Course;
import lombok.*;
import org.springframework.security.core.userdetails.User;

import javax.persistence.*;

    @Entity
    @AllArgsConstructor(access = AccessLevel.PROTECTED)
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    @Inheritance(strategy = InheritanceType.JOINED)
    public class Enrollment {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "EnrollmentID", nullable = false)
    private Long id;

    @Column(name = "EnrollmentStudent", nullable = false)
    private String userName;

    @Column(name = "EnrollmentCourse", nullable = false)
    private Course course;

    @Column(name = "Description", nullable = false)
    private String description;

    @Column(name = "EnrollmentState", nullable = false)
    private boolean isAccepted;


    public Enrollment(String userName, Course course, String description){
        this.userName = userName;
        this.course = course;
        this.description = description;
        this.isAccepted = false;//valor default(Só fica aceite a mando do manager)
    }


    //Para usar na US 108(So o manager aceita/rejeita)
    public void acceptEnrollment(){
        this.isAccepted=true;
    }

    }
