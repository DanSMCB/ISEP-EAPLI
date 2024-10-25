/*
 * Copyright (c) 2013-2023 the original author or authors.
 *
 * MIT License
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
package eapli.base.app.backoffice.console.presentation;

import eapli.base.Classe.aplication.ClassController;
import eapli.base.Classe.repository.ClassRepository;
import eapli.base.Classe.repository.ClassRepositoryImpl;
import eapli.base.ExtraClasse.aplication.ExtraClassController;
import eapli.base.app.backoffice.console.presentation.Classe.ScheduleClassAction;
import eapli.base.app.backoffice.console.presentation.Classe.ScheduleExtraClassAction;
import eapli.base.app.backoffice.console.presentation.Classe.UpdateClassAction;
import eapli.base.app.backoffice.console.presentation.authz.AddUserAction;
import eapli.base.app.backoffice.console.presentation.exam.ListCourseExamsAction;
import eapli.base.app.backoffice.console.presentation.exam.ListFutureExamsAction;
import eapli.base.app.backoffice.console.presentation.meeting.ListInvitesAction;
import eapli.base.app.backoffice.console.presentation.meeting.ScheduleMeetingAction;
import eapli.base.app.common.console.presentation.authz.MyUserMenu;
import eapli.base.Application;
import eapli.base.app.backoffice.console.presentation.authz.AddUserUI;
import eapli.base.app.backoffice.console.presentation.authz.DeactivateUserAction;
import eapli.base.app.backoffice.console.presentation.authz.ListUsersAction;
import eapli.base.app.backoffice.console.presentation.clientuser.AcceptRefuseSignupRequestAction;
import eapli.base.usermanagement.domain.BaseRoles;
import eapli.framework.actions.Actions;
import eapli.framework.actions.menu.Menu;
import eapli.framework.actions.menu.MenuItem;
import eapli.framework.infrastructure.authz.application.AuthorizationService;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;
import eapli.framework.presentation.console.AbstractUI;
import eapli.framework.presentation.console.ExitWithMessageAction;
import eapli.framework.presentation.console.ShowMessageAction;
import eapli.framework.presentation.console.menu.HorizontalMenuRenderer;
import eapli.framework.presentation.console.menu.MenuItemRenderer;
import eapli.framework.presentation.console.menu.MenuRenderer;
import eapli.framework.presentation.console.menu.VerticalMenuRenderer;

/**
 * TODO split this class in more specialized classes for each menu
 *
 * @author Paulo Gandra Sousa
 *
 * Menu do usuário do backoffice
 */
public class MainMenu extends AbstractUI {

    private static final String RETURN_LABEL = "Return ";

    private static final int EXIT_OPTION = 0;

    // USERS - MENU
    private static final int ADD_USER_OPTION = 1;
    private static final int LIST_USERS_OPTION = 2;
    private static final int DEACTIVATE_USER_OPTION = 3;
    private static final int ACCEPT_REFUSE_SIGNUP_REQUEST_OPTION = 4;

    // SETTINGS - MENU
    private static final int UNDERDEVELOPMENT_MESSAGE = 1;

    // EXAMS - MENU
    private static final int SEE_COURSE_EXAMS = 1;
    private static final int SEE_FUTURE_EXAMS = 1;
    private static final int ADMIN_SEE_FUTURE_EXAMS = 2;

    // CLASS - MENU
    private static final int SCHEDULE_CLASSES = 1;
    private static final int UPDATE_CLASSES = 2;
    private static final int SCHEDULE_EXTRA_CLASS = 3;

    // MAIN MENU
    private static final int MY_USER_OPTION = 1;
    private static final int USERS_OPTION = 2;
    private static final int EXAM_MENU = 3;
    private static final int MEETINGS_MENU = 4;
    private static final int CLASS_MENU = 5;
    private static final int SETTINGS_OPTION = 6;
    private static final int SCHEDULE_MEETING = 1;
    private static final int LIST_MEETING_INVITES = 2;

    private static final String SEPARATOR_LABEL = "--------------";

    private final AuthorizationService authz = AuthzRegistry.authorizationService();

    @Override
    public boolean show() {
        drawFormTitle();
        return doShow();
    }

    /**
     * @return true if the user selected the exit option
     */
    @Override
    public boolean doShow() {
        final Menu menu = buildMainMenu();
        final MenuRenderer renderer;
        if (Application.settings().isMenuLayoutHorizontal()) {
            renderer = new HorizontalMenuRenderer(menu, MenuItemRenderer.DEFAULT);
        } else {
            renderer = new VerticalMenuRenderer(menu, MenuItemRenderer.DEFAULT);
        }
        return renderer.render();
    }

    @Override
    public String headline() {

        return authz.session().map(s -> "Base [ @" + s.authenticatedUser().identity() + " ]")
                .orElse("Base [ ==Anonymous== ]");
    }

