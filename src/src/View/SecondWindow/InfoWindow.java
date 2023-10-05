package View.SecondWindow;

import Model.UserInfo;

import javax.swing.*;
import java.awt.*;

/**
 * This class is responsible for the second window.
 * It contains the view panel, the transcripts panel and the stats panel.
 */
public class InfoWindow extends JFrame {
    private ViewPanel viewPanel;
    private StatsPanel statsPanel;
    private TranscriptsPanel transcriptsPanel;

    private UserInfo userInfo = new UserInfo();

    public InfoWindow() {

        setTitle("Hello, " + userInfo.getNickname()+ "!");
        setSize(650, 350);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BoxLayout(getContentPane(), BoxLayout.X_AXIS));

        viewPanel = new ViewPanel();
        viewPanel.setPreferredSize(new Dimension(280, 350));

        statsPanel = new StatsPanel();
        statsPanel.setPreferredSize(new Dimension(170, 350));

        transcriptsPanel = new TranscriptsPanel();
        transcriptsPanel.setPreferredSize(new Dimension(150, 350));

        add(viewPanel);
        add(transcriptsPanel);
        add(statsPanel);
        transcriptsPanel.setBorder(BorderFactory.createEmptyBorder(5, 0, 5, 5));
        viewPanel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        statsPanel.setBorder(BorderFactory.createEmptyBorder(5, 0, 5, 5));

        setVisible(true);
    }
}