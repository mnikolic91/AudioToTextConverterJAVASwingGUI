package Model;

import java.util.Arrays;

public class AudioInfo {

    private String audio_url;
    private static String audioName;
    private long startTime;
    private long endTime;
    private long conversionDuration;
    private static String[] userNames;
    private static String audioTextPath;


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

    public String[] getUserNames() {
        return userNames;
    }

    public void setUserNames(String[] userNames) {
        this.userNames = userNames;
    }


    public void setAudioTextPath(String audioLink) {
        this.audioTextPath = ".\\transcripts\\" + audioLink + ".txt";
    }


    @Override
    public String toString() {
        return "AudioInfo{" +
                "audio_url='" + audio_url + '\'' +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                ", conversionDuration=" + conversionDuration +
                ", userNames=" + Arrays.toString(userNames) +
                ", audioTextPath='" + audioTextPath + '\'' +
                '}';
    }
}
