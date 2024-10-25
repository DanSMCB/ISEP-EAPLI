package eapli.base.meetingmanagement.domain;

import eapli.framework.infrastructure.authz.application.AuthorizationService;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;
import eapli.framework.infrastructure.authz.application.UserSession;
import eapli.framework.infrastructure.authz.domain.model.SystemUser;
import org.junit.jupiter.api.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class InviteTest {

    Date date=new Date(System.currentTimeMillis());
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("hh:mm");
    Date time = simpleDateFormat.parse("10:30");

    Meeting meeting = new Meeting(new MeetingDuration(30), new MeetingDate(date), new MeetingTime(time));

    private final AuthorizationService authz = AuthzRegistry.authorizationService();
    final UserSession s = authz.session().orElseThrow(IllegalStateException::new);
    final SystemUser myUser = s.authenticatedUser();


    public InviteTest() throws ParseException {
    }

    @Test
    void ensureInviteMustHaveSender() {
        System.out.println("must have invite sender");

        assertThrows(IllegalArgumentException.class, () -> new Invite(null, myUser, meeting));
    }

    @Test
    void ensureInviteMustHaveReceiver() {
        System.out.println("must have invite receiver");

        assertThrows(IllegalArgumentException.class, () -> new Invite(myUser, null, meeting));
    }

    @Test
    void ensureInviteMustHaveMeeting() {
        System.out.println("must have meeting");

        assertThrows(IllegalArgumentException.class, () -> new Invite(myUser, myUser, null));
    }
}
