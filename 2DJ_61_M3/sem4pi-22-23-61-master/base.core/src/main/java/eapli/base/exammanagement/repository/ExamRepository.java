package eapli.base.exammanagement.repository;

import eapli.base.Course.Domain.Course;
import eapli.base.clientusermanagement.domain.MecanographicNumber;
import eapli.base.exammanagement.domain.Exam;
import eapli.framework.domain.repositories.DomainRepository;

public interface ExamRepository extends DomainRepository<Long, Exam> {

    Iterable<Exam> findFutureExamsByMecanographicNumber(MecanographicNumber mecanographicNumber);

    Iterable<Exam> findByCourse(Course course);
}