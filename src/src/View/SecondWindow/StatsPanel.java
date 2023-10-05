package View.SecondWindow;


import Controller.InfoWindowManager;

import javax.swing.*;
import java.awt.*;

public class StatsPanel extends JPanel {

    InfoWindowManager iwm = new InfoWindowManager();

    private JTextArea statsTextArea= new JTextArea();
    private JLabel statsLabel;


    public StatsPanel() {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        statsLabel = new JLabel("Statistike:");
        statsLabel.setFont(new Font("Serif", Font.BOLD, 16));
        statsTextArea.setFont(new Font("Serif", Font.PLAIN, 14));
        JScrollPane statsScrollPane = new JScrollPane(statsTextArea);
        statsTextArea.setEditable(false);
        add(statsScrollPane);
        add(statsTextArea);

        statsTextArea.setText(iwm.toString());
        System.out.println(iwm.toString());

    }

    public void setStatsTextArea(String text) {
        statsTextArea.setText(text);
    }
}