package eapli.base.ExtraClasse.domain;

import eapli.base.Classe.domain.DayOfWeek;
import eapli.framework.domain.model.ValueObject;
import eapli.framework.validations.Preconditions;
import lombok.*;

import javax.persistence.Embeddable;

@Embeddable
@EqualsAndHashCode
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class ExtraClasse_Day extends eapli.base.Classe.domain.DayOfWeek implements ValueObject {

    private int day;

    public static eapli.base.Classe.domain.DayOfWeek from(int day) throws IllegalArgumentException {
        Preconditions.ensure(day >= 1 && day <= 7, "Invalid day of week");
        return new eapli.base.ExtraClasse.domain.ExtraClasse_Day(day);
    }

    public static DayOfWeek valueOf(int day) throws IllegalArgumentException {
        return from(day);
    }

    @Override
    public String toString() {
        return String.format("Day of Week: %d", day);
    }
}

