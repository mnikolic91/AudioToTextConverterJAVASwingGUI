package Controller;

import Model.Converter;
import com.google.gson.Gson;

import javax.swing.*;
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

public class MainController {

    Converter transcript = new Converter();

    /**
     * metoda koja salje zahtjev AssemblyAI API-ju, dohvaca i transcriptira audio u tekstualni format, te ga ispisuje u lokalni text file
     *
     * @param name
     * @param url
     * @throws URISyntaxException
     * @throws IOException
     * @throws InterruptedException
     * @see <a href="https://www.youtube.com/watch?v=9oq7Y8n1t00&ab_channel=CodingwithJohn">Coding with John</a>
     * @see <a href="https://www.assemblyai.com/">Assembly AI</a>
     */
    public void postGetTrans(String name, String url) throws URISyntaxException, IOException, InterruptedException {
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
        transcript = gson.fromJson(postResponse.body(), Converter.class);
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
            transcript = gson.fromJson(getResponse.body(), Converter.class);
            System.out.println(transcript.getStatus());

            if ("completed".equals(transcript.getStatus())) {
                //otvaramo buffered write gdje cemo zapisivati nas transcript
                BufferedWriter writer = new BufferedWriter(new FileWriter(transcript.getCurrentUsersHomeDir() + "\\transcripts\\" + name + ".txt"));
                writer.write(transcript.getText());
                //zatvaramo buffered writer
                writer.close();
                //prekidamo vezu sa klijentom
                break;

            } else if ("processing".equals(transcript.getStatus())) {
                Thread.sleep(1000);
            } else {
                System.err.println("Invalid input!");
                break;
            }
        }

        //provjera da li smo dohvatili tekst
        //System.out.println(transcript.getText());
    }


    /**
     * metoda koja ispisuje sve fileove u folderu
     *
     * @param name
     * @param listField
     */
    public void listFilesInFolder(String name, JList listField) {


        //folder objekt kojem pridruzujemo putanju
        File folder = new File(transcript.getCurrentUsersHomeDir() + "\\transcripts\\" + name + "\\");
        //provjera da li folder postoji
        if (!folder.exists()) {
            folder.mkdirs();
        }
        //pozivamo DefaultListModel za JList
        DefaultListModel model = new DefaultListModel();
        //petlja koja puni model
        for (File f : folder.listFiles()) {
            model.addElement(f.getName());
        }
        //ispis modela u JListi
        listField.setModel(model);
    }

    /**
     * metoda koja koristi BufferedReader kojom ispisujemo tekst u JTextPane
     *
     * @param topics
     * @param filename
     * @param name
     */
    public void readTextInGUI(String topics, String filename, JTextPane name) {

        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader(transcript.getCurrentUsersHomeDir() + "\\transcripts\\" + topics + "\\" + filename));
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        }
        try {
            StringBuilder sb = new StringBuilder();
            String line = br.readLine();

            while (line != null) {
                sb.append(line);
                sb.append(System.lineSeparator());
                line = br.readLine();
            }
            String everything = sb.toString();
            name.setText(everything);
        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            try {
                br.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }
}


