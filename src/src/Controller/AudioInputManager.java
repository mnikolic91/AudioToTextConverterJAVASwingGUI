package Controller;

import Model.AudioInfo;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.*;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

public class AudioInputManager {

    private static Gson gson = new Gson();
    private HashMap<String, AudioInfo> audioInfoHashMap = new HashMap<>();
    private AudioInfo audioInfo = new AudioInfo();

    public HashMap<String, AudioInfo> getAudioInfoHashMap() {
        return audioInfoHashMap;
    }

    public void setAudioInfoHashMap(HashMap<String, AudioInfo> audioInfoHashMap) {
        this.audioInfoHashMap = audioInfoHashMap;
    }

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
            audioInfoHashMap.values().forEach(audioInfo -> System.out.println(audioInfo));

        }
    }

    public static void saveAudioInfoHashMapToFile(Map<String, AudioInfo> audioInfoHashMap) {
        Map<String, AudioInfo> existingData = loadJsonFile();


        for (String key : audioInfoHashMap.keySet()) {
            AudioInfo newValue = audioInfoHashMap.get(key);
            if (existingData.containsKey(key)) {
                AudioInfo existingValue = existingData.get(key);
                existingValue.getUserNames().addAll(newValue.getUserNames());
            } else {
                existingData.put(key, newValue);
            }
        }

        saveJsonFile(existingData);
    }

    public static Map<String, AudioInfo> loadJsonFile() {
        try (Reader reader = new FileReader("AudioInfoMap.txt")) {
            Type type = new TypeToken<HashMap<String, AudioInfo>>() {}.getType();
            return gson.fromJson(reader, type);
        } catch (IOException e) {
            e.printStackTrace();
            return new HashMap<>();
        }
    }

    public static void saveJsonFile(Map<String, AudioInfo> data) {
        try (Writer writer = new FileWriter("AudioInfoMap.txt")) {
            gson.toJson(data, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //method that iterates trough AudioInfoMap.txt and if inputed audio url exists and returns boolean
    public boolean checkIfAudioUrlExists(String audioUrl) {
        Map<String, AudioInfo> audioInfoMap = loadJsonFile();
        for (String key : audioInfoMap.keySet()) {
            if (key.equals(audioUrl)) {
                return true;
            }
        }
        return false;
    }




}
