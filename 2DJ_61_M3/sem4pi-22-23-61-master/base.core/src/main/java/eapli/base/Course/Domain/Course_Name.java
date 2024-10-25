package eapli.base.Course.Domain;
import javax.persistence.*;
import lombok.*;

@Embeddable
@Getter
@Setter
@NoArgsConstructor
public class Course_Name {
    private String value;

    public Course_Name(String value){
        this.value=value;
    }

}
