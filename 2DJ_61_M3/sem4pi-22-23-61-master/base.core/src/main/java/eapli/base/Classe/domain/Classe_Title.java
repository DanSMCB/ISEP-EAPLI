package eapli.base.Classe.domain;
import eapli.framework.domain.model.ValueObject;
import eapli.framework.validations.Preconditions;
import lombok.*;

import javax.persistence.Embeddable;

@Embeddable
@EqualsAndHashCode
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Classe_Title implements ValueObject {

    private String title;

    public static eapli.base.Classe.domain.Classe_Title from(String title) throws Exception {
        Preconditions.ensure(title != null && !title.isEmpty(), "Title invalido");
        return new eapli.base.Classe.domain.Classe_Title(title);
    }

    public static Classe_Title valueOf(String title) throws Exception {
        return from(title);
    }

    @Override
    public String toString() {
        return String.format("Title : ", title);
    }
}
