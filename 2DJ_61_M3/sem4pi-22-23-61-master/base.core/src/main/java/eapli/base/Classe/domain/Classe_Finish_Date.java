package eapli.base.Classe.domain;

import eapli.framework.domain.model.ValueObject;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.time.LocalDate;

@Embeddable
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@Getter
@EqualsAndHashCode
public class Classe_Finish_Date implements ValueObject {
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(nullable = false)
    private LocalDate finish_date;

    public static Classe_Finish_Date valueOf(LocalDate finish_date) {
        return new Classe_Finish_Date(finish_date);
    }

    @Override
    public String toString() {
        return finish_date.toString();
    }
}
