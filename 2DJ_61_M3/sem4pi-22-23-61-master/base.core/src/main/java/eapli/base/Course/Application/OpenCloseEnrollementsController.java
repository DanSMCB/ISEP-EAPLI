package eapli.base.Course.Application;

import eapli.base.Course.Domain.Course;
import eapli.base.Course.Repository.CourseRepository;
import eapli.base.exammanagement.repository.ExamRepository;
import eapli.framework.infrastructure.authz.application.AuthorizationService;

public class OpenCloseEnrollementsController {

    private final CourseService cs;

    public OpenCloseEnrollementsController(final AuthorizationService authz, final CourseRepository courseRepository) {
       cs = new CourseService(authz,courseRepository);
    }

    public Course openEnrollments(Long courseId) {
        return cs.openEnrollments(courseId);
    }

    public Course closeEnrollments(Long courseId) {
        return cs.closeEnrollments(courseId);
    }

}
