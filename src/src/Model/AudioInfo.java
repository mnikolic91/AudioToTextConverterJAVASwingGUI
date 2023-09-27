package Model;

import java.util.HashMap;

public class AudioInfo {

    private String audio_url;
    private String audioName;
    private long startTime;
    private long endTime;
    private long conversionDuration;
    private HashMap<String, String> audioInfo = new HashMap<String, String>();
    private static HashMap<String, Integer> audioStats = new HashMap<String, Integer>();


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

    public long getStartTime() {
        return startTime;
    }

    public void setStartTime(long startTime) {
        this.startTime = startTime;
    }

    public long getEndTime() {
        return endTime;
    }

    public void setEndTime(long endTime) {
        this.endTime = endTime;
    }

    public long getConversionDuration() {
        return conversionDuration;
    }

    public void setConversionDuration(long conversionDuration) {
        this.conversionDuration = conversionDuration;
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
