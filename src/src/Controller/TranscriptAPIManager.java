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
 * Glavna klasa u Controlleru
 */

public class TranscriptAPIManager {

    private String filePath = ".\\transcripts\\";
    private static boolean isTranscripted=true;

    Transcript transcript = new Transcript();

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

            String transcriptStatus = transcript.getStatus();

            if ("completed".equals(transcriptStatus)) {
                // Kreirajte File objekt za provjeru postojanja direktorija
                File directory = new File(filePath + "\\" + UserInfo.nickname);

                // Provjerite postoji li direktorij, a ako ne postoji, stvorite ga
                if (!directory.exists()) {
                    directory.mkdirs(); // Stvaramo direktorij ako ne postoji
                }

                // Kreirajte File objekt za putanju datoteke
                File transcriptFile = new File(filePath + "\\" + UserInfo.nickname + "\\" + AudioInfo.audioName + ".txt");

                // Provjerite postoji li datoteka, a ako ne postoji, stvorite je
                if (!transcriptFile.exists()) {
                    try {
                        transcriptFile.createNewFile(); // Stvaramo datoteku ako ne postoji
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }


                try {
                    // Otvaramo buffered writer gdje ćemo zapisivati naš transkript
                    BufferedWriter writer = new BufferedWriter(new FileWriter(transcriptFile));
                    //System.out.println(transcriptFile.getAbsolutePath()); // Ispis putanje radi provjere

                    writer.write(transcript.getText());

                    // Zatvaramo buffered writer
                    writer.close();

                    // Prekidamo vezu s klijentom
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


