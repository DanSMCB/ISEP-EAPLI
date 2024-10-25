package eapli.base.Student_Teacher.Teacher.Domain;

import lombok.*;

import javax.persistence.Embeddable;
import java.util.Objects;

@Embeddable
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@Getter
@EqualsAndHashCode
public class Acronym
{
    private String value;
    public Acronym(String value) {
        this.value = value;
    }

    public static Acronym valueOf(String value) {
        return new Acronym(value.toUpperCase());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Acronym acronym = (Acronym) o;
        return value.equals(acronym.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    @Override
    public String toString() {
        return value;
    }







        }
