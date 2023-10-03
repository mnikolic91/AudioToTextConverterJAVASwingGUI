package View.SecondWindow;

import javax.swing.*;
import java.awt.*;

public class StatsPanel extends JPanel {
    private JTextArea statsTextArea;

    public StatsPanel() {
        setLayout(new BorderLayout());
        statsTextArea = new JTextArea();
        JScrollPane statsScrollPane = new JScrollPane(statsTextArea);
        add(statsScrollPane, BorderLayout.CENTER);

        //fill statsTextArea with random text
        statsTextArea.setText("Lorem ipsum dolor sit amet, "+ "\n" +"consectetur adipiscing elit.");
    }
}