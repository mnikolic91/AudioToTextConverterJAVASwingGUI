package View.IndexWindow;

import javax.swing.*;

/**
 * This class is responsible for the main window.
 * It contains the login panel and the audio info input panel.
 */
public class MainWindow {
    public JFrame frame;
    private LoginPanel loginPanel;
    private AudioInputPanel audioInfoInputPanel;

    public MainWindow() {
        frame = new JFrame("Audio to Text Converter");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(650, 350);

        JPanel mainPanel = new JPanel();

        loginPanel = new LoginPanel();
        mainPanel.add(loginPanel);

        audioInfoInputPanel = new AudioInputPanel();
        mainPanel.add(audioInfoInputPanel);
        audioInfoInputPanel.setBorder(BorderFactory.createEmptyBorder(60, 100, 25, 100));

        frame.add(mainPanel);
        frame.setVisible(true);
    }
}




