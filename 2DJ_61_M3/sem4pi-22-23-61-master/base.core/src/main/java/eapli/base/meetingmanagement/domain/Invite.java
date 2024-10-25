package eapli.base.meetingmanagement.domain;

import eapli.framework.domain.model.AggregateRoot;
import eapli.framework.domain.model.DomainEntities;
import eapli.framework.infrastructure.authz.domain.model.SystemUser;
import eapli.framework.validations.Preconditions;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Embeddable
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@EqualsAndHashCode
public class Invite implements AggregateRoot<Long> {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Meeting meeting;

    private SystemUser sender;

    private SystemUser receiver;

    public Invite (SystemUser sender, SystemUser receiver, Meeting meeting){
        Preconditions.noneNull(sender, receiver, meeting);

        this.sender=sender;
        this.receiver=receiver;
        this.meeting=meeting;
    }

    @Override
    public boolean sameAs(Object other) {
        Invite invite = (Invite) other;
        return this.equals(invite);
    }

    @Override
    public Long identity() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        return DomainEntities.areEqual(this, o);
    }

    @Override
    public String toString() {
        return "You've been invited by " + sender.email() + " to a meeting on the date " + meeting.getMeetingDate() +
                " at " + meeting.getMeetingDuration() + " with the dutarion of " + meeting.getMeetingDuration();
    }
}
