package View.SecondWindow;

import javax.swing.*;
import java.awt.*;

public class InfoWindow extends JFrame {
    private ViewPanel viewPanel;
    private StatsPanel statsPanel;
    private TranscriptsPanel transcriptsPanel;
    private MenuPanel menuPanel;

    public InfoWindow() {
        // Postavke prozora
        setTitle("Audio To Text Converter");
        setSize(650, 350);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Panel za profil
        menuPanel = new MenuPanel();

        // Panel za prikaz
        viewPanel = new ViewPanel();
        // Postavite tekst unutar viewPanel prema vašim potrebama

        // Panel za statistike
        statsPanel = new StatsPanel();
        // Postavite tekst unutar statsPanel prema vašim potrebama

        // Panel za transkripte
        transcriptsPanel = new TranscriptsPanel();
        // Dodajte stavke u comboBox unutar transcriptsPanel prema vašim potrebama

        // Postavljanje rasporeda za glavni prozor
        add(menuPanel, BorderLayout.NORTH);
        add(viewPanel, BorderLayout.CENTER);
        add(statsPanel, BorderLayout.EAST);
        add(transcriptsPanel, BorderLayout.SOUTH);

        // Postavljanje vidljivosti prozora
        setVisible(true);
    }
}