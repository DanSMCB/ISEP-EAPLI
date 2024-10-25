package eapli.base.exammanagement.application;

import eapli.base.clientusermanagement.domain.MecanographicNumber;
import eapli.base.exammanagement.domain.Exam;
import eapli.base.exammanagement.repository.ExamRepository;
import eapli.base.usermanagement.domain.BaseRoles;
import eapli.framework.infrastructure.authz.application.AuthorizationService;

public class ListFutureExamsService {

    private final AuthorizationService authz;
    private final ExamRepository examRepository;

    public ListFutureExamsService(final AuthorizationService authz, final ExamRepository examRepository) {
        this.authz = authz;
        this.examRepository = examRepository;
    }

    /**
     * @return
     */
    public Iterable<Exam> futureExams(MecanographicNumber mecanographicNumber) {
        authz.ensureAuthenticatedUserHasAnyOf(BaseRoles.STUDENT, BaseRoles.ADMIN);

        return examRepository.findFutureExamsByMecanographicNumber(mecanographicNumber);
    }
}
