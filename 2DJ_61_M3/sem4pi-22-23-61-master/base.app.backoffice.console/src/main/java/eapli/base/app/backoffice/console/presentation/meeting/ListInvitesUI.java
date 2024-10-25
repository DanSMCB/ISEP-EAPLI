package eapli.base.app.backoffice.console.presentation.meeting;

import eapli.base.infrastructure.persistence.PersistenceContext;
import eapli.base.meetingmanagement.application.ListInvitesController;
import eapli.base.meetingmanagement.domain.Invite;
import eapli.framework.infrastructure.authz.application.AuthorizationService;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;
import eapli.framework.infrastructure.authz.application.UserSession;
import eapli.framework.infrastructure.authz.domain.model.SystemUser;
import eapli.framework.presentation.console.AbstractUI;

public class ListInvitesUI extends AbstractUI {

    private final AuthorizationService authz = AuthzRegistry.authorizationService();
    final UserSession s = authz.session().orElseThrow(IllegalStateException::new);
    final SystemUser myUser = s.authenticatedUser();

    private final ListInvitesController controller = new ListInvitesController(
            AuthzRegistry.authorizationService(), PersistenceContext.repositories().inviteRepository());

    @Override
    protected boolean doShow() {
        final Iterable<Invite> invites = this.controller.listInvites(myUser.username());
        for(Invite i : invites){
            System.out.println(i.toString());
        }
        return false;
    }

    @Override
    public String headline() {
        return "List meeting invites";
    }
}
