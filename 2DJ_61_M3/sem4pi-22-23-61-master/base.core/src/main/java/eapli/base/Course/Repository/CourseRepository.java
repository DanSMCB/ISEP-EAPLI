package eapli.base.Course.Repository;

import eapli.base.Course.Application.CourseService;
import eapli.base.Course.Domain.Course;
import eapli.base.Enrollment.Domain.Enrollment;
import eapli.base.clientusermanagement.domain.MecanographicNumber;
import eapli.base.exammanagement.domain.Exam;
import eapli.framework.domain.repositories.DomainRepository;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

public interface CourseRepository extends DomainRepository<Long, Course> {
     Iterable<Course> findByCourseName(String courseName);

     default Course openCourse(Long courseId) {
          Course course = this.ofIdentity(courseId)
                  .orElseThrow(() -> new EntityNotFoundException("Course not found with ID: " + courseId));
          course.setIsOpen(true);
          return this.save(course);
     }
     default Course closeCourse(Long courseId) {
          Course course = this.ofIdentity(courseId)
                  .orElseThrow(() -> new EntityNotFoundException("Course not found with ID: " + courseId));
          course.setIsOpen(false);
          return this.save(course);
     }

     default Course openEnrollments(Long courseId) {
          Course course = this.ofIdentity(courseId)
                  .orElseThrow(() -> new EntityNotFoundException("Course not found with ID: " + courseId));
          course.setEnrollmentOpen(true);
          return this.save(course);
     }

     default Course closeEnrollments(Long courseId) {
          Course course = this.ofIdentity(courseId)
                  .orElseThrow(() -> new EntityNotFoundException("Course not found with ID: " + courseId));
          course.setEnrollmentOpen(false);
          return this.save(course);
     }

}
