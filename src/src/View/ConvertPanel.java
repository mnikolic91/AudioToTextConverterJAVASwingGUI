package View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ConvertPanel extends JPanel {
    private JButton convertButton;
    private JLabel timeLabel;

    public ConvertPanel() {
        setLayout(new BorderLayout());

        // Dodavanje gumba "Convert" i prikaza vremena
        JPanel bottomPanel = new JPanel();
        convertButton = new JButton("Convert");
        timeLabel = new JLabel("Time of Convert: ");
        bottomPanel.add(convertButton);

        add(bottomPanel);

        setBorder(BorderFactory.createLineBorder(Color.white));

        // Dodavanje akcije za gumb "Convert"
        convertButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                bottomPanel.add(timeLabel);
                SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
                String time = dateFormat.format(new Date());
                timeLabel.setText("Time of Convert: " + time);
            }
        });
    }
}
