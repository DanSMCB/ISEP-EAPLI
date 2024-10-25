package eapli.base.Course.Domain;
import javax.persistence.*;
import lombok.*;


@Embeddable
@Getter
@Setter
public class Minimum_Number_Of_Students {

    private int value;

    public Minimum_Number_Of_Students(int value){
        this.value=value;
    }

}