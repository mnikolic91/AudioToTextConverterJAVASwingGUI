package View;


import javax.swing.*;

class AudioInfoInputPanel extends JPanel {
    private JTextField linkInput;
    private JTextField nameTranscript;

    public AudioInfoInputPanel() {

        linkInput = new JTextField(10);
        add(new JLabel("Link Input:"));
        add(linkInput);

        nameTranscript = new JTextField(10);
        add(new JLabel("Name Transcript:"));
        add(nameTranscript);
    }
}