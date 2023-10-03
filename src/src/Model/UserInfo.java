package Model;


public class UserInfo {

    private static String nickname;
    private String password;
    private String salt;
    private static boolean loggedIn;

    public String getNickname() {
        return nickname;
    }

    public String getPassword() {
        return password;
    }

    public String getSalt() {
        return salt;
    }

    public boolean isLoggedIn() {
        return loggedIn;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }


    public void setPassword(String password) {
        this.password = password;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public void setLoggedIn(boolean loggedIn) {
        this.loggedIn = loggedIn;
    }





}
