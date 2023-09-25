package View;

import Controller.PasswordHashingAndSaving;

import javax.swing.*;
import java.awt.*;

class LoginPanel extends JPanel {
    private JTextField nicknameField;
    private JPasswordField passwordField;
    private JButton loginButton;
    private JButton registerButton;

    PasswordHashingAndSaving phase = new PasswordHashingAndSaving();

    public LoginPanel() {
        setLayout(new FlowLayout());

        nicknameField = new JTextField(10);
        passwordField = new JPasswordField(10);
        loginButton = new JButton("Login");
        registerButton = new JButton("Register");

        add(new JLabel("Nickname:"));
        add(nicknameField);
        add(new JLabel("Password:"));
        add(passwordField);
        add(loginButton);
        add(registerButton);

        setBorder(BorderFactory.createLineBorder(Color.white));

        //dodavanje actionlistenera za register field
        registerButton.addActionListener(e -> {
            String nickname = nicknameField.getText();
            String password = passwordField.getText();
            if (nickname.isEmpty() || password.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Please enter nickname and password!");
            } else if (phase.doesNicknameExists(nickname)) {
                JOptionPane.showMessageDialog(null, "Nickname already exists!");
            }
            else {
                phase.doHashing(password);
                phase.saveHashAndSaltToTextFile(nickname);
                JOptionPane.showMessageDialog(null, "You have successfully registered!");
            }
        });

    }
}