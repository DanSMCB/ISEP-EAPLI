package eapli.base.ExtraClasse.domain;

import eapli.base.Classe.domain.Classe_Finish_Time;
import eapli.framework.domain.model.ValueObject;
import eapli.framework.validations.Preconditions;
import lombok.*;

import javax.persistence.Embeddable;
import java.time.LocalTime;

@Setter
@Getter
@Embeddable
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EqualsAndHashCode
public class ExtraClasse_Finish_Time extends Classe_Finish_Time implements ValueObject {
    private LocalTime finish_time;
    public static ExtraClasse_Finish_Time from(LocalTime finish_time) throws IllegalArgumentException{
        try {
            Preconditions.ensure(finish_time != null, "Invalid finish time");


        } catch (Exception e) {
            throw new IllegalArgumentException(e);
        }
        return new ExtraClasse_Finish_Time(finish_time);
    }
    public static ExtraClasse_Finish_Time valueOf(LocalTime finish_time) {
        return new ExtraClasse_Finish_Time(finish_time);
    }
    @Override
    public String toString() {
        return String.format("Finish Time: %s", finish_time);

    }
    public LocalTime getFinish_time() {
        return finish_time;
    }
}
