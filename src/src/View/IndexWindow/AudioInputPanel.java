package View.IndexWindow;

import Controller.LoginAndRegistrationManager;
import Controller.TranscriptAPIManager;
import javax.swing.*;
import java.awt.*;
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
    private JLabel iconLabel;
    private TranscriptAPIManager controller= new TranscriptAPIManager();
    private LoginAndRegistrationManager phase = new LoginAndRegistrationManager();

    public AudioInputPanel() {

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));


        Icon icon = new ImageIcon("misc/time_icon.png");

        linkInput = new JTextField(35);
        JLabel linkLabel = new JLabel("Link Input:");
        linkLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        nameTranscript = new JTextField(35);
        JLabel nameLabel = new JLabel("Name Transcript:");
        nameLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JPanel bottomPanel = new JPanel();
        convertButton = new JButton("Convert");
        timeLabel = new JLabel("Time of Convert: ");
        timeLabel.setAlignmentX(Component.RIGHT_ALIGNMENT);
        iconLabel = new JLabel(icon);
        bottomPanel.add(iconLabel);
        bottomPanel.add(convertButton);

        add(linkLabel);
        add(linkInput);
        add(nameLabel);
        add(nameTranscript);
        add(bottomPanel);

        // action listener for the convert button
        convertButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //checks if user is logged in
                if (phase.isUserLoggedIn()) {
                    String nameTranscriptText = nameTranscript.getText();
                    String linkInputText = linkInput.getText();

                    // check if both fields are filled in
                    if (!nameTranscriptText.isEmpty() && !linkInputText.isEmpty()) {
                        bottomPanel.add(timeLabel);
                        SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
                        String time = dateFormat.format(new Date());
                        timeLabel.setText("Time of Convert: " + time);

                        Timer timer = new Timer();
                        timer.schedule(new TimerTask() {
                            @Override
                            public void run() {
                                try {
                                    controller.postGetTrans(nameTranscriptText, linkInputText);
                                } catch (URISyntaxException ex) {
                                    ex.printStackTrace();
                                } catch (IOException ex) {
                                    ex.printStackTrace();
                                } catch (InterruptedException ex) {
                                    ex.printStackTrace();
                                }
                            }
                        }, 500); // 500 miliseconds delay
                    } else {
                        JOptionPane.showMessageDialog(null, "Both Name Transcript and Link Input must be filled in to convert.");
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "You must be logged in to convert!");
                }
            }
        });
    }
}


