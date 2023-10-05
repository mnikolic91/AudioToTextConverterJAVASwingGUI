package Controller;

import Model.AudioInfo;
import Model.Transcript;
import Model.UserInfo;
import View.SecondWindow.ViewPanel;
import com.google.gson.Gson;

import java.io.*;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * This class is responsible for the transcript API manager.
 * It contains the method that sends a request to Assembly AI API to transcribe the audio.
 */

public class TranscriptAPIManager {

    private String filePath = ".\\transcripts\\";
    private static boolean isTranscripted = true;

    Transcript transcript = new Transcript();

    /**
     * method that sends a request to Assembly AI API to transcribe the audio
     *
     * @param url
     * @throws URISyntaxException
     * @throws IOException
     * @throws InterruptedException
     * @see <a href="https://www.youtube.com/watch?v=9oq7Y8n1t00&ab_channel=CodingwithJohn">Coding with John</a>
     * @see <a href="https://www.assemblyai.com/">Assembly AI</a>
     */
    public void postGetTrans(String url) throws URISyntaxException, IOException, InterruptedException {

        transcript.setAudio_url(url);

        Gson gson = new Gson();
        Map<String, String> transcriptAudioUrl = new HashMap<>();
        transcriptAudioUrl.put("audio_url", transcript.getAudio_url());
        String jsonReq = gson.toJson(transcriptAudioUrl);
        //System.out.println("jsdon:" +jsonReq);

        //send post request
        HttpRequest postRequest = HttpRequest.newBuilder()
                .uri(new URI("https://api.assemblyai.com/v2/transcript"))
                .header("Authorization", "ecfb0dd8374b42519c33e3a4acd6f351")
                .POST(HttpRequest.BodyPublishers.ofString(jsonReq))
                .build();

        System.out.println("post request " + postRequest);

        //calling http client
        HttpClient httpClient = HttpClient.newHttpClient();

        //calling the body of the post request
        HttpResponse<String> postResponse = httpClient.send(postRequest, HttpResponse.BodyHandlers.ofString());
        transcript = gson.fromJson(postResponse.body(), Transcript.class);


        //get request
        HttpRequest getRequest = HttpRequest.newBuilder()
                .uri(new URI("https://api.assemblyai.com/v2/transcript/" + transcript.getId()))
                .header("Authorization", "ecfb0dd8374b42519c33e3a4acd6f351")
                .build();


        //starting a loop until the audio is transcribed
        while (true) {
            HttpResponse<String> getResponse = httpClient.send(getRequest, HttpResponse.BodyHandlers.ofString());
            transcript = gson.fromJson(getResponse.body(), Transcript.class);
            System.out.println(transcript.getStatus());

            String transcriptStatus = transcript.getStatus();

            if ("completed".equals(transcriptStatus)) {
                File directory = new File(filePath + "\\" + UserInfo.nickname);

                if (!directory.exists()) {
                    directory.mkdirs();
                }

                File transcriptFile = new File(filePath + "\\" + UserInfo.nickname + "\\" + AudioInfo.audioName + ".txt");

                if (!transcriptFile.exists()) {
                    try {
                        transcriptFile.createNewFile();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }


                try {
                    //creating a buffered writer
                    BufferedWriter writer = new BufferedWriter(new FileWriter(transcriptFile));

                    writer.write(transcript.getText());

                    writer.close();

                    break;
                } catch (IOException e) {
                    e.printStackTrace();
                }


            } else if ("processing".equals(transcript.getStatus())) {
                Thread.sleep(1000);
            } else {
                System.err.println("Invalid input!");
                isTranscripted = false;
                break;
            }
        }

        ViewPanel.viewTextPane.setText(transcript.getText());
    }

    public static boolean isIsTranscripted() {
        return isTranscripted;
    }
}


