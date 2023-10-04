package View.SecondWindow;

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
        add(statsLabel);
        add(statsScrollPane);

        //fill statsTextArea with random text
        statsTextArea.setText("Lorem ipsum dolor sit amet, "+ "\n" +"consectetur adipiscing elit.");
    }
}