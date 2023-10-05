package View.SecondWindow;


import javax.swing.*;
import java.awt.*;

/**
 * This class is responsible for the view panel in the second window.
 * It contains a text pane.
 * The text pane is used to display the transcript.
 */
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