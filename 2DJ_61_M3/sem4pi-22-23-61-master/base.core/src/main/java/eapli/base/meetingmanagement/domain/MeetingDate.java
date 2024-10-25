package eapli.base.meetingmanagement.domain;

import eapli.framework.domain.model.ValueObject;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Embeddable;
import java.util.Date;

@Embeddable
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@Getter
@EqualsAndHashCode
public class MeetingDate implements ValueObject {

    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private Date meetingDate;

    public MeetingDate(Date meetingDate) {
        if (!isValidMeetingDate(meetingDate)) {
            throw new IllegalArgumentException("Invalid meeting date format");
        }
        this.meetingDate=meetingDate;
    }

    private static boolean isValidMeetingDate(Date meetingDate) {
        Date date = new Date(System.currentTimeMillis());
        return meetingDate.after(date);
    }

    @Override
    public String toString() {
        return meetingDate.toString();
    }
}
