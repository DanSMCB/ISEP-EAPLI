package eapli.base.meetingmanagement.domain;

import eapli.framework.domain.model.ValueObject;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Embeddable;
import java.time.LocalTime;
import java.util.Date;

@Embeddable
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@EqualsAndHashCode
public class MeetingTime implements ValueObject {

    @DateTimeFormat(pattern = "hh:mm")
    private Date meetingTime;

    public MeetingTime(Date meetingTime) {this.meetingTime=meetingTime;
    }

    @Override
    public String toString() {
        return meetingTime.toString();
    }
}
