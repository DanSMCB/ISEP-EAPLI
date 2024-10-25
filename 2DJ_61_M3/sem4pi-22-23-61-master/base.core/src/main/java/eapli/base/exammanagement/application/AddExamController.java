package eapli.base.exammanagement.application;

import eapli.base.Course.Domain.Course;
import eapli.base.exammanagement.domain.Exam;
import eapli.base.exammanagement.domain.ExamDate;
import eapli.base.exammanagement.domain.ExamTime;
import eapli.base.exammanagement.repository.ExamRepository;
import eapli.base.usermanagement.domain.BaseRoles;
import eapli.framework.application.UseCaseController;
import eapli.framework.infrastructure.authz.application.AuthorizationService;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;

import java.time.LocalDate;
import java.time.LocalTime;

/**
 * Controller de adicionar um Exame
 * Created and Edited by João Cruz
 *
 * Inspired and Based on Daniel Braga's and EAPLI's code
 */

@UseCaseController

// Fica para o próximo Sprint devido à falta do Course e falta de tempo.
public class AddExamController {
    private final AuthorizationService authz = AuthzRegistry.authorizationService();
    private final ExamRepository examRepository;

    public AddExamController(ExamRepository examRepository) {
        this.examRepository = examRepository;
    }

    public Exam addExam(Course examCourse, LocalDate examDate, LocalTime startTime, LocalTime endTime) {
        authz.ensureAuthenticatedUserHasAnyOf(BaseRoles.TEACHER, BaseRoles.ADMIN);

        //ExamTime examTime = new ExamTime(startTime, endTime);
        //Exam exam = new Exam(examTime, new ExamDate(examDate), examCourse);
        //return examRepository.save(exam);
        return null;
    }
}
