package eapli.base.exammanagement.domain;

import eapli.framework.domain.model.ValueObject;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@EqualsAndHashCode
public class ExamResult implements ValueObject {

    private float number;

    public ExamResult valueOf(float number) {
        if (!isValidExamResult(number)) {
            throw new IllegalArgumentException("Invalid exam result format");
        }
        return new ExamResult(number);
    }

    private static boolean isValidExamResult(float number) {
        return number>0 && number<20;
    }

//    @Override
//    public String toString() {
//        return number;
//    }
}