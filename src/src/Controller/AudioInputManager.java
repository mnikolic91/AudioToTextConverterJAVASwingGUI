package Controller;

import Model.AudioInfo;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.*;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;


public class AudioInputManager {

    private Gson gson = new Gson();

    AudioInfo audioInfo = new AudioInfo();


    //method that gets audio input from user
    public void setAudioName(String name) {
        audioInfo.setAudioName(name);

    }

    //method that gets audio url from user
    public void setAudioUrl(String url) {
        audioInfo.setAudio_url(url);
    }

    //method that stores audio name as key in hashmap and audio url as value in hashmap
    public void setAudioInfo(String url, String name) {
        audioInfo.addAudioInfo(url, name);

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


    //method that stores audioInfo hashMap in a text file
    public void saveAudioInfoToFile() {
        System.out.println(audioInfo.getAudioInfo());
        try (FileWriter writer = new FileWriter("transcriptedFiles.txt", true)) {
            for (Map.Entry<String, String> entry : audioInfo.getAudioInfo().entrySet()) {
                String line = entry.getKey() + ":" + entry.getValue() + "\n";
                writer.write(line);
            }
            System.out.println("HashMap saved to transcriptedFiles.txt");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    //method that reads and returns values from transcriptedFiles.txt
    public void loadAudioInfoFromFile() {
        try (BufferedReader reader = new BufferedReader(new FileReader("transcriptedFiles.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(":");
                if (parts.length == 2) {
                    String url = parts[0].trim();
                    String name = parts[1].trim();
                    setAudioInfo(url, name);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //method that sets the link input as key in audioStats hashMap and sets the value to 0 and increments the value every time the link is used
    public void incrementAudioStats(String link) {
        HashMap<String, Integer> audioStats = audioInfo.getAudioStats();
        if (audioStats.containsKey(link)) {
            int currentValue = audioStats.get(link);
            audioStats.put(link, currentValue + 1);
        } else {
            audioStats.put(link, 1);
        }
        audioInfo.setAudioStats(audioStats); // update the audioStats in the audioInfo object
        System.out.println("Key: " + audioInfo.getAudioStats().keySet() + ", Value: " + audioInfo.getAudioStats().values());
    }


    public void saveAudioStatsToFile() {
        HashMap<String, Integer> existingAudioStats = loadExistingAudioStatsFromFile();

        for (String key : audioInfo.getAudioStats().keySet()) {
            int newValue = audioInfo.getAudioStats().get(key);
            if (existingAudioStats.containsKey(key)) {
                int existingValue = existingAudioStats.get(key);
                existingAudioStats.put(key, existingValue + 1);
            } else {
                existingAudioStats.put(key, newValue);
            }
        }

        try (Writer writer = new FileWriter("audioStats.txt")) {
            gson.toJson(existingAudioStats, writer); // update the audioStats in the audioInfo object
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private HashMap<String, Integer> loadExistingAudioStatsFromFile() {
        try (Reader reader = new FileReader("audioStats.txt")) {
            return gson.fromJson(reader, new TypeToken<HashMap<String, Integer>>() {}.getType());
        } catch (IOException e) {
            e.printStackTrace();
            return new HashMap<>();
        }
    }

    public void loadAudioStatsFromFile() {
        HashMap<String, Integer> audioStats = new HashMap<>();
        try (Reader reader = new FileReader("audioStats.txt")) {
            Type type = new TypeToken<HashMap<String, Integer>>(){}.getType();
            audioStats = gson.fromJson(reader, type);
            System.out.println("audioStats loaded: " + audioStats);
            if (audioStats != null) {
                audioInfo.setAudioStats(audioStats);
                System.out.println("audioStats key: " + audioInfo.getAudioStats().keySet() + ", Value: " + audioInfo.getAudioStats().values());
            } else {
                System.out.println("audioStats is null");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //method that iterates through audioStats hashMap and returns the value of the inputed key
    public int getAudioStats(String link) {
        HashMap<String, Integer> audioStats = audioInfo.getAudioStats();
        if (audioStats.containsKey(link)) {
            return audioStats.get(link);
        } else {
            return 0;
        }
    }
}
