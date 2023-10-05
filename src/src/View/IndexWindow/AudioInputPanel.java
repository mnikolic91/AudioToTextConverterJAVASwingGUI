package View.IndexWindow;

import Controller.AudioInputManager;
import Controller.InfoWindowManager;
import Controller.LoginAndRegistrationManager;
import Controller.TranscriptAPIManager;
import Model.UserInfo;
import View.SecondWindow.InfoWindow;
import View.SecondWindow.TranscriptsPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.TimerTask;
import java.util.Timer;

public class AudioInputPanel extends JPanel {
    private final JTextField linkInput;
    private final JTextField nameTranscript;
    private final JButton convertButton;
    private JLabel timeLabel;
    private JLabel iconLabel;
    private long startTime;
    private long endTime;
    private long conversionDuration;
    private TranscriptAPIManager tapim = new TranscriptAPIManager();
    private LoginAndRegistrationManager lorm = new LoginAndRegistrationManager();
    private AudioInputManager aim = new AudioInputManager();
    private InfoWindowManager iwm = new InfoWindowManager();

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
                if (lorm.isUserLoggedIn()) {
                    String nameTranscriptText = nameTranscript.getText();
                    String linkInputText = linkInput.getText();

                    // check if both fields are filled in
                    if (!nameTranscriptText.isEmpty() && !linkInputText.isEmpty()) {
                        aim.setAudioUrl(linkInputText);
                        aim.setAudioName(nameTranscriptText);
                        aim.setUniqueValue(aim.generateUniqueValue());
                        aim.setAudioTextPath();
                        aim.addAudioName(nameTranscriptText);
                        aim.addUserName(lorm.getUserNickname());

                        bottomPanel.add(timeLabel);
                        //SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
                        timeLabel.setText("Time of Convert: " + aim.catchTime());

                        startTime = System.currentTimeMillis();
                        aim.setStartTime(startTime);

                        Timer timer = new Timer();
                        timer.schedule(new TimerTask() {
                            @Override
                            public void run() {
                                try {
                                    tapim.postGetTrans(linkInputText);

                                    endTime = System.currentTimeMillis();
                                    aim.setEndTime(endTime);

                                    conversionDuration = (endTime - startTime) / 1000;
                                    System.out.println("Conversion Duration: " + conversionDuration + " seconds");
                                    aim.setConversionDuration(conversionDuration);

                                    aim.addOrUpdateAudioInfo();
                                    iwm.listFilesInFolder(UserInfo.nickname, TranscriptsPanel.transcriptsList);

                                    InfoWindow infoWindow = new InfoWindow();
                                    infoWindow.setVisible(true);


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


