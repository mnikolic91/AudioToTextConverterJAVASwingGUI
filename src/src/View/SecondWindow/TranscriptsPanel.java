package View.SecondWindow;

import Controller.InfoWindowManager;

import javax.swing.*;
import javax.swing.text.Position;
import java.awt.*;

public class TranscriptsPanel extends JPanel {
    public static JList<String> transcriptsList = new JList<>();
    private JLabel transcriptLabel;
    private JTextField filterField;
    private JLabel iconLabel;
    InfoWindowManager iwm = new InfoWindowManager();

    public TranscriptsPanel() {
        setLayout(new BorderLayout());
        Icon icon = new ImageIcon("misc/magnifier.png");
        transcriptLabel = new JLabel("Your transcripts");
        transcriptLabel.setIcon(icon);
        transcriptLabel.setFont(new Font("Serif", Font.BOLD, 16));
        transcriptsList.setFont(new Font("Serif", Font.PLAIN, 14));
        filterField = new JTextField();
        filterField.setText("Search");
        filterField.setEditable(true);
        filterField.setPreferredSize(new Dimension(150, 20));
        filterField.setFont(new Font("Serif", Font.PLAIN, 14));

        // Postavljanje modela (ovo možete učiniti samo jednom)


        add((transcriptLabel), BorderLayout.PAGE_START);
        add(new JScrollPane(transcriptsList), BorderLayout.CENTER);
        add(filterField, BorderLayout.PAGE_END);


        transcriptsList.addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                String fileName = transcriptsList.getSelectedValue();
                iwm.showTextInViewPanel(fileName, ViewPanel.viewTextPane);
                String name = fileName.replace(".txt", "");
                System.out.println(name);
                iwm.AudioInfoSearcher(name);
                String result = iwm.toString();
                StatsPanel.statsTextArea.setText(result);
            }
        });

        filterField.addActionListener(e -> {
            String filterText = filterField.getText();
            if (filterText.length() == 0) {
                transcriptsList.clearSelection();
                transcriptsList.clearSelection();
            } else {
                int index = transcriptsList.getNextMatch(filterText, 0, Position.Bias.Forward);
                transcriptsList.setSelectedIndex(index);
                transcriptsList.ensureIndexIsVisible(index);
            }
        });
    }
}