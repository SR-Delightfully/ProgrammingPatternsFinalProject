package org.example;

public class FrenchMessageProvider implements MessageProvider {
    @Override public String getInformationError() { return "Ce compte n'existe pas.";}
    @Override public String getUsernameError() { return "Le nom d'utilisateur fourni n'existe pas.";}
    @Override public String getPasswordError() { return "Le mot de passe fourni ne correspond pas au compte.";}
    @Override public String getMisMatchError() {return "les champs du mot de pass ne correspondent pas.";}
    @Override public String getUsernameInvalidError() {return "Le nom d'utilisateur fourni n'est pas valide";}
    @Override public String getUsernameExistsError() {return "Le nom d'utilisateur fourni existe deja.";}
    @Override public String getPasswordInvalidError() {return "le mot de passe fourni n'est pas valide.";}
    @Override public String getEmailInvalidError() { return "l'adresse courriel fourni n'ext pas valide.";}
    @Override public String getAllFieldsRequired() { return "Tous les champs sont obligatoires.";}
    @Override public String getLoginSuccess() { return "Connection réussie!";}
    @Override public String getSignupSuccess() { return "Inscription réussie!";}
    @Override public String getCardsMissing() { return "Aucune carte trouvée pour ce type de jeu.";}
    @Override public String getNoCardFound() { return "Aucune carte a été trouvée.";}
    @Override public String getLanguageLabel() { return "Langue :"; }
    @Override public String getWelcomeTitle() { return "Bienvenue à Card Collector"; }
    @Override public String getWelcomeSubtitle() { return "Un système de gestion pour divers jeux de cartes à collectionner !"; }
    @Override public String getLoginPrompt() { return "Vous avez déjà un compte ?"; }
    @Override public String getSignupPrompt() { return "Vous n'avez pas de compte ?"; }
    @Override public String getLoginButtonLabel() { return "Connexion"; }
    @Override public String getSignupButtonLabel() { return "S'inscrire"; }
    // Login Screen
    @Override public String getLoginHeader() { return "Connexion"; }
    @Override public String getUsernameLabel() { return "Nom d'utilisateur:"; }
    @Override public String getPasswordLabel() { return "Mot de passe:"; }
    @Override public String getLoginButton() { return "Se connecter"; }
    @Override public String getBackButton() { return "Retour"; }
    // Signup Screen
    @Override public String getSignupHeader() { return "Inscription"; }
    @Override public String getEmailLabel() { return "Email:"; }
    @Override public String getConfirmPasswordLabel() { return "Confirmez le mot de passe:"; }
    @Override public String getSignupButton() { return "S'inscrire"; }
    // Game Types Screen
    public String getGameTypeTitle() { return "Choisissez un type de jeu"; }
    public String getGameTypeSubtitle() { return "Consultez la base de données du jeu d'un simple clic !"; }
    public String getMagicTheGatheringLabel() { return "Magic: The Gathering"; }
    public String getPokemonLabel() { return "Pokémon"; }

    // Gallery Screen
    public String getFilterTypeLabel() { return "Filtrer par type :"; }
    public String getFilterStageLabel() { return "Filtrer par étape :"; }
    public String getFilterByColorLabel() { return "Filtrer par couleur :"; }
    public String getFilterByTypeLabel() { return "Filtrer par type :"; }
    public String getSearchButtonLabel() { return "Chercher"; }
    public String getTotalCardsLabel() { return "Total des cartes : 0"; }
}
