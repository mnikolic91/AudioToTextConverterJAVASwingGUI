package Model;

import java.util.ArrayList;
import java.util.List;


public class AudioInfo {

    private static int uniqueValue;
    private static String audio_url;
    public static String audioName;
    private long startTime;
    private long endTime;
    private long conversionDuration;
    private static String audioTextPath;
    private List<String> userNames = new ArrayList<>();
    private List<String> audioNames = new ArrayList<>();


    public void setUniqueValue(int uniqueValue) {
        this.uniqueValue = uniqueValue;
    }

    public static int getUniqueValue() {
        return uniqueValue;
    }

    public static String getAudio_url() {
        return audio_url;
    }

    public void setAudio_url(String audio_url) {
        this.audio_url = audio_url;
    }

    public static String getAudioName() {
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

    public static String getAudioTextPath() {
        return audioTextPath;
    }

    public List<String> getAudioNames() {
        return audioNames;
    }

    public void setAudioNames(List<String> audioNames) {
        this.audioNames = audioNames;
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
                ", audioNames=" + audioNames +
                '}';
    }
}
