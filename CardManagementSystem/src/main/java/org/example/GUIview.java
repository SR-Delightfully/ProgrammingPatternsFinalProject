package org.example;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class GUIview {

    private JFrame frame;
    private JPanel cardPanel;
    private JLabel footerLabel;
    private final Database database;
    private MessageProvider messageProvider;
    private GUIController controller;

    public GUIview(Database database, MessageProvider messageProvider) {
        this.database = database;
        this.messageProvider = messageProvider;

        this.frame = new JFrame("Card Collector");
        if (footerLabel == null) {
            footerLabel = new JLabel("Total Cards: 0");  // Initialize it if it's null
        }

        this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.frame.setVisible(true);
    }

    public void setController(GUIController controller) {
        this.controller = controller;
    }

    /**
     * Displays the GUI for the Home/Welcome screen
     */
    public void showWelcomeScreen() {
        JPanel header = new JPanel();
        header.setLayout(new BoxLayout(header, BoxLayout.Y_AXIS));
        header.setBackground(new Color(47, 36, 71));

        // Language selector panel
        JPanel languagePanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        languagePanel.setOpaque(false);

        JLabel langLabel = new JLabel(messageProvider.getLanguageLabel());
        langLabel.setForeground(new Color(233, 245, 108));
        languagePanel.add(langLabel);

        JComboBox<String> langSelector = new JComboBox<>(new String[]{"English", "French"});
        String currentLang = (messageProvider instanceof FrenchMessageProvider) ? "French" : "English";
        langSelector.setSelectedItem(currentLang);

        langSelector.addActionListener(e -> {
            String selected = (String) langSelector.getSelectedItem();
            controller.handleLanguageChange(selected);
        });

        languagePanel.add(langSelector);

        // Title and subtitle using MessageProvider
        JLabel title = new JLabel(messageProvider.getWelcomeTitle());
        title.setFont(new Font("Arial", Font.BOLD, 42));
        title.setForeground(new Color(233, 245, 108));
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel subtitle = new JLabel(messageProvider.getWelcomeSubtitle());
        subtitle.setFont(new Font("Arial", Font.BOLD, 24));
        subtitle.setForeground(new Color(127, 116, 182));
        subtitle.setAlignmentX(Component.CENTER_ALIGNMENT);

        header.add(title);
        header.add(Box.createVerticalStrut(10));
        header.add(subtitle);

        // Login card
        JPanel loginCard = createCard(
                messageProvider.getLoginPrompt(),
                "https://i.imgur.com/yXpEDoG.png",
                messageProvider.getLoginButtonLabel()
        );
        loginCard.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                controller.showLoginScreen();
            }
        });

        // Signup card
        JPanel signupCard = createCard(
                messageProvider.getSignupPrompt(),
                "https://i.imgur.com/Iq8lriI.png",
                messageProvider.getSignupButtonLabel()
        );
        signupCard.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                controller.showSignupScreen();
            }
        });

        JPanel cards = new JPanel();
        cards.setLayout(new BoxLayout(cards, BoxLayout.X_AXIS));
        cards.setBackground(new Color(47, 36, 71));
        cards.setPreferredSize(new Dimension(650, 550));

        cards.add(Box.createHorizontalGlue());
        cards.add(loginCard);
        cards.add(Box.createHorizontalStrut(20));
        cards.add(signupCard);
        cards.add(Box.createHorizontalGlue());

        JPanel root = new JPanel();
        root.setBackground(new Color(47, 36, 71));
        root.setLayout(new BorderLayout(0, 40));
        root.setBorder(BorderFactory.createEmptyBorder(50, 50, 50, 50));

        root.add(header, BorderLayout.NORTH);
        root.add(cards, BorderLayout.CENTER);
        root.add(languagePanel, BorderLayout.SOUTH);

        frame.setContentPane(root);
        frame.setSize(800, 700);
        frame.revalidate();
        frame.repaint();
    }

    /**
     * Displays the GUI for the Login screen
     */
    public void showLoginScreen() {
        JPanel root = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.gridy = 0;

        root.setBackground(new Color(47, 36, 71));

        JLabel header = new JLabel(messageProvider.getLoginHeader());
        header.setFont(new Font("Arial", Font.BOLD, 20));
        header.setForeground(new Color(233, 245, 108));
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        root.add(header, gbc);

        gbc.anchor = GridBagConstraints.WEST;
        gbc.gridwidth = 1;
        gbc.gridy++;

        JLabel userLabel = new JLabel(messageProvider.getUsernameLabel());
        userLabel.setForeground(new Color(226, 227, 218));
        root.add(userLabel, gbc);

        gbc.gridx = 1;
        JTextField username = new JTextField(30);
        root.add(username, gbc);

        gbc.gridx = 0;
        gbc.gridy++;

        JLabel pwdLabel = new JLabel(messageProvider.getPasswordLabel());
        pwdLabel.setForeground(new Color(226, 227, 218));
        root.add(pwdLabel, gbc);

        gbc.gridx = 1;
        JPasswordField password = new JPasswordField(30);
        root.add(password, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;

        JLabel errorLabel = new JLabel("");
        errorLabel.setForeground(Color.RED);
        root.add(errorLabel, gbc);

        gbc.gridy++;
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 0));
        buttonPanel.setBackground(new Color(47, 36, 71));

        JButton login = new JButton(messageProvider.getLoginButton());
        login.setPreferredSize(new Dimension(120, 40));
        login.addActionListener(e -> {
            String user = username.getText().trim();
            String pass = new String(password.getPassword());

            if (user.isEmpty() || pass.isEmpty()) {
                JOptionPane.showMessageDialog(frame, messageProvider.getAllFieldsRequired());
            } else {
                boolean isLoginValid = controller.handleLogin(user, pass);

                if (!isLoginValid) {
                    JOptionPane.showMessageDialog(frame, messageProvider.getUsernameError());
                } else {
                    JOptionPane.showMessageDialog(frame, messageProvider.getLoginSuccess());
                }
            }
        });

        JButton back = new JButton(messageProvider.getBackButton());
        back.setPreferredSize(new Dimension(120, 40));
        back.addActionListener(e -> showWelcomeScreen());

        buttonPanel.add(login);
        buttonPanel.add(back);
        root.add(buttonPanel, gbc);

        frame.setContentPane(root);
        frame.setSize(800, 700);
        frame.setVisible(true);
    }

    /**
     * Displays the GUI for the Signup screen
     */
    public void showSignupScreen() {
        JPanel root = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.gridy = 0;

        root.setBackground(new Color(47, 36, 71));

        JLabel header = new JLabel(messageProvider.getSignupHeader());
        header.setFont(new Font("Arial", Font.BOLD, 20));
        header.setForeground(new Color(233, 245, 108));
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        root.add(header, gbc);

        gbc.anchor = GridBagConstraints.WEST;
        gbc.gridwidth = 1;
        gbc.gridy++;

        JLabel userLabel = new JLabel(messageProvider.getUsernameLabel());
        userLabel.setForeground(new Color(226, 227, 218));
        root.add(userLabel, gbc);

        gbc.gridx = 1;
        JTextField username = new JTextField(30);
        root.add(username, gbc);

        gbc.gridx = 0;
        gbc.gridy++;

        JLabel emailLabel = new JLabel(messageProvider.getEmailLabel());
        emailLabel.setForeground(new Color(226, 227, 218));
        root.add(emailLabel, gbc);

        gbc.gridx = 1;
        JTextField email = new JTextField(30);
        root.add(email, gbc);

        gbc.gridx = 0;
        gbc.gridy++;

        JLabel pwdLabel = new JLabel(messageProvider.getPasswordLabel());
        pwdLabel.setForeground(new Color(226, 227, 218));
        root.add(pwdLabel, gbc);

        gbc.gridx = 1;
        JPasswordField password = new JPasswordField(30);
        root.add(password, gbc);

        gbc.gridx = 0;
        gbc.gridy++;

        JLabel confLabel = new JLabel(messageProvider.getConfirmPasswordLabel());
        confLabel.setForeground(new Color(226, 227, 218));
        root.add(confLabel, gbc);

        gbc.gridx = 1;
        JPasswordField confirmPassword = new JPasswordField(30);
        root.add(confirmPassword, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;

        JLabel errorLabel = new JLabel("");
        errorLabel.setForeground(Color.RED);
        root.add(errorLabel, gbc);

        gbc.gridy++;
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 0));
        buttonPanel.setBackground(new Color(47, 36, 71));

        JButton signup = new JButton(messageProvider.getSignupButton());
        signup.setPreferredSize(new Dimension(120, 40));
        signup.addActionListener(e -> {
            System.out.println("Signup button clicked");
            String user = username.getText().trim();
            String mail = email.getText().trim();
            String pass = new String(password.getPassword());
            String confirmPass = new String(confirmPassword.getPassword());

            if (user.isEmpty() || mail.isEmpty() || pass.isEmpty() || confirmPass.isEmpty()) {
                errorLabel.setText(messageProvider.getAllFieldsRequired());
            } else if (!pass.equals(confirmPass)) {
                errorLabel.setText(messageProvider.getMisMatchError());
            } else {
                controller.handleSignup(user, mail, pass, confirmPass);
            }
        });

        JButton back = new JButton(messageProvider.getBackButton());
        back.setPreferredSize(new Dimension(120, 40));
        back.addActionListener(e -> {
            System.out.println("Back button clicked");
            showWelcomeScreen();
        });

        buttonPanel.add(signup);
        buttonPanel.add(back);
        root.add(buttonPanel, gbc);

        frame.setContentPane(root);
        frame.setSize(800, 700);
        frame.revalidate();
        frame.repaint();
    }

    /**
     * Displays the screen in which the user may choose which game's database to browse
     */
    public void showGameTypes() {
        JPanel header = new JPanel();
        header.setLayout(new BoxLayout(header, BoxLayout.Y_AXIS));
        header.setBackground(new Color(47, 36, 71));

        JLabel title = new JLabel(messageProvider.getGameTypeTitle());
        title.setFont(new Font("Arial", Font.BOLD, 42));
        title.setForeground(new Color(233, 245, 108));
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel subtitle = new JLabel(messageProvider.getGameTypeSubtitle());
        subtitle.setFont(new Font("Arial", Font.BOLD, 24));
        subtitle.setForeground(new Color(127, 116, 182));
        subtitle.setAlignmentX(Component.CENTER_ALIGNMENT);

        header.add(title);
        header.add(Box.createVerticalStrut(10));
        header.add(subtitle);

        JPanel mtgCard = createCard(messageProvider.getMagicTheGatheringLabel(), "https://i.imgur.com/KfNwbUc.png", messageProvider.getSearchButtonLabel());
        mtgCard.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                showGallery(GameType.MTG);
            }
        });

        JPanel pokemonCard = createCard(messageProvider.getPokemonLabel(), "https://i.imgur.com/bYfXiv3.png", messageProvider.getSearchButtonLabel());
        pokemonCard.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                showGallery(GameType.POKEMON);
            }
        });

        JPanel cards = new JPanel(new GridLayout(1, 3, 20, 0));
        cards.setBackground(new Color(47, 36, 71));
        cards.add(mtgCard);
        cards.add(pokemonCard);

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

    /**
     * Displays a collectible/trading card game's database of cards
     * @param gameType refers to the game in which you would like to browse.
     */
    private void showGallery(GameType gameType) {
        JPanel root = new JPanel(new BorderLayout(10, 10));
        root.setBackground(new Color(47, 36, 71));
        root.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Title
        JLabel title = new JLabel(gameType + " Card Collection", JLabel.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 30));
        title.setForeground(new Color(47, 36, 71));

        JPanel titlePanel = new JPanel(new BorderLayout());
        titlePanel.setBackground(new Color(227, 237, 43));
        titlePanel.add(title, BorderLayout.CENTER);

        // Main card panel (Grid)
        cardPanel = new JPanel(new GridLayout(0, 3, 10, 10));
        JScrollPane scrollPane = new JScrollPane(cardPanel);
        scrollPane.setPreferredSize(new Dimension(600, 450));

        // Footer
        footerLabel = new JLabel(messageProvider.getTotalCardsLabel());
        JPanel footerPanel = new JPanel(new BorderLayout());
        footerPanel.setBackground(new Color(47, 36, 71));
        footerPanel.add(footerLabel, BorderLayout.WEST);

        JPanel centerPanel = new JPanel(new BorderLayout());
        centerPanel.add(scrollPane, BorderLayout.CENTER);
        centerPanel.add(footerPanel, BorderLayout.SOUTH);

        root.add(titlePanel, BorderLayout.NORTH);
        root.add(centerPanel, BorderLayout.CENTER);

        // Load and display cards
        fetchAndDisplayCards(gameType, "All", null);

        frame.setContentPane(root);
        frame.setSize(800, 700);
        frame.revalidate();
        frame.repaint();
    }


    private void fetchAndDisplayCards(GameType gameType, String typeFilter, String colorFilter, String searchKeyword) {
        List<Card> cards;
        CardController cardController = new CardController(database);

        if (cardPanel == null) {
            cardPanel = new JPanel(new GridLayout(0, 3, 10, 10)); // Initialize it if it's null
        }
        // Apply filters and search
        if (gameType == GameType.POKEMON) {
            cards = cardController.filterAndSearchPokemonCards(typeFilter, searchKeyword);
        } else if (gameType == GameType.MTG) {
            cards = cardController.filterAndSearchMTGCards(colorFilter, typeFilter, searchKeyword);
        } else {
            cards =  cardController.getAllCards();  // Fallback case if no filters applied
        }

        // Clear current card panel
        cardPanel.removeAll();

        // Add the filtered cards to the panel
        for (Card card : cards) {
            JLabel cardLabel = new JLabel(card.getCardName());
            cardPanel.add(cardLabel);
        }



        // Update the footer label to show total card count
        footerLabel.setText("Total cards: " + cards.size());

        // Refresh the view
        cardPanel.revalidate();
        cardPanel.repaint();
    }


    /**
     * Used to create a card container; a simple box with a title, subtitle and possibly images.
     * @param titleText refers to the title of the card
     * @param imageUrl refers to the url of the image in the card
     * @param subtitleText refers to the subtitle of the card
     * @return returns a JPanel with the card specific styles applied
     */
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

        JPanel subtitlePanel = new JPanel(new BorderLayout());
        subtitlePanel.setBackground(new Color(227, 237, 43));
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
        card.setOpaque(true);
        card.setFocusable(true);

        card.add(Box.createVerticalStrut(10));
        card.add(titleLabel);
        card.add(Box.createVerticalStrut(60));
        card.add(imageLabel);
        card.add(Box.createVerticalGlue());
        card.add(subtitlePanel);

        return card;
    }

    /**
     * Verifies if an email is in fact valid or not
     * @param email refers to a string containing the characters that make up the email in question
     * @return returns a boolean; true if valid, false if not.
     */
    private boolean isValidEmail(String email) {
        return email.matches("^[\\w.-]+@[\\w.-]+\\.[a-zA-Z]{2,}$");
    }

    /**
     * Used to get a response from the various API
     * @param gameType refers to the game that belongs to the API you want to fetch
     * @param filter1 ?
     * @param filter2 ?
     */
    private void fetchAndDisplayCards(GameType gameType, String filter1, String filter2) {
        String response = ApiResponse.fetchCardData(gameType);

        System.out.println("API Response: " + response);

        if (response == null || response.isEmpty()) {
            JOptionPane.showMessageDialog(frame, messageProvider.getCardsMissing());
            return;
        }

        List<Card> cards = parseCardsFromAPIResponse(response, gameType);

        if (cards.isEmpty()) {
            JOptionPane.showMessageDialog(frame, messageProvider.getNoCardFound());
            return;
        }

        updateCardPanel(cardPanel, cards, footerLabel);
    }

    /**
     * Updates a card panel and resets the text for the total number of cards displayed
     * @param panel refers to the panel in which you would like to update
     * @param cards refers to the list of cards that you would like to display
     * @param footer refers to the panel located at the bottom of your application
     */
    private void updateCardPanel(JPanel panel, List<Card> cards, JLabel footer) {
        if (panel == null) {
            System.out.println("The panel is null. Cannot update.");
            return;
        }
        panel.removeAll();
        for (Card card : cards) {
            JPanel cardUI = new JPanel();
            cardUI.setLayout(new BoxLayout(cardUI, BoxLayout.Y_AXIS));
            cardUI.setBorder(BorderFactory.createLineBorder(Color.GRAY));
            cardUI.add(new JLabel("Name: " + card.getCardName()));
            cardUI.add(new JLabel("Game: " + card.getGameType()));
            cardUI.add(new JLabel("Year: " + card.getReleaseDate()));
            panel.add(cardUI);
        }
        footer.setText("Total cards: " + cards.size());
        panel.revalidate();
        panel.repaint();
    }

    /**
     * Parses through the API's response and converts the large dataset into a comprehensible List of cards
     * @param apiResponse refers to the string in which you would like to parse for information
     * @param gameType refers to the gametype of the API
     * @return
     */
    private List<Card> parseCardsFromAPIResponse(String apiResponse, GameType gameType) {
        List<Card> cards = new ArrayList<>();
        Gson gson = new Gson();

        try {
            if (gameType == GameType.POKEMON) {
                JsonObject responseJson = gson.fromJson(apiResponse, JsonObject.class);
                JsonArray results = responseJson.getAsJsonArray("data");

                for (JsonElement element : results) {
                    JsonObject cardJson = element.getAsJsonObject();
                    String name = cardJson.get("name").getAsString();
                    String releaseDate = cardJson.has("set") && cardJson.getAsJsonObject("set").has("releaseDate")
                            ? cardJson.getAsJsonObject("set").get("releaseDate").getAsString()
                            : "Unknown";
                    cards.add(new Card(name, GameType.POKEMON, releaseDate));
                }

            } else if (gameType == GameType.MTG) {
                JsonObject responseJson = gson.fromJson(apiResponse, JsonObject.class);
                JsonArray cardsJsonArray = responseJson.getAsJsonArray("data");

                for (JsonElement element : cardsJsonArray) {
                    JsonObject cardJson = element.getAsJsonObject();
                    String name = cardJson.get("name").getAsString();
                    String releaseDate = cardJson.has("released_at")
                            ? cardJson.get("released_at").getAsString()
                            : "Unknown";
                    cards.add(new Card(name, GameType.MTG, releaseDate));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return cards;
    }


    /**
     * Used to gather messages in various languages to allow for localization
     * @param newProvider refers to the message provider to be set
     */
    public void setMessageProvider(MessageProvider newProvider) {
        this.messageProvider = newProvider;
    }

    /**
     * Displays messages to the user
     * @param message
     */
    public void displayMessage(String message) {
        JOptionPane.showMessageDialog(frame, message);
    }

    /**
     * displays errors to the user
     * @param error
     */
    public void displayError(String error) {
        JOptionPane.showMessageDialog(frame, error, "Error", JOptionPane.ERROR_MESSAGE);
    }
}
