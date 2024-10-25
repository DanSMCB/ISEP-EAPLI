package eapli.base.ExtraClasse.domain;

import eapli.base.Classe.domain.Classe;
import eapli.base.Classe.domain.Classe_Start_Date;
import eapli.base.ExtraClasse.domain.ExtraClasse_Day;
import eapli.base.Student_Teacher.Teacher.Domain.Acronym;
import eapli.base.Student_Teacher.Teacher.Domain.Teacher;
import eapli.framework.domain.model.AggregateRoot;
import eapli.framework.validations.Preconditions;
import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Entity
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@Getter
@Setter
@ToString
@EqualsAndHashCode
@Inheritance(strategy = InheritanceType.JOINED)
public class ExtraClasse extends Classe implements AggregateRoot<Long> {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(unique = true)
    private ExtraClasse_Title title;
    private ExtraClasse_Start_Time start_time;
    private ExtraClasse_Finish_Time finish_time;

    private ExtraClasse_Day day;
    private Acronym acronym;
   // @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
   // private Set<Teacher> teacher ;
    @Override
    public Long identity() {
        return this.getId();
    }
    @Override

    public boolean sameAs(Object other) {
        return this.getId() == ((ExtraClasse)other).getId();
    }

    @Override
    public int compareTo(Long other) {
        return super.compareTo(other);
    }

}

