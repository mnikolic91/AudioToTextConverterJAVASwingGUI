package Controller;

import Model.UserInfo;


import javax.swing.*;
import java.io.*;
import java.util.Arrays;


/**
 * This class is responsible for the info window manager.
 * It contains the methods for listing files in the transcripts panel,
 * showing text in the view panel and showing stats in the stats panel.
 */
public class InfoWindowManager {

    private static String keySet="temp";
    private static String[] userNames={"temp"};
    private static int conversionDurations=0;
    private static String time="temp";

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


    public String getKeySet() {
        return keySet;
    }

    public String getUserNames() {
        return Arrays.toString(userNames);
    }

    public int getConversionDurations() {
        return conversionDurations;
    }

    public String getTime() {
        return time;
    }

    public static void setKeySet(String keySet) {
        InfoWindowManager.keySet = keySet;
    }

    public static void setUserNames(String[] userNames) {
        InfoWindowManager.userNames = userNames;
    }

    public static void setConversionDurations(int conversionDurations) {
        InfoWindowManager.conversionDurations = conversionDurations;
    }

    public static void setTime(String time) {
        InfoWindowManager.time = time;
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

