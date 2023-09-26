package Model;

/**
 * Klasa koja drzi sve koristene informacije
 */

public class Transcript {

    //registracija korisnika koji žele transformirati audio u tekst
    //korisnik vidi ime audia(link) koji je transformirao, vrijeme kad je započeo transformaciju i koliko je dugo trajala, te tekst transfomracije
    //svaki audio link ima svoj unikatni hash kod koji se veže uz korisnika
    //uvesti pretraživanje po hash kodu linka i ispis svih korisnika koji su transformirali taj audio


    private String audio_url;
    private String id;
    private String status;
    private String text;


    //direktorij u kojem se trenutno nalazimo - varijabla nam treba kod spremanja i citanja fileova
    String currentUsersHomeDir = System.getProperty("user.dir");


    public String getAudio_url() {
        return audio_url;
    }

    public void setAudio_url(String audio_url) {
        this.audio_url = audio_url;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }


    public String getCurrentUsersHomeDir() {
        return currentUsersHomeDir;
    }

    public void setCurrentUsersHomeDir(String currentUsersHomeDir) {
        this.currentUsersHomeDir = currentUsersHomeDir;
    }
}
