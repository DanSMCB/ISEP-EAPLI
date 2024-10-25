package eapli.base.app.backoffice.console.presentation.meeting;

import eapli.framework.actions.Action;

public class ListInvitesAction implements Action {

    @Override
    public boolean execute() {
        return new ListInvitesUI().show();
    }
}
