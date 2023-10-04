package View.SecondWindow;

import javax.swing.*;

public class StatsPanel extends JPanel {
    private JTextArea statsTextArea;
    private JLabel statsLabel;

    public StatsPanel() {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        statsLabel = new JLabel("Statistike:");
        statsTextArea = new JTextArea();
        JScrollPane statsScrollPane = new JScrollPane(statsTextArea);
        add(statsLabel);
        add(statsScrollPane);

        //fill statsTextArea with random text
        statsTextArea.setText("Lorem ipsum dolor sit amet, "+ "\n" +"consectetur adipiscing elit.");
    }
}