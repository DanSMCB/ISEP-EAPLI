package eapli.base.Classe.domain;

import eapli.framework.domain.model.ValueObject;
import eapli.framework.validations.Preconditions;
import lombok.*;

import javax.persistence.Embeddable;
import java.time.LocalDate;
import java.time.LocalTime;

@Setter
@Getter
@Embeddable
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EqualsAndHashCode
public class Classe_Start_Time  implements ValueObject {
    private LocalTime start_time;
    public static Classe_Start_Time from(LocalTime start_time) throws IllegalArgumentException{
        try {
            Preconditions.ensure(start_time != null, "Invalid start time");


        } catch (Exception e) {
            throw new IllegalArgumentException(e);
         }
        return new Classe_Start_Time(start_time);
    }
    public static Classe_Start_Time valueOf(LocalTime start_time) {
        return new Classe_Start_Time(start_time);
    }
    @Override
    public String toString() {
        return String.format("Start Time: %s", start_time);
    }
    public LocalTime getStart_time() {
        return start_time;
    }



}




