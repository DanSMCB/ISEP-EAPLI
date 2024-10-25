package eapli.base.Student_Teacher.Teacher.Domain;
import eapli.base.Student_Teacher.Date_Of_Birth;
import eapli.base.Student_Teacher.Tax_Payer_Number;
import eapli.framework.domain.model.AggregateRoot;
import eapli.framework.infrastructure.authz.domain.model.SystemUser;
import eapli.framework.infrastructure.authz.domain.model.Username;
import lombok.*;

import javax.persistence.*;
import java.util.Objects;

@Entity
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@ToString
@EqualsAndHashCode
@Inheritance(strategy = InheritanceType.JOINED)
public class Teacher extends SystemUser implements AggregateRoot<Username>
{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(unique = true)
    private Acronym acronym;
    private Tax_Payer_Number tax_payer_number;
    private Date_Of_Birth Date_of_Birth;

    public Acronym getAcronym() {
        return this.acronym;
    }
    @Override
    public boolean sameAs(Object other)
    {
        return Objects.equals(this.getId(), ((Teacher) other).getId());
    }

    @Override
    public String toString(){return acronym.toString();}
}
