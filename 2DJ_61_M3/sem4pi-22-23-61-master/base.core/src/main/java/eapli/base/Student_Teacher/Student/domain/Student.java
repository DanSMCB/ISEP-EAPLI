package eapli.base.Student_Teacher.Student.domain;


import eapli.base.Student_Teacher.Date_Of_Birth;
import eapli.base.Student_Teacher.Tax_Payer_Number;
import eapli.base.clientusermanagement.domain.MecanographicNumber;
import eapli.framework.domain.model.AggregateRoot;
import eapli.framework.domain.model.DomainEntities;
import eapli.framework.infrastructure.authz.domain.model.Role;
import eapli.framework.infrastructure.authz.domain.model.SystemUser;
import eapli.framework.infrastructure.authz.domain.model.Username;
import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@ToString
@EqualsAndHashCode
@Inheritance(strategy = InheritanceType.JOINED)
public class Student extends SystemUser implements AggregateRoot<Username>{

    @EmbeddedId
    private MecanographicNumber mecanographicNumber;

    @Embedded
    private Tax_Payer_Number taxPayerNumber;

    @Embedded
    private eapli.base.Student_Teacher.Date_Of_Birth dateOfBirth;

    private Set<Role> roles = new HashSet<>();

    public Student(MecanographicNumber mecanographicNumber, Tax_Payer_Number tax_payer_number, Date_Of_Birth dateOfBirth){
        super();
        this.mecanographicNumber=mecanographicNumber;
        this.taxPayerNumber=tax_payer_number;
        this.dateOfBirth=dateOfBirth;
    }

    @Override
    public String toString() {
        return "Student{" +
                "mechanographicNumber=" + mecanographicNumber +
                ", taxPayerNumber=" + taxPayerNumber +
                ", dateOfBirth=" + dateOfBirth +
                '}';
    }

    public MecanographicNumber getMechanographicNumber() {
        return mecanographicNumber;
    }

    public Tax_Payer_Number getTaxPayerNumber() {
        return this.taxPayerNumber;
    }


    @Override
    public boolean sameAs(Object other) {
        Student student = (Student) other;
        return this.equals(student) && getTaxPayerNumber().equals(student.getTaxPayerNumber()) && student.getDateOfBirth().equals(student.getDateOfBirth());
    }

    private Date_Of_Birth getDateOfBirth() {
        return dateOfBirth;
    }

    @Override
    public boolean equals(final Object o) {
        return DomainEntities.areEqual(this, o);
    }
}
