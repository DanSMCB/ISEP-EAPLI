package eapli.base.Course.Domain;
import javax.persistence.*;
import lombok.*;


@Embeddable
@Getter
@Setter
public class Small_Textual_Description {
    
    private String value;

    public Small_Textual_Description(String value){
        this.value=value;
    }

}
