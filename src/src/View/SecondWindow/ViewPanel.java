package View.SecondWindow;

import Controller.InfoWindowManager;
import Model.Transcript;

import javax.swing.*;
import java.awt.*;

public class ViewPanel extends JPanel {
    private JTextArea viewTextArea;
    private InfoWindowManager iwm = new InfoWindowManager();
    private Transcript transcript = new Transcript();

    public ViewPanel() {
        setLayout(new BorderLayout());
        viewTextArea = new JTextArea();
        JScrollPane viewScrollPane = new JScrollPane(viewTextArea);
        add(viewScrollPane, BorderLayout.CENTER);
    }
}