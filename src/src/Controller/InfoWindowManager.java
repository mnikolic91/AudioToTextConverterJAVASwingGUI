package Controller;

import Model.Transcript;
import Model.UserInfo;
import View.SecondWindow.InfoWindow;
import View.SecondWindow.TranscriptsPanel;
import View.SecondWindow.ViewPanel;

import javax.swing.*;
import java.io.*;

public class InfoWindowManager {

    InfoWindow infoWindow;
    ViewPanel viewPanel;
    Transcript transcript;
    UserInfo userInfo;

    public void listFilesInFolder(String name, JList listField) {

        //folder objekt kojem pridruzujemo putanju
        File folder = new File( userInfo.getTranscriptFolder() + "\\" + name + "\\");
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




}

