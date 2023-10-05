package View.SecondWindow;


import javax.swing.*;
import java.awt.*;

/**
 * This class is responsible for the stats panel in the second window.
 * It contains a label and a text area.
 * The text area is used to display the transcript stats.
 */
public class StatsPanel extends JPanel {


    public static JTextArea statsTextArea = new JTextArea();
    private JLabel statsLabel;


    public StatsPanel() {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        statsLabel = new JLabel("Trans stats");
        statsLabel.setFont(new Font("Serif", Font.BOLD, 16));
        statsTextArea.setFont(new Font("Serif", Font.PLAIN, 14));
        JScrollPane statsScrollPane = new JScrollPane(statsTextArea);
        statsTextArea.setEditable(false);

        statsTextArea.setLineWrap(true);
        statsTextArea.setWrapStyleWord(true);

        add(statsLabel);
        add(statsScrollPane);


        statsTextArea.setText("Your transcript stats will be shown here.");


    }

}