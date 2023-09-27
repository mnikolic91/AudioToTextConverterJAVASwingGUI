package Model;

import java.text.SimpleDateFormat;
import java.util.HashMap;

public class AudioInfo {

    private String audio_url;
    private String audioName;
    private SimpleDateFormat startTime;
    private SimpleDateFormat endTime;
    private static HashMap<String, String> audioInfo = new HashMap<String, String>();
    private HashMap<String, Integer> audioStats = new HashMap<String, Integer>();



    public String getAudio_url() {
        return audio_url;
    }

    public void setAudio_url(String audio_url) {
        this.audio_url = audio_url;
    }

    public String getAudioName() {
        return audioName;
    }

    public void setAudioName(String audioName) {
        this.audioName = audioName;
    }

    public SimpleDateFormat getStartTime() {
        return startTime;
    }

    public void setStartTime(SimpleDateFormat startTime) {
        this.startTime = startTime;
    }

    public SimpleDateFormat getEndTime() {
        return endTime;
    }

    public void setEndTime(SimpleDateFormat endTime) {
        this.endTime = endTime;
    }

    public void addAudioInfo(String url, String name) {
        audioInfo.put(url, name);
    }

    public HashMap<String, String> getAudioInfo() {
        return audioInfo;
    }

    public void setAudioInfo(HashMap<String, String> audioInfo) {
        this.audioInfo = audioInfo;
    }

    public HashMap<String, Integer> getAudioStats() {
        return audioStats;
    }

    public void setAudioStats(HashMap<String, Integer> audioStats) {
        this.audioStats = audioStats;
    }
}
