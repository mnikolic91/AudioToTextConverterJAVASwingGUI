package View.IndexWindow;

import Controller.MainController;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.URISyntaxException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimerTask;
import java.util.Timer;

class AudioInputPanel extends JPanel {
    private JTextField linkInput;
    private JTextField nameTranscript;
    private JButton convertButton;
    private JLabel timeLabel;
    private MainController controller= new MainController();

    public AudioInputPanel() {

        linkInput = new JTextField(10);
        add(new JLabel("Link Input:"));
        add(linkInput);

        nameTranscript = new JTextField(10);
        add(new JLabel("Name Transcript:"));
        add(nameTranscript);


        JPanel bottomPanel = new JPanel();
        convertButton = new JButton("Convert");
        timeLabel = new JLabel("Time of Convert: ");
        bottomPanel.add(convertButton);

        add(bottomPanel);



        // action listener for the convert button
        convertButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                bottomPanel.add(timeLabel);
                SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
                String time = dateFormat.format(new Date());
                timeLabel.setText("Time of Convert: " + time);

                Timer timer = new Timer();
                timer.schedule(new TimerTask() {
                    @Override
                    public void run() {
                        try {
                            controller.postGetTrans(nameTranscript.getText(), linkInput.getText());
                        } catch (URISyntaxException ex) {
                            ex.printStackTrace();
                        } catch (IOException ex) {
                            ex.printStackTrace();
                        } catch (InterruptedException ex) {
                            ex.printStackTrace();
                        }
                    }
                }, 500); // 500 miliseconds delay
            }
        });
    }
}

