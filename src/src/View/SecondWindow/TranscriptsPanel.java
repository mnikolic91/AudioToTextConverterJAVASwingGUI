package View.SecondWindow;

import Controller.InfoWindowManager;

import javax.swing.*;
import java.awt.*;

public class TranscriptsPanel extends JPanel {
    public static JList<String> transcriptsList = new JList<>();;
    private JLabel transcriptLabel;

    InfoWindowManager iwm = new InfoWindowManager();

    public TranscriptsPanel() {
        setLayout(new BorderLayout());
        transcriptLabel = new JLabel("Vaši transkripti:");
        transcriptLabel.setFont(new Font("Serif", Font.BOLD, 16));
        transcriptsList.setFont(new Font("Serif", Font.PLAIN, 14));

        // Postavljanje modela (ovo možete učiniti samo jednom)

        add(transcriptLabel, BorderLayout.NORTH);
        add(new JScrollPane(transcriptsList), BorderLayout.CENTER);

        transcriptsList.addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                String fileName = transcriptsList.getSelectedValue();
                iwm.showTextInViewPanel(fileName, ViewPanel.viewTextPane);
                String name = fileName.replace(".txt", "");
                System.out.println(name);
                iwm.AudioInfoSearcher(name);
            }
        });
    }
}