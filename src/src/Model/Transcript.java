package Model;

import java.text.SimpleDateFormat;
import java.util.HashMap;

/**
 * Klasa koja drzi sve koristene informacije
 */

public class Transcript {

    private String audio_url;
    private String id;
    private String status;
    private String text;


    //direktorij u kojem se trenutno nalazimo - varijabla nam treba kod spremanja i citanja fileova


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

}
