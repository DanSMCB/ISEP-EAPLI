package eapli.base.Student_Teacher.Teacher.Repository;

import eapli.base.Student_Teacher.Teacher.Domain.Teacher;
import eapli.base.Student_Teacher.Teacher.Domain.Acronym;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class TeacherRepository {
    private static final List<Teacher> teachers = new ArrayList<>();

    public static Optional<Teacher> findTeacherByAcronym(Acronym acronym) {
        for (Teacher teacher : teachers) {
            if (teacher.getAcronym().equals(acronym)) {
                return Optional.of(teacher);
            }
        }
        return Optional.empty();
    }

    public void save(Teacher teacher) {

        teachers.add(teacher);
    }

    Acronym acronym = new Acronym("TST");

    public void delete(Teacher teacher) {
        teachers.remove(teacher);
    }

    public List<Teacher> findAll() {
        return new ArrayList<>(teachers);
    }

    public Optional<Teacher> findById(Long id) {
        for (Teacher teacher : teachers) {
            if (teacher.getId().equals(id)) {
                return Optional.of(teacher);
            }
        }
        return Optional.empty();
    }
}
