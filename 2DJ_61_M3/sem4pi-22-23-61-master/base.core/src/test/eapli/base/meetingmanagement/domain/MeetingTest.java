package eapli.base.meetingmanagement.domain;

import org.junit.jupiter.api.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class MeetingTest {

    Date date=new Date(System.currentTimeMillis());
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("hh:mm");
    Date time = simpleDateFormat.parse("10:30");

    public MeetingTest() throws ParseException {
    }

    @Test
    void ensureMeetingMustHaveDuration() {
        System.out.println("must have meeting duration");

        assertThrows(IllegalArgumentException.class, () -> new Meeting(null, new MeetingDate(date), new MeetingTime(time)));
    }

    @Test
    void ensureMeetingMustHaveDate() {
        System.out.println("must have exam date");

        assertThrows(IllegalArgumentException.class, () -> new Meeting(new MeetingDuration(30), null, new MeetingTime(time)));
    }

    @Test
    void ensureMeetingMustHaveTime() {
        System.out.println("must have exam course");

        assertThrows(IllegalArgumentException.class, () -> new Meeting(new MeetingDuration(30), new MeetingDate(date), null));
    }
}
