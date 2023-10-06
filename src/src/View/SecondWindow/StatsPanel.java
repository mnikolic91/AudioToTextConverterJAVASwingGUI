package View.SecondWindow;

import Controller.InfoWindowManager;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class StatsPanel extends JPanel {

    static DefaultTableModel model = new DefaultTableModel();
    private JTable statsTable;
    public static JTextArea statsTextArea = new JTextArea();
    private JLabel statsLabel;

    InfoWindowManager iwm = new InfoWindowManager();
    TranscriptsPanel tp = new TranscriptsPanel();



    public StatsPanel() {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        statsLabel = new JLabel("Trans stats");
        statsTable = new JTable(model);
        statsTable.setPreferredScrollableViewportSize(new Dimension(500, 70));
        statsLabel.setFont(new Font("Serif", Font.BOLD, 16));
        statsTextArea.setFont(new Font("Serif", Font.PLAIN, 14));
        JScrollPane statsScrollPane = new JScrollPane(statsTextArea);
        statsTextArea.setEditable(false);

        statsTextArea.setLineWrap(true);
        statsTextArea.setWrapStyleWord(true);

        add(statsTable);
        add(statsLabel);
        add(statsScrollPane);

        model.addColumn("Link");
        model.addColumn("Time");
        model.addColumn("Usernames");
        model.addColumn("Duration");



        statsTextArea.setText("Your transcript stats will be shown here.");
    }
    /**
     * This method is used to add a row to the stats table.
     * @param model
     * @param link
     * @param time
     * @param usernames
     * @param duration
     */
    public static void dodajRedak(DefaultTableModel model, String link, String time, String usernames, int duration) {
        model.addRow(new Object[]{"Link: " + link, "Time: " + time, "Usernames: " + usernames, "Duration: " + duration});
    }
}
