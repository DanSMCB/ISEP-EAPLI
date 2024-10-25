package eapli.base.app.backoffice.console.presentation.Classe;

import eapli.base.Classe.aplication.ClassController;
import eapli.framework.actions.Action;

public class UpdateClassAction implements Action {
    private final ClassController classController;

    public UpdateClassAction(ClassController classController) {
        this.classController = classController;
    }
    public boolean execute(){
        try {
            new UpdateClassScheduleUI(classController).run();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return true; // Return a boolean indicating if the operation was successful.
    }
}
