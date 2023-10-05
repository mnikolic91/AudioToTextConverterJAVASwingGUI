package Controller;

import Model.UserInfo;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import javax.swing.*;
import java.io.*;
import java.util.Arrays;


/**
 * This class is responsible for the info window manager.
 * It contains the methods for listing files in the transcripts panel,
 * showing text in the view panel and showing stats in the stats panel.
 */
public class InfoWindowManager {

    private String keySet;
    private String[] userNames;
    private int conversionDurations;
    private String time;

    UserInfo userInfo;


    /**
     * Constructor for the InfoWindowManager class.
     * It initializes the userInfo object.
     */
    public void listFilesInFolder(String name, JList listField) {

        File folder = new File(userInfo.getTranscriptFolder() + "\\" + name + "\\");
        //provjera da li folder postoji
        if (!folder.exists()) {
            folder.mkdirs();
        }

        DefaultListModel model = new DefaultListModel();

        for (File f : folder.listFiles()) {
            model.addElement(f.getName());
        }

        listField.setModel(model);
    }


    /**
     * Constructor for the InfoWindowManager class.
     * It initializes the userInfo object.
     */
    public void showTextInViewPanel(String name, JTextPane textPane) {

        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader(userInfo.getTranscriptFolder() + "\\" + UserInfo.nickname + "\\" + name + "\\"));
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        }
        try {
            StringBuilder sb = new StringBuilder();
            String line = br.readLine();

            while (line != null) {
                sb.append(line);
                sb.append(System.lineSeparator());
                line = br.readLine();
            }
            String everything = sb.toString();
            textPane.setText(everything);
        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            try {
                br.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }


    /**
     * Constructor for the InfoWindowManager class.
     * It initializes the userInfo object.
     */
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

                        //System.out.println("jel puni " + keySet + " " + Arrays.toString(userNames) + " " + conversionDurations + " " + time);

                    }
                }
            } else {
                System.out.println("Nije moguće učitati JSON datoteku.");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String toString() {
        return "Original link to the audio: " + keySet + '\'' +
                ",\n Your audio conversion  started at: '" + time + '\'' +
                ",\n Users that also converted this audio: " + Arrays.toString(userNames) +
                ",\n Average conversion duration for this audio: " + conversionDurations + " seconds" +
                '.';
    }
}

