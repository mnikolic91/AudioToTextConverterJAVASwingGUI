package View.SecondWindow;

import Controller.InfoWindowManager;

import javax.swing.*;
import java.awt.*;

public class StatsPanel extends JPanel {


    private JTextArea statsTextArea;
    private JLabel statsLabel;

    public StatsPanel() {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        statsLabel = new JLabel("Statistike:");
        statsTextArea = new JTextArea();
        statsLabel.setFont(new Font("Serif", Font.BOLD, 16));
        statsTextArea.setFont(new Font("Serif", Font.PLAIN, 14));
        JScrollPane statsScrollPane = new JScrollPane(statsTextArea);
        statsTextArea.setEditable(false);
        statsTextArea.setLineWrap(true);
        statsTextArea.setWrapStyleWord(true);
        add(statsLabel);
        add(statsScrollPane);

        statsTextArea.setText("This audio was transcribed under " + InfoWindowManager.getUrl() + "\n"
        + "Other users that converted this same audio: " + InfoWindowManager.getUserNames() + "\n" +
                "You started your audio conversion at: " + InfoWindowManager.getTime() + "\n" +
                "And it lasted for " + InfoWindowManager.getDuration() + " seconds.\n" +
                "You can find your transcript in the 'Transcripts' tab.");

    }
}