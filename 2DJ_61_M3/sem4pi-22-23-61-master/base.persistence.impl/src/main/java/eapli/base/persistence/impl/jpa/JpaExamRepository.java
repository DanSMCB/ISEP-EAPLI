package eapli.base.persistence.impl.jpa;

import eapli.base.Application;
import eapli.base.Course.Domain.Course;
import eapli.base.clientusermanagement.domain.MecanographicNumber;
import eapli.base.exammanagement.domain.Exam;
import eapli.base.exammanagement.repository.ExamRepository;
import eapli.framework.domain.repositories.TransactionalContext;
import eapli.framework.infrastructure.repositories.impl.jpa.JpaAutoTxRepository;

import javax.persistence.TypedQuery;

class JpaExamRepository extends JpaAutoTxRepository<Exam, Long, Long> implements ExamRepository {

    public JpaExamRepository(final TransactionalContext autoTx) {
        super(autoTx, "id");
    }

    public JpaExamRepository(final String puname) {
        super(puname, Application.settings().getExtendedPersistenceProperties(),
                "id");
    }

    @Override
    public Iterable<Exam> findByCourse(final Course course) {
        final TypedQuery<Exam> q = createQuery(
                "SELECT e FROM Exam e WHERE e.examCourse = :course", Exam.class);
        q.setParameter("course", course);
        return q.getResultList();
    }

    @Override
    public Iterable<Exam> findFutureExamsByMecanographicNumber(final MecanographicNumber mecanographicNumber) {
        final TypedQuery<Exam> q = createQuery(
                "SELECT e FROM Exam e WHERE e.examCourse CONTAINS(SELECT a FROM Student WHERE a.mecanographicNumber = :mecanographicNumber) AND e.examDate > CURDATE()", Exam.class);
        q.setParameter("mecanographicNumber", mecanographicNumber);
        return q.getResultList();
    }
}
