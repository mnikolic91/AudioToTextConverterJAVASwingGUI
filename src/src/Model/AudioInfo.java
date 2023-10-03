package Model;

import java.util.ArrayList;
import java.util.List;


public class AudioInfo {

    private static int uniqueValue;
    private static String audio_url;
    private static String audioName;
    private long startTime;
    private long endTime;
    private long conversionDuration;
    private String audioTextPath;
    private List<String> userNames = new ArrayList<>();


    public void setUniqueValue(int uniqueValue) {
        this.uniqueValue = uniqueValue;
    }

    public static int getUniqueValue() {
        return uniqueValue;
    }

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

    public List<String> getUserNames() {
        return userNames;
    }

    public void setUserNames(List<String> userNames) {
        this.userNames = userNames;
    }

    public void setAudioTextPath() {
        this.audioTextPath = ".\\transcripts\\" + uniqueValue + ".txt";
    }

    public String getAudioTextPath() {
        return audioTextPath;
    }


    @Override
    public String toString() {
        return "AudioInfo{" +
                "audio_url='" + audio_url + '\'' +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                ", conversionDuration=" + conversionDuration +
                ", userNames=" + userNames +
                ", audioTextPath='" + audioTextPath + '\'' +
                '}';
    }
}
