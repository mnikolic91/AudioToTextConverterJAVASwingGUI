package View.SecondWindow;

import Controller.InfoWindowManager;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import javax.swing.*;
import javax.swing.text.Position;
import java.awt.*;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;

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

    private  String keySet;
    private  String[] userNames;
    private  int conversionDurations;
    private  String time;

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
                AudioInfoSearcher(name);
                StatsPanel.dodajRedak(StatsPanel.model, keySet, time, Arrays.toString(userNames), conversionDurations);
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

    public void AudioInfoSearcher(String audioNameToSearch) {
        String filePath = "AudioInfoMap.txt";

        try (FileReader reader = new FileReader(filePath)) {
            Gson gson = new Gson();
            JsonObject audioInfoMap = gson.fromJson(reader, JsonObject.class);

            if (audioInfoMap != null) {
                for (String key : audioInfoMap.keySet()) {
                    JsonObject audioObject = audioInfoMap.getAsJsonObject(key);
                    JsonArray audioNamesList = audioObject.getAsJsonArray("audioNames");
                    boolean found = false;

                    if (audioNamesList != null) {
                        for (JsonElement element : audioNamesList) {
                            if (element.getAsString().equals(audioNameToSearch)) {
                                found = true;
                                break;
                            }
                        }
                    }

                    if (found) {
                        System.out.println("URL: " + key);
                        keySet = key;

                        // Ispis ostalih vrijednosti iz objekta
                        System.out.println("startTime: " + audioObject.get("time").getAsString());
                        time = audioObject.get("time").getAsString();
                        System.out.println("conversionDuration: " + audioObject.get("conversionDuration").getAsInt());
                        conversionDurations = audioObject.get("conversionDuration").getAsInt();
                        System.out.println("userNames: " + audioObject.getAsJsonArray("userNames").toString());
                        userNames = audioObject.getAsJsonArray("userNames").toString().split(",");

                        System.out.println("Pronađeni audioNames: " + audioNamesList.toString());

                        System.out.println("jel puni " + keySet + " " + Arrays.toString(userNames) + " " + conversionDurations + " " + time);

                    }
                }
            } else {
                System.out.println("Nije moguće učitati JSON datoteku.");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getKeySet() {
        return keySet;
    }

    public String[] getUserNames() {
        return userNames;
    }

    public int getConversionDurations() {
        return conversionDurations;
    }

    public String getTime() {
        return time;
    }
}