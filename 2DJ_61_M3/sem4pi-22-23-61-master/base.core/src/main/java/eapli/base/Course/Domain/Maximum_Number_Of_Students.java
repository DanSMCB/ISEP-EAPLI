package eapli.base.Course.Domain;
import javax.persistence.*;
import lombok.*;

@Embeddable
@Getter
@Setter
public class Maximum_Number_Of_Students {

    private int value;

    public Maximum_Number_Of_Students(int value){
        this.value=value;
    }

}
