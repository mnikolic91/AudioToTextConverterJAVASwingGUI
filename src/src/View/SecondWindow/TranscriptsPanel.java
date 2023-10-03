package View.SecondWindow;

import javax.swing.*;
import java.awt.*;

public class TranscriptsPanel extends JPanel {
    private JComboBox<String> transcriptsComboBox;

    public TranscriptsPanel() {
        setLayout(new BorderLayout());
        transcriptsComboBox = new JComboBox<>();
        // Dodajte stavke u comboBox prema vašim potrebama
        transcriptsComboBox.addItem("Transkript 1");
        transcriptsComboBox.addItem("Transkript 2");
        transcriptsComboBox.addItem("Transkript 3");
        add(transcriptsComboBox, BorderLayout.NORTH);
    }
}