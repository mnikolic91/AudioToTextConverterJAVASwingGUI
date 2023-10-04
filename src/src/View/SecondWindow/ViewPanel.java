package View.SecondWindow;

import Controller.AudioInputManager;
import Controller.InfoWindowManager;
import Model.Transcript;

import javax.swing.*;
import java.awt.*;

public class ViewPanel extends JPanel {
    public static JTextPane viewTextPane = new JTextPane();

    public ViewPanel() {
        setLayout(new BorderLayout());
        JScrollPane viewScrollPane = new JScrollPane(viewTextPane);
        add(viewScrollPane, BorderLayout.CENTER);

        viewTextPane.setEditable(false);
        viewTextPane.setFont(new Font("Serif", Font.PLAIN, 14));

    }
}