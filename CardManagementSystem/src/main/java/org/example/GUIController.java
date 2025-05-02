package org.example;

import javax.swing.*;

public class GUIController {

    private final GUIview view;
    private final Database db;
    private MessageProvider messageProvider;

    public GUIController(Database db, GUIview view, MessageProvider messageProvider) {
        this.db = db;
        this.view = view;
        this.messageProvider = messageProvider;

        initController();
    }

    /**
     * Initializes the controller and displays the welcome screen from the GUI
     */
    private void initController() {
        System.out.println("Controller initialized");
        showWelcomeScreen();
    }

    /**
     * Used to handle the login form's functionality, will display error if either fields required are empty,
     * or if they contain any invalid inputs
     * @param username refers to how the user will be addressed
     * @param password refers to the password associated with the user
     * @return
     */
    public boolean handleLogin(String username, String password) {
        if (username.isEmpty() || password.isEmpty()) {
            view.displayError(messageProvider.getInformationError());
        } else if (db.validateLogin(username, password)) {
            view.displayMessage(messageProvider.getLoginSuccess());
            view.showGameTypes();
        } else {
            if (!db.userExists(username)) {
                view.displayError(messageProvider.getUsernameExistsError());
            } else {
                view.displayError(messageProvider.getPasswordInvalidError());
            }
        }
        return false;
    }

    /**
     * Used to handle the signup form's functionality, will display error if any fields required are empty,
     * or if they contain any invalid inputs.
     * @param username refers to the name the user would like to be referred to as
     * @param email refers to the user's email address
     * @param password refers to the user's desired password
     * @param confirmPassword a field to ensure that the user correctly inputted their password
     */
    public void handleSignup(String username, String email, String password, String confirmPassword) {
        if (username.isEmpty() || email.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()) {
            view.displayError(messageProvider.getInformationError());
        } else if (!email.matches("^[\\w.-]+@[\\w.-]+\\.[a-zA-Z]{2,}$")) {
            view.displayError(messageProvider.getEmailInvalidError());
        } else if (!password.equals(confirmPassword)) {
            view.displayError(messageProvider.getPasswordInvalidError());
        } else if (db.userExists(username)) {
            view.displayError(messageProvider.getUsernameExistsError());
        } else {
            String id = java.util.UUID.randomUUID().toString();
            try {
                db.addUser(new User(id, username, email, password));
                view.displayMessage(messageProvider.getSignupSuccess());
                view.showLoginScreen();
            } catch (Exception e) {
                view.displayError(messageProvider.getInformationError());
            }
        }
    }

    /**
     * Used to handle changing languages
     * @param language refers to the language in which you would like to switch tp
     */
    public void handleLanguageChange(String language) {
        new SwingWorker<Void, Void>() {
            @Override
            protected Void doInBackground() throws Exception {
                messageProvider = MessageFactory.getProvider(language);
                if (messageProvider == null) {
                    view.displayError(messageProvider.getInformationError());
                } else {
                    view.setMessageProvider(messageProvider);
                }
                return null;
            }

            @Override
            protected void done() {
                view.showWelcomeScreen();
            }
        }.execute();
    }

    /**
     * handles logout functionality and returns the user back to the home screen
     */
    public void handleLogout() {
        view.showWelcomeScreen();
    }

    /**
     * handles the functionality for card fetching depending on the game type of the cards in question
     * @param gameType
     */
    public void handleCardFetch(GameType gameType) {
//        view.fetchAndDisplayCards(gameType, "All", null);
    }

    /**
     * display the GUI for the login screen
     */
    public void showLoginScreen() {
        view.showLoginScreen();
    }

    /**
     * displays the GUI for the signup screen
     */
    public void showSignupScreen() {
        view.showSignupScreen();
    }

    /**
     * displays th GUI for the welcome/home screen
     */
    public void showWelcomeScreen() {
        SwingUtilities.invokeLater(() -> view.showWelcomeScreen());
    }
}
