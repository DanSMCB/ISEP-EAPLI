package eapli.base.Classe.domain;

import eapli.framework.domain.model.ValueObject;
import eapli.framework.validations.Preconditions;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;

@Embeddable
@EqualsAndHashCode
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class DayOfWeek implements ValueObject {

    private int day;

    public static DayOfWeek from(int day) throws IllegalArgumentException {
        Preconditions.ensure(day >= 1 && day <= 7, "Invalid day of week");
        return new DayOfWeek(day);
    }

    public static DayOfWeek valueOf(int day) throws IllegalArgumentException {
        return from(day);
    }

    @Override
    public String toString() {
        return String.format("Day of Week: %d", day);
    }
}

