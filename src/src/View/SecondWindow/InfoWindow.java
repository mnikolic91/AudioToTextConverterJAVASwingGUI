package View.SecondWindow;

import Model.UserInfo;

import javax.swing.*;
import java.awt.*;

public class InfoWindow extends JFrame {
    private ViewPanel viewPanel;
    private StatsPanel statsPanel;
    private TranscriptsPanel transcriptsPanel;

    private UserInfo userInfo = new UserInfo();

    public InfoWindow() {
        // Postavke prozora
        setTitle("Hello, " + userInfo.getNickname()+ "!");
        setSize(650, 350);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BoxLayout(getContentPane(), BoxLayout.X_AXIS));


        // Panel za prikaz
        viewPanel = new ViewPanel();
        viewPanel.setPreferredSize(new Dimension(280, 350));
        // Postavite tekst unutar viewPanel prema vašim potrebama

        // Panel za statistike
        statsPanel = new StatsPanel();
        statsPanel.setPreferredSize(new Dimension(170, 350));
        // Postavite tekst unutar statsPanel prema vašim potrebama

        // Panel za transkripte
        transcriptsPanel = new TranscriptsPanel();
        transcriptsPanel.setPreferredSize(new Dimension(150, 350));
        // Dodajte stavke u comboBox unutar transcriptsPanel prema vašim potrebama

        // Postavljanje rasporeda za glavni prozor
        add(viewPanel);
        add(transcriptsPanel);
        add(statsPanel);
        transcriptsPanel.setBorder(BorderFactory.createEmptyBorder(5, 0, 5, 5));
        viewPanel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        statsPanel.setBorder(BorderFactory.createEmptyBorder(5, 0, 5, 5));

        // Postavljanje vidljivosti prozora
        setVisible(true);
    }
}