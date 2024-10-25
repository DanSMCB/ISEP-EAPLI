package eapli.base.Course.Application;

import eapli.base.Course.Domain.Course;
import eapli.base.Course.Repository.CourseRepository;
import eapli.framework.infrastructure.authz.application.AuthorizationService;

public class OpenCloseCourseController {

    private final CourseService cs;

    public OpenCloseCourseController(final AuthorizationService authz, final CourseRepository courseRepository) {
        cs = new CourseService(authz,courseRepository);
    }

    public Course openCourse(Long courseId) {
        return cs.openCourse(courseId);
    }

    public Course closeCourse(Long courseId) {
        return cs.closeCourse(courseId);
    }

}
