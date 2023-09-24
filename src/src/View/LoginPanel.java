package View;

import javax.swing.*;
import java.awt.*;

class LoginPanel extends JPanel {
    private JTextField nicknameField;
    private JTextField passwordField;
    private JButton loginButton;
    private JButton registerButton;

    public LoginPanel() {
        setLayout(new FlowLayout());

        nicknameField = new JTextField(10);
        passwordField = new JTextField(10);
        loginButton = new JButton("Login");
        registerButton = new JButton("Register");

        add(new JLabel("Nickname:"));
        add(nicknameField);
        add(new JLabel("Password:"));
        add(passwordField);
        add(loginButton);
        add(registerButton);

        setBorder(BorderFactory.createLineBorder(Color.white));
    }
}