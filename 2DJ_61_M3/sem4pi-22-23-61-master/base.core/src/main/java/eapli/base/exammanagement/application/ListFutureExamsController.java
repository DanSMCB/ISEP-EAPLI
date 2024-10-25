package eapli.base.exammanagement.application;

import eapli.base.clientusermanagement.domain.MecanographicNumber;
import eapli.base.exammanagement.domain.Exam;
import eapli.base.exammanagement.repository.ExamRepository;
import eapli.framework.infrastructure.authz.application.AuthorizationService;

public class ListFutureExamsController {

    private final ListFutureExamsService svc;

    public ListFutureExamsController(final AuthorizationService authz, final ExamRepository examRepository) {
        // dependency injection - to make this object more testable we don't create the
        // infrastructure objects to avoid coupling to the implementation. This way, the controller
        // can be used in different scenarios with different implementations of the repository. for
        // instance, unit testing.
        svc = new ListFutureExamsService(authz, examRepository);
    }

    /**
     * List all exams.
     *
     * @return
     */
        public Iterable<Exam> listFutureExams(MecanographicNumber mecanographicNumber) {
        return svc.futureExams(mecanographicNumber);
    }

}

