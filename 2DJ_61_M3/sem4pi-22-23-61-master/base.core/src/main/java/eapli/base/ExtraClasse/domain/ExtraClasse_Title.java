package eapli.base.ExtraClasse.domain;

import eapli.base.Classe.domain.Classe_Start_Time;
import eapli.base.Classe.domain.Classe_Title;
import eapli.framework.domain.model.ValueObject;
import eapli.framework.validations.Preconditions;

public class ExtraClasse_Title  extends Classe_Title implements ValueObject {
    private String title;
    public static eapli.base.ExtraClasse.domain.ExtraClasse_Title from(String title) throws Exception{
        try{
            Preconditions.ensure((title == null), "Title invalido");

        }
        catch (Exception e){
            throw new Exception(e);
        }
        return new eapli.base.ExtraClasse.domain.ExtraClasse_Title();
    }
    public static ExtraClasse_Title valueOf(String title) throws Exception{
        return from(title);
    }


    @Override
    public String toString() {
        return String.format("Title : ", title);
    }
}

