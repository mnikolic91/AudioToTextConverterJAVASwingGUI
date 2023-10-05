package Controller;

import Model.UserInfo;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import javax.swing.*;
import java.io.*;

public class InfoWindowManager {

    private static String url;
    private static String time;
    private static int duration;
    private static String[] userNames;


    public void listFilesInFolder(String name, JList listField) {

        //folder objekt kojem pridruzujemo putanju
        File folder = new File(userInfo.getTranscriptFolder() + "\\" + name + "\\");
        //provjera da li folder postoji
        if (!folder.exists()) {
            folder.mkdirs();
        }
        //pozivamo DefaultListModel za JList
        DefaultListModel model = new DefaultListModel();
        //petlja koja puni model
        for (File f : folder.listFiles()) {
            model.addElement(f.getName());
        }
        //ispis modela u JListi
        listField.setModel(model);
    }

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
                        // Ovdje možete ispisati URL
                        System.out.println("URL: " + key);
                        url= key;

                        // Ispis ostalih vrijednosti iz objekta
                        System.out.println("startTime: " + audioObject.get("time").getAsString());
                        time = audioObject.get("time").getAsString();
                        System.out.println("conversionDuration: " + audioObject.get("conversionDuration").getAsInt());
                        duration = audioObject.get("conversionDuration").getAsInt();
                        System.out.println("userNames: " + audioObject.getAsJsonArray("userNames").toString());
                        userNames = audioObject.getAsJsonArray("userNames").toString().split(",");

                        // Ispis "audioNames"
                        System.out.println("Pronađeni audioNames: " + audioNamesList.toString());
                    }
                }
            } else {
                System.out.println("Nije moguće učitati JSON datoteku.");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    UserInfo userInfo;

    public static String getUrl() {
        return url;
    }

    public static String getTime() {
        return time;
    }

    public static int getDuration() {
        return duration;
    }

    public static String[] getUserNames() {
        return userNames;
    }


}

