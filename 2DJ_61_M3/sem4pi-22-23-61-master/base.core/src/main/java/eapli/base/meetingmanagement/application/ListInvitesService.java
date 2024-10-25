package eapli.base.meetingmanagement.application;

import eapli.base.meetingmanagement.domain.Invite;
import eapli.base.meetingmanagement.repository.InviteRepository;
import eapli.framework.infrastructure.authz.application.AuthorizationService;
import eapli.framework.infrastructure.authz.domain.model.Username;

public class ListInvitesService {

    private final AuthorizationService authz;
    private final InviteRepository inviteRepository;

    public ListInvitesService(final AuthorizationService authz, final InviteRepository inviteRepository) {
        this.authz = authz;
        this.inviteRepository = inviteRepository;
    }

    /**
     * @return
     */
    public Iterable<Invite> invites(Username username) {
        return inviteRepository.findInvitesByUsername(username);
    }
}
