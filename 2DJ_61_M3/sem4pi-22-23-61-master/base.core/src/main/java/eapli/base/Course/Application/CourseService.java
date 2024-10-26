package eapli.base.Course.Application;

import eapli.base.Course.Domain.Course;
import eapli.base.Course.Repository.CourseRepository;
import eapli.framework.infrastructure.authz.application.AuthorizationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

@Service
public class CourseService {
    private final CourseRepository courseRepository;
    private final AuthorizationService authz;
    @Autowired
    public CourseService(final AuthorizationService authz, CourseRepository courseRepository) {
        this.authz = authz;
        this.courseRepository = courseRepository;
    }

    public Course createCourse(Course course) {
        return courseRepository.save(course);
    }

    public Course openEnrollments(Long courseId) {
        Optional<Course> courseOpt = courseRepository.ofIdentity(courseId);
        if (courseOpt.isPresent()) {
            Course course = courseOpt.get();
            course.setEnrollmentOpen(true);
            return courseRepository.save(course);
        } else {
            throw new EntityNotFoundException("Course not found with ID: " + courseId);
        }
    }
    public Course closeEnrollments(Long courseId) {
        Optional<Course> courseOpt = courseRepository.ofIdentity(courseId);
        if (courseOpt.isPresent()) {
            Course course = courseOpt.get();
            course.setEnrollmentOpen(false);
            return courseRepository.save(course);
        } else {
            throw new EntityNotFoundException("Course not found with ID: " + courseId);
        }
    }

    public Course openCourse(Long courseId) {
        Optional<Course> courseOpt = courseRepository.ofIdentity(courseId);
        if (courseOpt.isPresent()) {
            Course course = courseOpt.get();
            course.setIsOpen(true);
            return courseRepository.save(course);
        } else {
            throw new EntityNotFoundException("Course not found with ID: " + courseId);
        }
    }

    public Course closeCourse(Long courseId) {
        Optional<Course> courseOpt = courseRepository.ofIdentity(courseId);
        if (courseOpt.isPresent()) {
            Course course = courseOpt.get();
            course.setIsOpen(false);
            return courseRepository.save(course);
        } else {
            throw new EntityNotFoundException("Course not found with ID: " + courseId);
        }
    }


    /*public Course openEnrollments(Course course) {
        Course course = courseRepository.ofIdentity(courseId);
        course.setEnrollmentOpen(true);
        return courseRepository.save(course);
    }

    public Course closeEnrollments(Long courseId) {
        Course course = courseRepository.ofIdentity(courseId);
        course.setEnrollmentOpen(false);
        return courseRepository.save(course);
    }*/

    /*public List<Course> getCoursesByName(String courseName) {
        return courseRepository.findByCourseName(courseName);
    }

    public Course findCourseById(Long courseId) {
        return courseRepository.findById(courseId)
                .orElseThrow(() -> new IllegalArgumentException("Course not found"));
    }

    public Course openEnrollment(Long courseId) {
        Course course = courseRepository.findById(courseId).orElseThrow(() -> new IllegalArgumentException("Curso não encontrado"));
        course.setEnrollmentOpen(true);
        return courseRepository.save(course);
    }

    public Course closeEnrollment(Long courseId) {
        Course course = courseRepository.findById(courseId).orElseThrow(() -> new IllegalArgumentException("Curso não encontrado"));
        course.setEnrollmentOpen(false);
        return courseRepository.save(course);
    }

    public Course openCourse(Long courseId) {
        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new IllegalArgumentException("Curso não encontrado"));
        course.setIsOpen(true);
        return courseRepository.save(course);
    }

    public Course closeCourse(Long courseId) {
        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new IllegalArgumentException("Curso não encontrado"));
        course.setIsOpen(false);
        return courseRepository.save(course);
    }*/

}

