package Controller;

import Model.AudioInfo;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;

public class AudioInputManager {

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








}
