package Model;


public class UserInfo {

    public static String nickname;
    private String password;
    private String salt;
    private static boolean loggedIn;
    private static final String TRANSCRIPT_FOLDER = ".\\transcripts\\";

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

    public static String getTranscriptFolder() {
        return TRANSCRIPT_FOLDER;
    }

}
