package eapli.base.Classe.domain;

import eapli.framework.domain.model.ValueObject;
import eapli.framework.validations.Preconditions;
import lombok.*;

import javax.persistence.Embeddable;
import java.time.LocalTime;

@Embeddable
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Setter
@EqualsAndHashCode
public class Classe_Finish_Time implements ValueObject {
    private LocalTime finish_time;
    public static Classe_Finish_Time from(LocalTime finish_time) throws IllegalArgumentException{
        try {
            Preconditions.ensure(finish_time != null, "Invalid finish time");


        } catch (Exception e) {
            throw new IllegalArgumentException(e);
        }
        return new Classe_Finish_Time(finish_time);
    }
    public static Classe_Finish_Time valueOf(LocalTime finish_time) {
        return new Classe_Finish_Time(finish_time);
    }
    @Override
    public String toString() {
        return String.format("Finish Time: %s", finish_time);

    }
    public LocalTime getFinish_time() {
        return finish_time;
    }
}



