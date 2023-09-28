package View.IndexWindow;

import Controller.LoginAndRegistrationManager;

import javax.swing.*;
import java.awt.*;

class LoginPanel extends JPanel {

    private JTextField nicknameField;
    private JPasswordField passwordField;
    private JButton loginButton;
    private JButton registerButton;
    private JLabel loggedIn;
    private String nickname;
    private String password;

    LoginAndRegistrationManager larm = new LoginAndRegistrationManager();

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
        loggedIn = new JLabel();
        add(loggedIn);
        loggedIn.setVisible(false);

        setBorder(BorderFactory.createLineBorder(Color.white));

        //action listener for the registration button
        registerButton.addActionListener(e -> {
            nickname = nicknameField.getText();
            password = passwordField.getText();
            if (nickname.isEmpty() || password.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Please enter nickname and password!");
            } else if (larm.doesNicknameExists(nickname)) {
                JOptionPane.showMessageDialog(null, "Nickname already exists!");
            }
            else {
                larm.generateRandomString();
                larm.doHashing(password);
                larm.saveHashAndSaltToTextFile(nickname);
                nicknameField.setText("");
                passwordField.setText("");
                JOptionPane.showMessageDialog(null, "You have successfully registered!");
                loggedIn.setText("Please log in!");
                loggedIn.setVisible(true);
                registerButton.setEnabled(false);

            }
        });

        //adding action listener to login button
        loginButton.addActionListener(e -> {
            nickname = nicknameField.getText();
            password = passwordField.getText();
            if (nickname.isEmpty() || password.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Please enter nickname and password!");
            } else if (!larm.doesNicknameExists(nickname)) {
                JOptionPane.showMessageDialog(null, "Nickname does not exist!");
            } else if (larm.compareHashedPassword(nickname, password)) {
                JOptionPane.showMessageDialog(null, "You have successfully logged in!");
                larm.changeUserStatusToLoggedIn();
                larm.setUserNickname(nickname);
                loggedIn.setText("Hello, " + nickname + "!");
                loggedIn.setVisible(true);
                nicknameField.setText("");
                passwordField.setText("");
                loginButton.setEnabled(false);
                registerButton.setEnabled(false);

            } else {
                JOptionPane.showMessageDialog(null, "Password is incorrect!");
                passwordField.setText("");
            }
        });
    }
}