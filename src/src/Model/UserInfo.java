package Model;

import java.util.HashMap;

public class UserInfo {

    private String nickname;
    private String password;
    private HashMap<String, String> userInfo;


    public UserInfo(String nickname, String password) {
        this.nickname = nickname;
        this.password = password;
    }
}