    private Menu buildMainMenu() {
        final Menu mainMenu = new Menu();

        final Menu myUserMenu = new MyUserMenu();
        mainMenu.addSubMenu(MY_USER_OPTION, myUserMenu);

        if (!Application.settings().isMenuLayoutHorizontal()) {
            mainMenu.addItem(MenuItem.separator(SEPARATOR_LABEL));
        }

        // Opções do Administrador
        if (authz.isAuthenticatedUserAuthorizedTo(BaseRoles.ADMIN)) {
            final Menu usersMenu = buildUsersMenu();
            mainMenu.addSubMenu(USERS_OPTION, usersMenu);
            final Menu examMenu = buildExamMenu();
            mainMenu.addSubMenu(EXAM_MENU, examMenu);
            final Menu settingsMenu = buildAdminSettingsMenu();
            mainMenu.addSubMenu(SETTINGS_OPTION, settingsMenu);
        }

        // Opções do Manager
        if (authz.isAuthenticatedUserAuthorizedTo(BaseRoles.MANAGER)) {
            final Menu usersMenu = buildUsersMenu();
            mainMenu.addSubMenu(USERS_OPTION, usersMenu);
            final Menu settingsMenu = buildAdminSettingsMenu();
            mainMenu.addSubMenu(SETTINGS_OPTION, settingsMenu);
        }

        // Opções do Teacher & Student do Exame
        if(authz.isAuthenticatedUserAuthorizedTo(BaseRoles.TEACHER, BaseRoles.STUDENT)){
            final Menu examMenu = buildExamMenu();
            mainMenu.addSubMenu(EXAM_MENU, examMenu);
        }
        if(authz.isAuthenticatedUserAuthorizedTo(BaseRoles.TEACHER)){
            final Menu classMenu = buildClassMenu();
            mainMenu.addSubMenu(CLASS_MENU, classMenu);
        }

        // Opções do Meetings
        final Menu meetingMenu = buildMeetingMenu();
        mainMenu.addSubMenu(MEETINGS_MENU, meetingMenu);

        if (!Application.settings().isMenuLayoutHorizontal()) {
            mainMenu.addItem(MenuItem.separator(SEPARATOR_LABEL));
        }

        mainMenu.addItem(EXIT_OPTION, "Exit", new ExitWithMessageAction("Exiting the application... Bye!"));

        return mainMenu;
    }

    private Menu buildExamMenu(){
        final Menu menu = new Menu("Exam >");

        if(authz.isAuthenticatedUserAuthorizedTo(BaseRoles.TEACHER, BaseRoles.ADMIN)){
            menu.addItem(SEE_COURSE_EXAMS, "View list of all exams in a course", new ListCourseExamsAction());
        }

        if(authz.isAuthenticatedUserAuthorizedTo(BaseRoles.STUDENT)){
            menu.addItem(SEE_FUTURE_EXAMS, "View list of all my future exams", new ListFutureExamsAction());
        }

        // ID Option diferente para o admin para evitar conflitos e erros de execução
        if(authz.isAuthenticatedUserAuthorizedTo(BaseRoles.ADMIN)){
            menu.addItem(ADMIN_SEE_FUTURE_EXAMS, "View list of all my future exams", new ListFutureExamsAction());
        }

        menu.addItem(EXIT_OPTION, RETURN_LABEL, Actions.SUCCESS);

        return menu;
    }

    private Menu buildClassMenu(){
        final Menu menu = new Menu("Class >");
        ClassRepository classRepository = new ClassRepositoryImpl();
        ClassController classController = new ClassController(classRepository);
        ExtraClassController extraClassController = new ExtraClassController(classRepository);
        if(authz.isAuthenticatedUserAuthorizedTo(BaseRoles.TEACHER, BaseRoles.ADMIN)){
            menu.addItem(SCHEDULE_CLASSES, "Schedule a new class",new ScheduleClassAction(classController));

        }
        if(authz.isAuthenticatedUserAuthorizedTo(BaseRoles.TEACHER, BaseRoles.ADMIN)){
            menu.addItem(UPDATE_CLASSES, "Update an existing class", new UpdateClassAction(classController));

        }
        if(authz.isAuthenticatedUserAuthorizedTo(BaseRoles.TEACHER, BaseRoles.ADMIN)){
            menu.addItem(SCHEDULE_EXTRA_CLASS, "Schedule a new extra class", new ScheduleExtraClassAction(extraClassController));
            menu.addItem(EXIT_OPTION, RETURN_LABEL, Actions.SUCCESS);
        }
        return menu;
    }

    private Menu buildMeetingMenu(){
        final Menu menu = new Menu("Meeting >");

        menu.addItem(SCHEDULE_MEETING, "Schedule a meeting", new ScheduleMeetingAction());
        menu.addItem(LIST_MEETING_INVITES, "View list of meetings I've been invited to", new ListInvitesAction());
        menu.addItem(EXIT_OPTION, RETURN_LABEL, Actions.SUCCESS);

        return menu;
    }

    private Menu buildAdminSettingsMenu() {
        final Menu menu = new Menu("Settings >");

        menu.addItem(UNDERDEVELOPMENT_MESSAGE, "Testing Option", new ShowMessageAction("This option is under development"));
        menu.addItem(EXIT_OPTION, RETURN_LABEL, Actions.SUCCESS);

        return menu;
    }

    private Menu buildUsersMenu() {
        final Menu menu = new Menu("Users >");

        menu.addItem(ADD_USER_OPTION, "Add User", new AddUserAction());
        menu.addItem(LIST_USERS_OPTION, "List all Users", new ListUsersAction());
        menu.addItem(DEACTIVATE_USER_OPTION, "Deactivate User", new DeactivateUserAction());
        menu.addItem(ACCEPT_REFUSE_SIGNUP_REQUEST_OPTION, "Accept/Refuse Signup Request", new AcceptRefuseSignupRequestAction());
        menu.addItem(EXIT_OPTION, RETURN_LABEL, Actions.SUCCESS);

        return menu;
    }





}
