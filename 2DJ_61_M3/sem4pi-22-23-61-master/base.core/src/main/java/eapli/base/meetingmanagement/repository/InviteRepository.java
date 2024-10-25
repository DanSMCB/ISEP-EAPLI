package eapli.base.meetingmanagement.repository;

import eapli.base.meetingmanagement.domain.Invite;
import eapli.framework.domain.repositories.DomainRepository;
import eapli.framework.infrastructure.authz.domain.model.Username;

public interface InviteRepository extends DomainRepository<Long, Invite> {

    Iterable<Invite> findInvitesByUsername(Username username);
}