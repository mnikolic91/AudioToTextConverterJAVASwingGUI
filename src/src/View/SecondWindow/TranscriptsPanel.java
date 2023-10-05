package View.SecondWindow;

import Controller.InfoWindowManager;

import javax.swing.*;
import javax.swing.text.Position;
import java.awt.*;

/**
 * This class is responsible for the transcripts panel in the second window.
 * It contains a label, a text field and a list.
 * The list is used to display the transcripts.
 */
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

        /**
         * This listener is used to display the selected transcript in the view panel.
         * It also calls the AudioInfoSearcher method from the InfoWindowManager class
         * to display the audio information in the stats panel.
         */
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

        /**
         * This listener is used to filter the transcripts list.
         */
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