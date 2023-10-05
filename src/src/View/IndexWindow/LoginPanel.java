package View.IndexWindow;

import Controller.LoginAndRegistrationManager;

import javax.swing.*;
import java.awt.*;

/**
 * This class is responsible for the login panel in the main window.
 * It contains the login and registration fields.
 */
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

        /**
         * Adding action listeners to the buttons.
         * When the user clicks on the login button, the program checks if the nickname exists in the database.
         * If it does, the program checks if the password is correct.
         * If it is, the user is logged in.
         * If it is not, the user is prompted to enter the password again.
         * If the nickname does not exist, the user is prompted to register.
         * When the user clicks on the register button, the program checks if the nickname already exists in the database.
         * If it does, the user is prompted to enter another nickname.
         * If it does not, the user is registered.
         */
        registerButton.addActionListener(e -> {
            nickname = nicknameField.getText();
            password = passwordField.getText();
            if (nickname.isEmpty() || password.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Please enter nickname and password!");
            } else if (larm.doesNicknameExists(nickname)) {
                JOptionPane.showMessageDialog(null, "Nickname already exists!");
            } else {
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

        /**
         * Adding action listeners to the buttons.
         * When the user clicks on the login button, the program checks if the nickname exists in the database.
         * If it does, the program checks if the password is correct.
         * If it is, the user is logged in.
         * If it is not, the user is prompted to enter the password again.
         * If the nickname does not exist, the user is prompted to register.
         * When the user clicks on the register button, the program checks if the nickname already exists in the database.
         * If it does, the user is prompted to enter another nickname.
         * If it does not, the user is registered.
         */
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