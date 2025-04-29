package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class GUI {

    private JFrame frame;

    public GUI() {
        frame = new JFrame("Card Collector");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        showWelcomeScreen();
        frame.setVisible(true);
    }

    private void showWelcomeScreen() {
        JLabel title = new JLabel("Welcome to Card Collector");
        title.setFont(new Font("Arial", Font.BOLD, 24));
        title.setForeground(new Color(0, 0, 139));
        title.setHorizontalAlignment(SwingConstants.CENTER);

        JPanel loginCard = createCard("Login", "login.png");
        loginCard.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                showLoginScreen();
            }
        });

        JPanel signupCard = createCard("Sign Up", "signup.png");
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

    private JPanel createCard(String text, String imagePath) {
        JLabel label = new JLabel(text);
        label.setFont(new Font("Arial", Font.PLAIN, 16));
        label.setHorizontalAlignment(SwingConstants.CENTER);

        JLabel imageLabel = new JLabel();
        ImageIcon icon = new ImageIcon(imagePath);
        Image scaledImage = icon.getImage().getScaledInstance(80, 80, Image.SCALE_SMOOTH);
        imageLabel.setIcon(new ImageIcon(scaledImage));

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

        JButton login = new JButton("Log In");
        JButton back = new JButton("Back");
        back.addActionListener(e -> showWelcomeScreen());

        root.add(header);
        root.add(Box.createVerticalStrut(15));
        root.add(userLabel);
        root.add(username);
        root.add(Box.createVerticalStrut(10));
        root.add(passLabel);
        root.add(password);
        root.add(Box.createVerticalStrut(15));
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

        JLabel passLabel = new JLabel("Password:");
        JPasswordField password = new JPasswordField(15);

        JLabel confirmLabel = new JLabel("Confirm Password:");
        JPasswordField confirmPassword = new JPasswordField(15);

        JButton signup = new JButton("Sign Up");
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
        root.add(confirmLabel);
        root.add(confirmPassword);
        root.add(Box.createVerticalStrut(15));
        root.add(signup);
        root.add(Box.createVerticalStrut(10));
        root.add(back);

        frame.setContentPane(root);
        frame.setSize(400, 350);
        frame.revalidate();
        frame.repaint();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new GUI());
    }
}
