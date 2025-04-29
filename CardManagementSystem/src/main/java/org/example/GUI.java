package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.URL;
import java.util.LinkedList;

public class GUI {

    private JFrame frame;
    private final Database database;

    public GUI(Database database) {
        frame = new JFrame("Card Collector");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        showWelcomeScreen();
        this.database = database;
    }

    private void showWelcomeScreen() {
        JLabel title = new JLabel("Welcome to Card Collector");
        title.setFont(new Font("Arial", Font.BOLD, 24));
        title.setForeground(new Color(0, 0, 139));
        title.setHorizontalAlignment(SwingConstants.CENTER);

        JPanel loginCard = createCard("Login", "https://via.placeholder.com/80?text=Login");
        loginCard.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                showLoginScreen();
            }
        });

        JPanel signupCard = createCard("Sign Up", "https://via.placeholder.com/80?text=Signup");
        signupCard.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                showSignupScreen();
            }
        });

        JPanel cards = new JPanel(new FlowLayout(FlowLayout.CENTER, 40, 0));
        cards.add(loginCard);
        cards.add(signupCard);

        JPanel root = new JPanel();
        root.setLayout(new BorderLayout(0, 40));
        root.setBorder(BorderFactory.createEmptyBorder(50, 50, 50, 50));
        root.add(title, BorderLayout.NORTH);
        root.add(cards, BorderLayout.CENTER);

        frame.setContentPane(root);
        frame.setSize(500, 400);
        frame.revalidate();
        frame.repaint();
    }

    private JPanel createCard(String text, String imageUrl) {
        JLabel label = new JLabel(text);
        label.setFont(new Font("Arial", Font.PLAIN, 16));
        label.setHorizontalAlignment(SwingConstants.CENTER);

        JLabel imageLabel = new JLabel();
        try {
            ImageIcon icon = new ImageIcon(new URL(imageUrl));
            Image scaledImage = icon.getImage().getScaledInstance(80, 80, Image.SCALE_SMOOTH);
            imageLabel.setIcon(new ImageIcon(scaledImage));
        } catch (Exception e) {
            imageLabel.setText("Image not found");
            imageLabel.setHorizontalAlignment(SwingConstants.CENTER);
        }

        JPanel card = new JPanel();
        card.setLayout(new BoxLayout(card, BoxLayout.Y_AXIS));
        card.setPreferredSize(new Dimension(150, 150));
        card.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        card.setBackground(Color.LIGHT_GRAY);
        card.setAlignmentX(Component.CENTER_ALIGNMENT);

        card.add(Box.createVerticalStrut(10));
        card.add(imageLabel);
        card.add(Box.createVerticalStrut(10));
        card.add(label);

        return card;
    }

    private void showLoginScreen() {
        JPanel root = new JPanel();
        root.setLayout(new BoxLayout(root, BoxLayout.Y_AXIS));
        root.setBorder(BorderFactory.createEmptyBorder(30, 50, 30, 50));

        JLabel header = new JLabel("Login");
        header.setFont(new Font("Arial", Font.BOLD, 20));
        header.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel userLabel = new JLabel("Username:");
        JTextField username = new JTextField(15);

        JLabel passLabel = new JLabel("Password:");
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
                showMainGUI(); // Replace with your actual main app screen
            } else {
                errorLabel.setText("Invalid username or password.");
            }
        });


        JButton back = new JButton("Back");
        back.addActionListener(e -> showWelcomeScreen());

        root.add(header);
        root.add(Box.createVerticalStrut(15));
        root.add(userLabel);
        root.add(username);
        root.add(Box.createVerticalStrut(10));
        root.add(passLabel);
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

        JLabel userLabel = new JLabel("Username:");
        JTextField username = new JTextField(15);

        JLabel emailLabel = new JLabel("Email:");
        JTextField email = new JTextField(15);

        JLabel passLabel = new JLabel("Password:");
        JPasswordField password = new JPasswordField(15);

        JLabel confirmLabel = new JLabel("Confirm Password:");
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
        root.add(userLabel);
        root.add(username);
        root.add(Box.createVerticalStrut(10));
        root.add(emailLabel);
        root.add(email);
        root.add(Box.createVerticalStrut(10));
        root.add(passLabel);
        root.add(password);
        root.add(Box.createVerticalStrut(10));
        root.add(confirmLabel);
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
        // Basic email validation regex
        return email.matches("^[\\w.-]+@[\\w.-]+\\.[a-zA-Z]{2,}$");
    }

    // can be changed later to our design...
    private void showMainGUI() {
        JPanel root = new JPanel(new BorderLayout());
        root.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JLabel label = new JLabel("Welcome to the Main App Screen!");
        label.setFont(new Font("Arial", Font.BOLD, 20));
        label.setHorizontalAlignment(SwingConstants.CENTER);

        JButton logout = new JButton("Logout");
        logout.addActionListener(e -> showWelcomeScreen());

        root.add(label, BorderLayout.CENTER);
        root.add(logout, BorderLayout.SOUTH);

        frame.setContentPane(root);
        frame.setSize(500, 400);
        frame.revalidate();
        frame.repaint();
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
