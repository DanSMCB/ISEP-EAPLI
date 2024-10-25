package eapli.base.exammanagement.domain;

import eapli.framework.domain.model.ValueObject;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Embeddable;
import java.sql.Time;
import java.time.LocalTime;
import java.util.Date;

@Embeddable
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@EqualsAndHashCode
public class ExamTime implements ValueObject {

    @DateTimeFormat(pattern = "hh:mm")
    private Date openDate;
    @DateTimeFormat(pattern = "hh:mm")
    private Date closeDate;

    public ExamTime(Date openDate, Date closeDate) {
        if (!isValidExamTime(openDate, closeDate)) {
            throw new IllegalArgumentException("Invalid exam time format");
        }
        this.openDate=openDate;
        this.closeDate=closeDate;
    }

    private static boolean isValidExamTime(Date openDate, Date closeDate) {
        return closeDate.after(openDate);
    }

    @Override
    public String toString() {
        return openDate.toString() + " - " + closeDate.toString();
    }
}