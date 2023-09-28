package Controller;

import Model.AudioInfo;
import com.google.gson.Gson;

import java.util.HashMap;

public class AudioInputManager {

    private Gson gson = new Gson();
    private HashMap<String, AudioInfo> audioInfoHashMap = new HashMap<>();
    private AudioInfo audioInfo = new AudioInfo();

    public static int generateUniqueValue() {
        int uniqueValue = (int) (Math.random() * 90000) + 10000;
        return uniqueValue;
    }

    public void setUniqueValue(int uniqueValue) {
        audioInfo.setUniqueValue(uniqueValue);
    }


    //method that gets audio input from user
    public void setAudioName(String name) {
        audioInfo.setAudioName(name);
    }

    //method that gets audio url from user
    public void setAudioUrl(String url) {
        audioInfo.setAudio_url(url);
    }

    //method sets start time of conversion
    public void setStartTime(long startTime) {
        audioInfo.setStartTime(startTime);
    }

    //method sets end time of conversion
    public void setEndTime(long endTime) {
        audioInfo.setEndTime(endTime);
    }

    //method sets duration of conversion
    public void setConversionDuration(long conversionDuration) {
        audioInfo.setConversionDuration(conversionDuration);
    }

    //method that adds the user name to the audio info
    public void addUserName(String userName) {
        audioInfo.getUserNames().add(userName);
    }

    //method that sets the audio path
    public void setAudioTextPath() {
        audioInfo.setAudioTextPath();
    }

    //method that checks if audio info link is null or not and adds it to the hashmap
    public void addAudioInfo() {
        if (audioInfo.getAudio_url() != null) {
            audioInfoHashMap.put(audioInfo.getAudio_url(), audioInfo);
            System.out.println("Audio Info Added to HashMap values:");
            audioInfoHashMap.values().forEach(audioInfo -> System.out.println(audioInfo)); // Ovisno o implementaciji toString() metode u klasi AudioInfo

        }
    }

    //method that saves the audio info to a json file and updates the value for the same key




}
