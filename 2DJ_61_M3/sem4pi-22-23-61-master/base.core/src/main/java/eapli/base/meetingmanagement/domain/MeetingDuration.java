package eapli.base.meetingmanagement.domain;

import eapli.framework.domain.model.ValueObject;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;

@Embeddable
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@EqualsAndHashCode
public class MeetingDuration implements ValueObject {

    private Integer value;

    public MeetingDuration(int value) {
        if (!isValidMeetingDuration(value)) {
            throw new IllegalArgumentException("Invalid meeting duration format");
        }
        this.value=value;
    }

    private static boolean isValidMeetingDuration(int value) {
        return value>0;
    }

    @Override
    public String toString() {
        return value.toString();
    }


}
