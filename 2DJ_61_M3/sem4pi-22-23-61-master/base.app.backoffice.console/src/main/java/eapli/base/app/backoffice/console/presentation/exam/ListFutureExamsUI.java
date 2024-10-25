package eapli.base.app.backoffice.console.presentation.exam;

import eapli.base.Student_Teacher.Student.domain.Student;
import eapli.base.exammanagement.application.ListFutureExamsController;
import eapli.base.exammanagement.domain.Exam;
import eapli.base.infrastructure.persistence.PersistenceContext;
import eapli.framework.infrastructure.authz.application.AuthorizationService;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;
import eapli.framework.infrastructure.authz.application.UserSession;
import eapli.framework.infrastructure.authz.domain.model.SystemUser;
import eapli.framework.presentation.console.AbstractUI;



public class ListFutureExamsUI extends AbstractUI {

    private final AuthorizationService authz = AuthzRegistry.authorizationService();
    final UserSession s = authz.session().orElseThrow(IllegalStateException::new);
    final SystemUser myUser = s.authenticatedUser();

    private final ListFutureExamsController controller = new ListFutureExamsController(
            AuthzRegistry.authorizationService(), PersistenceContext.repositories().examRepository());


    @Override
    protected boolean doShow() {
        Student student = (Student)myUser;
        Iterable<Exam> futureExams = controller.listFutureExams(student.getMechanographicNumber());
        for(Exam e : futureExams){
            System.out.println("Exam id: " + e.getId() + "; exam course: " + e.getExamCourse().getCourseName());
        }
        return false;
    }

    @Override
    public String headline() {
        return "List all future exams";
    }
}
