package View.SecondWindow;

import Controller.InfoWindowManager;

import javax.swing.*;
import java.awt.*;

public class StatsPanel extends JPanel {


    private static JTextPane statsTextArea = new JTextPane();
    private JLabel statsLabel;

    //method that sets the text in the statsTextArea
    public static void setStatsTextArea(String text) {
        statsTextArea.setText(text);
    }


    public StatsPanel() {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        statsLabel = new JLabel("Statistike:");
        statsLabel.setFont(new Font("Serif", Font.BOLD, 16));
        statsTextArea.setFont(new Font("Serif", Font.PLAIN, 14));
        JScrollPane statsScrollPane = new JScrollPane(statsTextArea);
        statsTextArea.setEditable(false);
        add(statsLabel);
        add(statsScrollPane);

        statsTextArea.setText("Your transcript statistics will be displayed here.");

    }
}