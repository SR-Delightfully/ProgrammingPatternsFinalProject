package org.example;

public interface MessageProvider {
    String getInformationError();
    String getUsernameError();
    String getPasswordError();
    String getMisMatchError();
    String getUsernameInvalidError();
    String getUsernameExistsError();
    String getPasswordInvalidError();
    String getEmailInvalidError();
    String getAllFieldsRequired();
    String getLoginSuccess();
    String getSignupSuccess();
    String getCardsMissing();
    String getNoCardFound();
    String getLanguageLabel();
    String getWelcomeTitle();
    String getWelcomeSubtitle();
    String getLoginPrompt();
    String getSignupPrompt();
    String getLoginButtonLabel();
    String getSignupButtonLabel();
    // Login Screen
    String getLoginHeader();
    String getUsernameLabel();
    String getPasswordLabel();
    String getLoginButton();
    String getBackButton();

    // Signup Screen
    String getSignupHeader();
    String getEmailLabel();
    String getConfirmPasswordLabel();
    String getSignupButton();

    // Game Types Screen
    String getGameTypeTitle();
    String getGameTypeSubtitle();
    String getMagicTheGatheringLabel();
    String getPokemonLabel();

    // Gallery Screen
    String getFilterTypeLabel();
    String getFilterStageLabel();
    String getFilterByColorLabel();
    String getFilterByTypeLabel();
    String getSearchButtonLabel();
    String getTotalCardsLabel();

}
