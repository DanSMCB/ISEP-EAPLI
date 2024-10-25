package eapli.base.meetingmanagement.application;

import eapli.base.meetingmanagement.domain.Invite;
import eapli.base.meetingmanagement.repository.InviteRepository;
import eapli.framework.infrastructure.authz.application.AuthorizationService;
import eapli.framework.infrastructure.authz.domain.model.Username;

public class ListInvitesController {

    private final ListInvitesService svc;

    public ListInvitesController(final AuthorizationService authz, final InviteRepository inviteRepository) {
        // dependency injection - to make this object more testable we don't create the
        // infrastructure objects to avoid coupling to the implementation. This way, the controller
        // can be used in different scenarios with different implementations of the repository. for
        // instance, unit testing.
        svc = new ListInvitesService(authz, inviteRepository);
    }

    /**
     * List all exams.
     *
     * @return
     */
    public Iterable<Invite> listInvites(Username username) {
        return svc.invites(username);
    }
}
