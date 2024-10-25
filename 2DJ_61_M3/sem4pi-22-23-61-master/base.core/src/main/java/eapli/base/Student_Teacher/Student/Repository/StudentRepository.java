package eapli.base.Student_Teacher.Student.Repository;


import eapli.base.Student_Teacher.Student.domain.Student;
import eapli.base.clientusermanagement.domain.MecanographicNumber;
import eapli.framework.domain.repositories.DomainRepository;
import eapli.framework.infrastructure.authz.domain.model.Username;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
public interface StudentRepository extends DomainRepository<Username, Student> {
    Map<MecanographicNumber, Student> students = new HashMap<>();

    static Student findStudentByMechanographicNumber(MecanographicNumber mechanographicNumber) {
        return students.get(mechanographicNumber);
    }

     default void addStudent(Student student) {
        students.put(student.getMechanographicNumber(), student);
    }

    default void removeStudent(Student student){
        students.remove(student);
    }

    default void removeStudentByMechaNum(MecanographicNumber mechanographicNumber){
        students.remove(mechanographicNumber);
    }

    default Student getStudentByMechaNum(MecanographicNumber mechanographicNumber){
        return students.get(mechanographicNumber);
    }
}