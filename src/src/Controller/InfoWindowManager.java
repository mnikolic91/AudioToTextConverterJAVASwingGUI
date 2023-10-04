package Controller;

import Model.Transcript;
import Model.UserInfo;
import View.SecondWindow.InfoWindow;
import View.SecondWindow.TranscriptsPanel;
import View.SecondWindow.ViewPanel;

import javax.swing.*;
import java.io.File;


public class InfoWindowManager {

    InfoWindow infoWindow;
    ViewPanel viewPanel;
    Transcript transcript;
    UserInfo userInfo;
    TranscriptsPanel transcriptsPanel = new TranscriptsPanel();

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
}

