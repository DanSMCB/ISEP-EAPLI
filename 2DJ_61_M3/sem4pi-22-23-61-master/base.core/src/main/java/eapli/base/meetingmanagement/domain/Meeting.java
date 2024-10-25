package eapli.base.meetingmanagement.domain;

import eapli.base.Student_Teacher.Student.domain.Student;
import eapli.base.exammanagement.domain.Exam;
import eapli.framework.domain.model.AggregateRoot;
import eapli.framework.domain.model.DomainEntities;
import eapli.framework.validations.Preconditions;
import lombok.*;

import javax.persistence.Embeddable;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.List;

@Embeddable
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@EqualsAndHashCode
public class Meeting implements AggregateRoot<Long> {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private MeetingDuration meetingDuration;

    private MeetingDate meetingDate;

    private MeetingTime meetingTime;

    private List<Student> studentList;

    public Meeting (MeetingDuration meetingDuration, MeetingDate meetingDate, MeetingTime meetingTime){
        Preconditions.noneNull(meetingDuration, meetingDate, meetingTime);

        this.meetingDuration=meetingDuration;
        this.meetingDate=meetingDate;
        this.meetingTime=meetingTime;
        this.studentList=null;
    }

    @Override
    public boolean equals(Object o) {
        return DomainEntities.areEqual(this, o);
    }

    @Override
    public boolean sameAs(Object other) {
        Meeting meeting = (Meeting) other;
        return this.equals(meeting) && getMeetingDate().equals(meeting.getMeetingDate()) && getMeetingDuration().equals(meeting.getMeetingDuration()) && getMeetingTime().equals(meeting.getMeetingTime());
    }

    public void addStudent(Student student){
        studentList.add(student);
    }

    @Override
    public Long identity() {
        return id;
    }
}
