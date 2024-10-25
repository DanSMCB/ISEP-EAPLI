package eapli.base.persistence.impl.jpa;

import eapli.base.Application;
import eapli.base.meetingmanagement.domain.Invite;
import eapli.base.meetingmanagement.repository.InviteRepository;
import eapli.framework.domain.repositories.TransactionalContext;
import eapli.framework.infrastructure.authz.domain.model.Username;
import eapli.framework.infrastructure.repositories.impl.jpa.JpaAutoTxRepository;

import javax.persistence.TypedQuery;

public class JpaInviteRepository extends JpaAutoTxRepository<Invite, Long, Long> implements InviteRepository {

    public JpaInviteRepository(final TransactionalContext autoTx) {
        super(autoTx, "id");
    }

    public JpaInviteRepository(final String puname) {
        super(puname, Application.settings().getExtendedPersistenceProperties(),
                "id");
    }

    @Override
    public Iterable<Invite> findInvitesByUsername(Username username) {
        final TypedQuery<Invite> q = createQuery(
                "SELECT e FROM Invite e WHERE e.receiver.username = :username", Invite.class);
        q.setParameter("username", username);
        return q.getResultList();
    }
}
