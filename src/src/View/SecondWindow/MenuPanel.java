package View.SecondWindow;

import Model.UserInfo;

import javax.swing.*;


class MenuPanel extends JPanel {

    private UserInfo userInfo = new UserInfo();

    private JLabel profileLabel;

    public MenuPanel() {
        profileLabel = new JLabel("Vaš profil, " + userInfo.getNickname());
        add(profileLabel);
    }
}
