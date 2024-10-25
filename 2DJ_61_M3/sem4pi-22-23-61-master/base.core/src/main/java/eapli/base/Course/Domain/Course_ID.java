package eapli.base.Course.Domain;

import javax.persistence.*;
import lombok.*;

@Embeddable
@Getter
@Setter
public class Course_ID {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String value;



}
