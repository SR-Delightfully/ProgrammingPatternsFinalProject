package org.example;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.text.Font;
import javafx.scene.paint.Color;

public class GUI extends Application {

    private Stage primaryStage;

    @Override
    public void start(Stage stage) {
        this.primaryStage = stage;
        showWelcomeScreen();
    }

    private void showWelcomeScreen() {
        Label title = new Label("Welcome to Card Collector");
        title.setFont(new Font("Arial", 24));
        title.setTextFill(Color.DARKBLUE);

        VBox loginCard = new VBox();
        loginCard.setOnMouseClicked(e -> showLoginScreen());

        VBox signupCard = new VBox();
        signupCard.setOnMouseClicked(e -> showSignupScreen());

        HBox cards = new HBox(40, loginCard, signupCard);
        cards.setAlignment(Pos.CENTER);

        VBox root = new VBox(40, title, cards);
        root.setAlignment(Pos.CENTER);
        root.setPadding(new Insets(50));

        Scene scene = new Scene(root, 500, 400);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Card Collector - Welcome");
        primaryStage.show();
    }

    private VBox createCard(String text) {
        Label label = new Label(text);
        label.setFont(Font.font("Arial", 16));
        VBox card = new VBox(label);
        card.setAlignment(Pos.CENTER);
        card.setPadding(new Insets(20));
        card.setStyle("-fx-border-color: black; -fx-border-width: 2; -fx-background-color: lightgray;");  // Styled card
        return card;
    }

    private void showLoginScreen() {
        Label header = new Label("Login");
        header.setFont(Font.font("Arial", 20));

        TextField username = new TextField();
        username.setPromptText("Username");

        PasswordField password = new PasswordField();
        password.setPromptText("Password");

        Button login = new Button("Log In");
        Button back = new Button("Back");
        back.setOnAction(e -> showWelcomeScreen());

        VBox root = new VBox(10, header, username, password, login, back);
        root.setAlignment(Pos.CENTER);
        root.setPadding(new Insets(30));

        Scene scene = new Scene(root, 400, 300);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Card Collector - Login");
    }

    private void showSignupScreen() {
        Label header = new Label("Sign Up");
        header.setFont(Font.font("Arial", 20));

        TextField username = new TextField();
        username.setPromptText("Username");

        PasswordField password = new PasswordField();
        password.setPromptText("Password");

        PasswordField confirmPassword = new PasswordField();
        confirmPassword.setPromptText("Confirm Password");

        Button signup = new Button("Sign Up");
        Button back = new Button("Back");
        back.setOnAction(e -> showWelcomeScreen());

        VBox root = new VBox(10, header, username, password, confirmPassword, signup, back);
        root.setAlignment(Pos.CENTER);
        root.setPadding(new Insets(30));

        Scene scene = new Scene(root, 400, 350);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Card Collector - Sign Up");
    }

    public static void main(String[] args) {
        launch(args);
    }
}