package Controller;

import Model.AudioInfo;
import Model.Transcript;
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
 * Glavna klasa u Controlleru
 */

public class TranscriptAPIManager {

    private String filePath = ".\\transcripts\\";
    private static boolean isTranscripted=true;

    Transcript transcript = new Transcript();
    AudioInfo audioInfo = new AudioInfo();

    /**
     * metoda koja salje zahtjev AssemblyAI API-ju, dohvaca i transcriptira audio u tekstualni format, te ga ispisuje u lokalni text file
     *
     * @param url
     * @throws URISyntaxException
     * @throws IOException
     * @throws InterruptedException
     * @see <a href="https://www.youtube.com/watch?v=9oq7Y8n1t00&ab_channel=CodingwithJohn">Coding with John</a>
     * @see <a href="https://www.assemblyai.com/">Assembly AI</a>
     */
    public void postGetTrans(String url) throws URISyntaxException, IOException, InterruptedException {
        //novi objekt klase InfoGetSet koja nam olaksava pristup json elementima API-ja

        //postavljamo audio URL koji zelimo transkribirati
        transcript.setAudio_url(url);

        //pozivamo importirani Gson koji nam sluzi kao posrednik za koristenje json podataka kod poziva API-ja
        //gson jar file downloadamo na linku: https://search.maven.org/artifact/com.google.code.gson/gson/2.9.1/jar
        //potrebno je dodati gson modul u novi library
        Gson gson = new Gson();
        Map<String, String> transcriptAudioUrl = new HashMap<>();
        transcriptAudioUrl.put("audio_url", transcript.getAudio_url());
        String jsonReq = gson.toJson(transcriptAudioUrl);
        //System.out.println("jsdon:" +jsonReq);

        //radimo prvi post request
        //saljemo request Assembly AI-u da zelimo zapoceti transkripciju
        HttpRequest postRequest = HttpRequest.newBuilder()
                .uri(new URI("https://api.assemblyai.com/v2/transcript"))
                .header("Authorization", "ecfb0dd8374b42519c33e3a4acd6f351")
                .POST(HttpRequest.BodyPublishers.ofString(jsonReq))
                .build();

        System.out.println("post request " + postRequest);

        //pozivamo novog klijenta
        HttpClient httpClient = HttpClient.newHttpClient();
        //System.out.println("http client "+httpClient);

        //prizivamo body od post requesta gdje mozemo vidjeti id requesta, status, text i slicno
        HttpResponse<String> postResponse = httpClient.send(postRequest, HttpResponse.BodyHandlers.ofString());
        //System.out.println("post response "+postResponse);
        //System.out.println("body "+ postResponse.body());
        transcript = gson.fromJson(postResponse.body(), Transcript.class);
        //System.out.println("transcript "+transcript.toString());
        //System.out.println(transcript.getId());

        //sastavljamo get request
        //jako slicno kao post request, ali sada dodajemo id u nastavku linka
        HttpRequest getRequest = HttpRequest.newBuilder()
                .uri(new URI("https://api.assemblyai.com/v2/transcript/" + transcript.getId()))
                .header("Authorization", "ecfb0dd8374b42519c33e3a4acd6f351")
                .build();

        //provjera da li prepoznaje id
        //System.out.println("https://api.assemblyai.com/v2/transcript/" + transcript.getId());

        //ulazimo u while petlju dok assembly AI ne zavrsi transkripciju audia
        //petlja se prekida kad status procesa bude zavrsen
        while (true) {
            HttpResponse<String> getResponse = httpClient.send(getRequest, HttpResponse.BodyHandlers.ofString());
            transcript = gson.fromJson(getResponse.body(), Transcript.class);
            System.out.println(transcript.getStatus());

            if ("completed".equals(transcript.getStatus())) {
                //otvaramo buffered write gdje cemo zapisivati nas transcript
                BufferedWriter writer = new BufferedWriter(new FileWriter(filePath + audioInfo.getUniqueValue()+".txt"));
                System.out.println(audioInfo.getAudioName());
                writer.write(transcript.getText());
                //zatvaramo buffered writer
                writer.close();
                //prekidamo vezu sa klijentom
                break;

            } else if ("processing".equals(transcript.getStatus())) {
                Thread.sleep(1000);
            } else {
                System.err.println("Invalid input!");
                isTranscripted = false;
                break;
            }
        }



        //provjera da li smo dohvatili tekst
        //System.out.println(transcript.getText());
    }

    public static boolean isIsTranscripted() {
        return isTranscripted;
    }
}


