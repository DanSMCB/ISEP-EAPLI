package eapli.base.exammanagement.domain;

import eapli.framework.domain.model.ValueObject;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Embeddable;
import java.time.LocalDate;
import java.util.Date;

@Embeddable
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@EqualsAndHashCode
public class ExamDate implements ValueObject {

    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private Date examDate;

    public ExamDate(Date examDate) {
        if (!isValidExamDate(examDate)) {
            throw new IllegalArgumentException("Invalid exam date format");
        }
        this.examDate=examDate;
    }

    private static boolean isValidExamDate(Date examDate) {
        Date date = new Date(System.currentTimeMillis());
        return examDate.after(date);
    }

    @Override
    public String toString() {
        return examDate.toString();
    }
}