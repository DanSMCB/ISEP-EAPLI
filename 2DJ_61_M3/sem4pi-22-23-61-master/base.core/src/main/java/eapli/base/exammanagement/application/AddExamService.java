package eapli.base.exammanagement.application;

import eapli.base.Course.Domain.Course;
import eapli.base.Course.Repository.CourseRepository;
import eapli.base.exammanagement.domain.Exam;
import eapli.base.exammanagement.domain.ExamDate;
import eapli.base.exammanagement.domain.ExamTime;
import eapli.base.exammanagement.repository.ExamRepository;
import eapli.base.usermanagement.domain.BaseRoles;
import eapli.framework.application.ApplicationService;
import eapli.framework.infrastructure.authz.application.AuthorizationService;

/**
 * Adicionar Serviço de Exame
 * Created and Edited by João Cruz
 * Inspired and Based on Daniel Braga's and EAPLI's code
 */

@ApplicationService
public class AddExamService {
    private final AuthorizationService authz;
    private final ExamRepository examRepository;
    private final CourseRepository courseRepository;

    public AddExamService(final AuthorizationService authz, final ExamRepository examRepository, final CourseRepository courseRepository) {
        this.authz = authz;
        this.examRepository = examRepository;
        this.courseRepository = courseRepository;
    }

    public Exam addExam(Course examCourse, ExamTime examTime, ExamDate examDate) {
        authz.ensureAuthenticatedUserHasAnyOf(BaseRoles.TEACHER, BaseRoles.ADMIN);

        Exam exam = new Exam(examTime, examDate, examCourse);
        return examRepository.save(exam);
    }

    public Iterable<Course> getCourses() {
        authz.ensureAuthenticatedUserHasAnyOf(BaseRoles.TEACHER, BaseRoles.ADMIN);

        return courseRepository.findAll();
    }
}
