package Controller;

import Model.AudioInfo;
import Model.UserInfo;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.*;
import java.lang.reflect.Type;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * This class is responsible for the audio input manager.
 * It contains the methods for setting the audio input from user,
 * setting the start and end time of conversion, and saving the audio info to a file.
 */

public class AudioInputManager {

    private static Gson gson = new Gson();
    private HashMap<String, AudioInfo> audioInfoHashMap = new HashMap<>();

    private AudioInfo audioInfo = new AudioInfo();
    private TranscriptAPIManager tapim = new TranscriptAPIManager();
    private UserInfo userInfo = new UserInfo();


    public static int generateUniqueValue() {
        int uniqueValue = (int) (Math.random() * 90000) + 10000;
        return uniqueValue;
    }

    //method that gets the user name
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

    //method that sets the audio path
    public void addAudioName(String audioName) {
        audioInfo.getAudioNames().add(audioName);
    }

    //method that returns the toString method from audio info
    public String toString() {
        System.out.println(audioInfo.toString());
        return audioInfo.toString();
    }

    /**
     * method that catches the date and time of conversion
     * @return date and time of conversion
     */
    public String catchTime() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
        String time = dateFormat.format(new Date());
        audioInfo.setTime(time);
        return time;
    }

    /**
     * method that catches the date and time of conversion
     * @return date and time of conversion
     */
    public void addOrUpdateAudioInfo() {
        if (audioInfo.getAudio_url() != null && tapim.isIsTranscripted()) {
            Map<String, AudioInfo> existingData = loadJsonFile();

            if (existingData == null) {
                existingData = new HashMap<>();
            }

            AudioInfo existingValue = existingData.get(audioInfo.getAudio_url());

            if (existingValue == null) {
                existingData.put(audioInfo.getAudio_url(), audioInfo);

            } else {
                if (!existingValue.getUserNames().contains(userInfo.getNickname())) {
                    existingValue.getUserNames().add(userInfo.getNickname());


                }
                if (!existingValue.getAudioNames().contains(AudioInfo.audioName)) {
                    existingValue.getAudioNames().add(AudioInfo.audioName);
                }
            }
            saveJsonFile(existingData);

        } else {
            System.out.println("Audio URL is null or not transcribed yet!");
        }
    }

    /**
     * method that saves the audio info to a file
     * @param data
     */
    private void saveJsonFile(Map<String, AudioInfo> data) {
        File file = new File("AudioInfoMap.txt");

        // Provjerite postoji li datoteka, a ako ne postoji, stvorite je
        if (!file.exists()) {
            try {
                file.createNewFile(); // Stvaramo datoteku ako ne postoji
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        try (Writer writer = new FileWriter("AudioInfoMap.txt")) {
            gson.toJson(data, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * method that loads the audio info from a file
     * @return
     */
    private Map<String, AudioInfo> loadJsonFile() {
        File file = new File("AudioInfoMap.txt");

        // Provjerite postoji li datoteka, a ako ne postoji, stvorite je
        if (!file.exists()) {
            try {
                file.createNewFile(); // Stvaramo datoteku ako ne postoji
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        try (Reader reader = new FileReader("AudioInfoMap.txt")) {
            Type type = new TypeToken<HashMap<String, AudioInfo>>() {
            }.getType();
            return gson.fromJson(reader, type);
        } catch (IOException e) {
            e.printStackTrace();
            return new HashMap<>();
        }
    }
}
