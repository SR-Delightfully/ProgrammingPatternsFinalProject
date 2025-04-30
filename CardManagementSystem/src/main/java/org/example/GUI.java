package org.example;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.net.URL;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class GUI {

    private JFrame frame;
    private JPanel cardPanel;
    private JLabel footerLabel;
    private final Database database;

    public GUI(Database database) {
        frame = new JFrame("Card Collector");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        this.database = database;
        showWelcomeScreen();
    }

    private void showWelcomeScreen() {
        JPanel header = new JPanel();
        header.setLayout(new BoxLayout(header, BoxLayout.Y_AXIS));
        header.setBackground(new Color(47, 36, 71));

        JLabel title = new JLabel("Welcome to Card Collector");
        title.setFont(new Font("Arial", Font.BOLD, 42));
        title.setForeground(new Color(233, 245, 108));
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel subtitle = new JLabel("A management system for various collectible card games!");
        subtitle.setFont(new Font("Arial", Font.BOLD, 24));
        subtitle.setForeground(new Color(127, 116, 182));
        subtitle.setAlignmentX(Component.CENTER_ALIGNMENT);

        header.add(title);
        header.add(Box.createVerticalStrut(10));
        header.add(subtitle);

        // Create cards
        JPanel loginCard = createCard("  Already have an account?  ", "https://i.imgur.com/yXpEDoG.png", "Login");
        loginCard.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                showLoginScreen();
            }
        });

        JPanel signupCard = createCard("  Don't have an account?   ", "https://i.imgur.com/Iq8lriI.png", "Sign Up");
        signupCard.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                showSignupScreen();
            }
        });

        // Center cards horizontally
        JPanel cards = new JPanel();
        cards.setLayout(new BoxLayout(cards, BoxLayout.X_AXIS));
        cards.setBackground(new Color(47, 36, 71));
        cards.setPreferredSize(new Dimension(650, 550));

        cards.add(Box.createHorizontalGlue());
        cards.add(loginCard);
        cards.add(Box.createHorizontalStrut(20));
        cards.add(signupCard);
        cards.add(Box.createHorizontalGlue());

        // Root panel
        JPanel root = new JPanel();
        root.setBackground(new Color(47, 36, 71));
        root.setLayout(new BorderLayout(0, 40));
        root.setBorder(BorderFactory.createEmptyBorder(50, 50, 50, 50));
        root.add(header, BorderLayout.NORTH);
        root.add(cards, BorderLayout.CENTER);

        frame.setContentPane(root);
        frame.setSize(800, 700);
        frame.revalidate();
        frame.repaint();
    }

    private JPanel createCard(String titleText, String imageUrl, String subtitleText) {
        JLabel titleLabel = new JLabel(titleText);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel imageLabel = new JLabel();
        imageLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        try {
            ImageIcon icon = new ImageIcon(new URL(imageUrl));
            int width = 160;
            int height = 160;

            BufferedImage scaledImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
            Graphics2D g2d = scaledImage.createGraphics();
            g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BICUBIC);
            g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2d.drawImage(icon.getImage(), 0, 0, width, height, null);
            g2d.dispose();

            imageLabel.setIcon(new ImageIcon(scaledImage));
        } catch (Exception e) {
            imageLabel.setText("Image not found");
            imageLabel.setHorizontalAlignment(SwingConstants.CENTER);
        }

        // Subtitle in a full-width colored panel
        JPanel subtitlePanel = new JPanel(new BorderLayout());
        subtitlePanel.setBackground(new Color(233, 245, 108));
        subtitlePanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 40));
        subtitlePanel.setPreferredSize(new Dimension(0, 40));

        JLabel subtitleLabel = new JLabel(subtitleText);
        subtitleLabel.setFont(new Font("Arial", Font.PLAIN, 18));
        subtitleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        subtitleLabel.setOpaque(false);
        subtitlePanel.add(subtitleLabel, BorderLayout.CENTER);

        JPanel card = new JPanel();
        card.setLayout(new BoxLayout(card, BoxLayout.Y_AXIS));
        card.setBackground(new Color(127, 116, 182));
        card.setBorder(BorderFactory.createLineBorder(new Color(147, 139, 179), 4, true));
        card.setMaximumSize(new Dimension(288, 423));

        card.add(Box.createVerticalStrut(10));
        card.add(titleLabel);
        card.add(Box.createVerticalStrut(60));
        card.add(imageLabel);
        card.add(Box.createVerticalGlue());
        card.add(subtitlePanel);

        return card;
    }

    private void showLoginScreen() {
        JPanel root = new JPanel();
        root.setLayout(new BoxLayout(root, BoxLayout.Y_AXIS));
        root.setBorder(BorderFactory.createEmptyBorder(30, 50, 30, 50));

        JLabel header = new JLabel("Login");
        header.setFont(new Font("Arial", Font.BOLD, 20));
        header.setAlignmentX(Component.CENTER_ALIGNMENT);

        JTextField username = new JTextField(15);
        JPasswordField password = new JPasswordField(15);
        JLabel errorLabel = new JLabel("");
        errorLabel.setForeground(Color.RED);
        errorLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JButton login = new JButton("Log In");
        login.addActionListener(e -> {
            String user = username.getText().trim();
            String pass = new String(password.getPassword());

            if (user.isEmpty() || pass.isEmpty()) {
                errorLabel.setText("All fields are required.");
            } else if (database.validateLogin(user, pass)) {
                errorLabel.setText("");
                JOptionPane.showMessageDialog(frame, "Login successful!");
                showMainGUI();
            } else {
                errorLabel.setText("Invalid username or password.");
            }
        });

        JButton back = new JButton("Back");
        back.addActionListener(e -> showWelcomeScreen());

        root.add(header);
        root.add(Box.createVerticalStrut(15));
        root.add(new JLabel("Username:"));
        root.add(username);
        root.add(Box.createVerticalStrut(10));
        root.add(new JLabel("Password:"));
        root.add(password);
        root.add(Box.createVerticalStrut(10));
        root.add(errorLabel);
        root.add(Box.createVerticalStrut(10));
        root.add(login);
        root.add(Box.createVerticalStrut(10));
        root.add(back);

        frame.setContentPane(root);
        frame.setSize(400, 300);
        frame.revalidate();
        frame.repaint();
    }

    private void showSignupScreen() {
        JPanel root = new JPanel();
        root.setLayout(new BoxLayout(root, BoxLayout.Y_AXIS));
        root.setBorder(BorderFactory.createEmptyBorder(30, 50, 30, 50));

        JLabel header = new JLabel("Sign Up");
        header.setFont(new Font("Arial", Font.BOLD, 20));
        header.setAlignmentX(Component.CENTER_ALIGNMENT);

        JTextField username = new JTextField(15);
        JTextField email = new JTextField(15);
        JPasswordField password = new JPasswordField(15);
        JPasswordField confirmPassword = new JPasswordField(15);
        JLabel errorLabel = new JLabel("");
        errorLabel.setForeground(Color.RED);
        errorLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JButton signup = new JButton("Sign Up");
        signup.addActionListener(e -> {
            String user = username.getText().trim();
            String mail = email.getText().trim();
            String pass = new String(password.getPassword());
            String confirmPass = new String(confirmPassword.getPassword());

            if (user.isEmpty() || mail.isEmpty() || pass.isEmpty() || confirmPass.isEmpty()) {
                errorLabel.setText("All fields are required.");
            } else if (!isValidEmail(mail)) {
                errorLabel.setText("Invalid email address.");
            } else if (!pass.equals(confirmPass)) {
                errorLabel.setText("Passwords do not match.");
            } else if (database.userExists(user)) {
                errorLabel.setText("Username already exists.");
            } else {
                String id = java.util.UUID.randomUUID().toString();
                database.addUser(new User(id, user, mail, pass));
                JOptionPane.showMessageDialog(frame, "Signup successful!");
                showLoginScreen();
            }
        });

        JButton back = new JButton("Back");
        back.addActionListener(e -> showWelcomeScreen());

        root.add(header);
        root.add(Box.createVerticalStrut(15));
        root.add(new JLabel("Username:"));
        root.add(username);
        root.add(Box.createVerticalStrut(10));
        root.add(new JLabel("Email:"));
        root.add(email);
        root.add(Box.createVerticalStrut(10));
        root.add(new JLabel("Password:"));
        root.add(password);
        root.add(Box.createVerticalStrut(10));
        root.add(new JLabel("Confirm Password:"));
        root.add(confirmPassword);
        root.add(Box.createVerticalStrut(10));
        root.add(errorLabel);
        root.add(Box.createVerticalStrut(10));
        root.add(signup);
        root.add(Box.createVerticalStrut(10));
        root.add(back);

        frame.setContentPane(root);
        frame.setSize(400, 450);
        frame.revalidate();
        frame.repaint();
    }

    private boolean isValidEmail(String email) {
        return email.matches("^[\\w.-]+@[\\w.-]+\\.[a-zA-Z]{2,}$");
    }

    private void showMainGUI() {
        JPanel root = new JPanel(new BorderLayout());
        root.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Header with Title and Logout button
        JPanel header = new JPanel(new BorderLayout());
        JLabel title = new JLabel("My Card Collection");
        title.setFont(new Font("Arial", Font.BOLD, 22));
        JButton logout = new JButton("Logout");
        logout.addActionListener(e -> showWelcomeScreen());
        header.add(title, BorderLayout.WEST);
        header.add(logout, BorderLayout.EAST);

        // Filter Panel with Game Type ComboBox
        JPanel filterPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 10)); // Added space and left alignment
        JLabel filterLabel = new JLabel("Select Game Type:");
        JComboBox<String> gameTypeComboBox = new JComboBox<>(new String[]{"Pokémon", "Magic", "Yu-Gi-Oh!"});

        // Optionally, set a fixed size for the combo box
        gameTypeComboBox.setPreferredSize(new Dimension(200, 30)); // Set size to make it look good

        gameTypeComboBox.addActionListener(e -> {
            String selectedGameType = (String) gameTypeComboBox.getSelectedItem();
            System.out.println("Selected Game Type: " + selectedGameType);

            // Map the ComboBox string value to the appropriate GameType enum
            GameType gameType = switch (selectedGameType) {
                case "Pokémon" -> GameType.POKEMON;
                case "Magic" -> GameType.MTG;
                case "Yu-Gi-Oh!" -> GameType.YUGIOH;
                default -> null;
            };

            // Pass the GameType enum directly to fetchAndDisplayCards
            if (gameType != null) {
                fetchAndDisplayCards(gameType);
            }
        });

        // Add label and combo box to the filter panel
        filterPanel.add(filterLabel);
        filterPanel.add(gameTypeComboBox);

        // Card Panel for displaying fetched cards
        cardPanel = new JPanel(new GridLayout(0, 3, 10, 10)); // 3 columns for cards
        JScrollPane scrollPane = new JScrollPane(cardPanel);

        // Footer Label for total cards count
        footerLabel = new JLabel("Total cards: 0");

        // Add filter panel, card panel, and footer
        root.add(header, BorderLayout.NORTH);
        root.add(filterPanel, BorderLayout.NORTH); // Add filterPanel to the north to make it top-aligned
        root.add(scrollPane, BorderLayout.CENTER);
        root.add(footerLabel, BorderLayout.SOUTH);

        // Set the main screen to the frame
        frame.setContentPane(root);
        frame.setSize(500, 400);
        frame.revalidate();
        frame.repaint();
    }







    private void fetchAndDisplayCards(GameType gameType) {
        // Fetch data from the API based on the selected game type
        String response = ApiResponse.fetchCardData(gameType);

        System.out.println("API Response: " + response);

        if (response == null || response.isEmpty()) {
            // Handle the case when no data is returned from the API
            JOptionPane.showMessageDialog(frame, "No cards found for this game type.");
            return;
        }

        // Parse the response to get a list of cards
        List<Card> cards = parseCardsFromAPIResponse(response, gameType);

        if (cards.isEmpty()) {
            // Handle the case where no cards were parsed
            JOptionPane.showMessageDialog(frame, "No valid cards found.");
            return;
        }

        // Now that we have the cards, update the card panel and footer label
        updateCardPanel(cardPanel, cards, footerLabel);
    }



    private void updateCardPanel(JPanel panel, List<Card> cards, JLabel footer) {
        panel.removeAll();
        for (Card card : cards) {
            JPanel cardUI = new JPanel();
            cardUI.setLayout(new BoxLayout(cardUI, BoxLayout.Y_AXIS));
            cardUI.setBorder(BorderFactory.createLineBorder(Color.GRAY));
            cardUI.add(new JLabel("Name: " + card.getCardName()));
            cardUI.add(new JLabel("Game: " + card.getGameType()));
            cardUI.add(new JLabel("Year: " + card.getReleaseDate()));
            cardUI.add(new JLabel("Price: $" + card.getPrice()));
            panel.add(cardUI);
        }
        footer.setText("Total cards: " + cards.size());
        panel.revalidate();
        panel.repaint();
    }

    private List<Card> parseCardsFromAPIResponse(String apiResponse, GameType gameType) {

        List<Card> cards = new ArrayList<>();

        System.out.println("Parsed " + cards.size() + " cards");

        // Example logic to parse the API response for different game types
        if (gameType == (GameType.POKEMON)) {
            // Example of how to parse Pokémon cards (you will need to adapt this to the actual response structure)
            // Using Gson to parse the JSON response
            Gson gson = new Gson();
            JsonObject responseJson = gson.fromJson(apiResponse, JsonObject.class);
            JsonArray results = responseJson.getAsJsonArray("results"); // Assuming the API returns a "results" array

            for (JsonElement element : results) {
                JsonObject cardJson = element.getAsJsonObject();
                String name = cardJson.get("name").getAsString();
                String releaseDate = "Unknown"; // Pokémon doesn't have a release date in the basic response
                double price = 5.0; // Default price (you might need to fetch actual pricing)
                cards.add(new Card(name, GameType.POKEMON, releaseDate, price));
            }
        } else if (gameType == (GameType.MTG)) {
            // Example logic to parse Magic: The Gathering cards
            Gson gson = new Gson();
            JsonObject responseJson = gson.fromJson(apiResponse, JsonObject.class);
            JsonArray cardsJsonArray = responseJson.getAsJsonArray("cards"); // Assuming the API returns a "cards" array

            for (JsonElement element : cardsJsonArray) {
                JsonObject cardJson = element.getAsJsonObject();
                String name = cardJson.get("name").getAsString();
                String releaseDate = cardJson.get("releaseDate").getAsString();
                double price = cardJson.has("price") ? cardJson.get("price").getAsDouble() : 10.0; // Example price parsing
                cards.add(new Card(name, GameType.MTG, releaseDate, price));
            }
        } else if (gameType == (GameType.YUGIOH)) {
            // Example logic to parse Yu-Gi-Oh cards
            Gson gson = new Gson();
            JsonArray responseJson = gson.fromJson(apiResponse, JsonArray.class);

            for (JsonElement element : responseJson) {
                JsonObject cardJson = element.getAsJsonObject();
                String name = cardJson.get("name").getAsString();
                String releaseDate = "Unknown"; // Yu-Gi-Oh API may not have release date in response
                double price = 8.0; // Default price (you can adjust based on the API)
                cards.add(new Card(name, GameType.YUGIOH, releaseDate, price));
            }
        }

        return cards;
    }


    public static void main(String[] args) {
        DatabaseConnection dbConn = new DatabaseConnection("jdbc:sqlite:cards.db");
        dbConn.createConnection();


        if (dbConn.getConnection() == null) {
            System.out.println("Failed to establish a database connection.");
            return;
        }

        Database.initializeDatabase();
        Database db = new Database(dbConn);

        SwingUtilities.invokeLater(() -> new GUI(db));
    }
}
