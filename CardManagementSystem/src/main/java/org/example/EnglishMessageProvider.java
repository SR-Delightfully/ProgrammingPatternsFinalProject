package org.example;

public class EnglishMessageProvider implements MessageProvider {
    @Override public String getInformationError() { return "this account does not exist";}
    @Override public String getUsernameError() { return "The username provided does not exist.";}
    @Override public String getPasswordError() { return "The password provided does not match the account.";}
    @Override public String getMisMatchError() {return "The password fields do not match.";}
    @Override public String getUsernameInvalidError() {return "The username provided is not valid.";}
    @Override public String getUsernameExistsError() {return "The username provided already exists.";}
    @Override public String getPasswordInvalidError() {return "The password provided is not valid.";}
    @Override public String getEmailInvalidError() { return "The email provided is not valid.";}
    @Override public String getAllFieldsRequired() { return "All fields are required."; }
    @Override public String getLoginSuccess() { return "Login successful!";}
    @Override public String getSignupSuccess() { return "Signup successful!";}
    @Override public String getCardsMissing() { return "No cards found for this game type.";}
    @Override public String getNoCardFound() { return "No card found.";}
    @Override public String getLanguageLabel() { return "Language:"; }
    @Override public String getWelcomeTitle() { return "Welcome to Card Collector"; }
    @Override public String getWelcomeSubtitle() { return "A management system for various collectible card games!"; }
    @Override public String getLoginPrompt() { return "Already have an account?"; }
    @Override public String getSignupPrompt() { return "Don't have an account?"; }
    @Override public String getLoginButtonLabel() { return "Login"; }
    @Override public String getSignupButtonLabel() { return "Sign Up"; }
    // Login Screen
    @Override public String getLoginHeader() { return "Login"; }
    @Override public String getUsernameLabel() { return "Username:"; }
    @Override public String getPasswordLabel() { return "Password:"; }
    @Override public String getLoginButton() { return "Log In"; }
    @Override public String getBackButton() { return "Back"; }
    // Signup Screen
    @Override public String getSignupHeader() { return "Sign Up"; }
    @Override public String getEmailLabel() { return "Email:"; }
    @Override public String getConfirmPasswordLabel() { return "Confirm Password:"; }
    @Override public String getSignupButton() { return "Sign Up"; }
    // Game Types Screen
    @Override public String getGameTypeTitle() { return "Choose a Game Type"; }
    @Override public String getGameTypeSubtitle() { return "View the game's database with a single click!"; }
    @Override public String getMagicTheGatheringLabel() { return "Magic: The Gathering"; }
    @Override public String getPokemonLabel() { return "Pokémon"; }

    // Gallery Screen
    @Override public String getFilterTypeLabel() { return "Filter Type:"; }
    @Override public String getFilterStageLabel() { return "Filter Stage:"; }
    @Override public String getFilterByColorLabel() { return "Filter by Color:"; }
    @Override public String getFilterByTypeLabel() { return "Filter by Type:"; }
    @Override public String getSearchButtonLabel() { return "Search"; }
    @Override public String getTotalCardsLabel() { return "Total cards: 0"; }
}

