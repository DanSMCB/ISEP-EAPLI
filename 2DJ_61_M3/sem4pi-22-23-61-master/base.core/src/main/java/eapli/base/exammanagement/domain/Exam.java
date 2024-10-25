package eapli.base.exammanagement.domain;

import eapli.base.Course.Domain.Course;
import eapli.base.Student_Teacher.Student.domain.Student;
import eapli.framework.domain.model.AggregateRoot;
import eapli.framework.domain.model.DomainEntities;
import eapli.framework.validations.Preconditions;
import lombok.*;

import javax.persistence.Embeddable;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Embeddable
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@EqualsAndHashCode
public class Exam implements AggregateRoot<Long>
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private ExamResult result;
    private ExamTime examTime;
    private ExamDate examDate;
    private Course examCourse;

    public Exam(ExamTime examTime, ExamDate examDate, Course examCourse){
        Preconditions.noneNull(id, result, examDate);

        this.result=null;
        this.examTime=examTime;
        this.examDate=examDate;
        this.examCourse=examCourse;
    }

    public void setResult(ExamResult examResult){
        this.result=examResult;
    }


    @Override
    public boolean equals(Object o) {
        return DomainEntities.areEqual(this, o);
    }

    @Override
    public boolean sameAs(Object other) {
        Exam exam = (Exam) other;
        return this.equals(exam) && getExamCourse().equals(exam.getExamCourse()) && getExamDate().equals(exam.getExamDate()) && getExamTime().equals(exam.getExamTime()) && getResult().equals(exam.getResult());
    }

    @Override
    public String toString() {
        return id.toString();
    }


    @Override
    public Long identity() {
        return this.id;
    }
}