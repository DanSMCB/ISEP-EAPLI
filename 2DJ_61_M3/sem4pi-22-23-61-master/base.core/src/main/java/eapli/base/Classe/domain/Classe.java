package eapli.base.Classe.domain;

import eapli.base.ExtraClasse.domain.ExtraClasse;
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
public class Classe implements AggregateRoot<Long> {




    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(unique = true)
    private eapli.base.Classe.domain.Classe_Title title;
    private Classe_Start_Date start_date;
    private Classe_Finish_Date finish_date;
    private Classe_Start_Time start_time;
    private Classe_Finish_Time finish_time;
    private DayOfWeek day;
    private Acronym acronym;
   /* @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Teacher> teacher ;

    */

    public void changeTitle(eapli.base.Classe.domain.Classe_Title newTitle) {
        Preconditions.nonNull(newTitle, "Title should not be null");

        this.title = newTitle;
    }
    public void change_startDate(Classe_Start_Date newstartDate) {
        this.start_date = new Classe_Start_Date();
    }
    public void change_finishDate(Classe_Finish_Date newfinishDate) {
        this.finish_date = new Classe_Finish_Date();
    }
    public void change_startTime(Classe_Start_Time newstartTime) {
        this.start_time = new Classe_Start_Time();
    }
    public void change_finishTime(Classe_Finish_Time newfinishTime) {
        this.finish_time = new Classe_Finish_Time();
    }
    @Override
    public Long identity() {
        return this.getId();
    }

    @Override
    public boolean sameAs(Object other) {
        return this.getId() == ((Classe)other).getId();
    }

}

