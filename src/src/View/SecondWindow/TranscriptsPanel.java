package View.SecondWindow;

import javax.swing.*;
import java.awt.*;

public class TranscriptsPanel extends JPanel {
    public static JList<String> transcriptsList= new JList<>();;
    private JLabel transcriptLabel;


    public TranscriptsPanel() {
        setLayout(new BorderLayout());
        transcriptLabel = new JLabel("Vaši transkripti:");

        // Postavljanje modela (ovo možete učiniti samo jednom)

        add(transcriptLabel, BorderLayout.NORTH);
        add(new JScrollPane(transcriptsList), BorderLayout.CENTER);

    }


}