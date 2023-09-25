package Controller;

import Model.UserInfo;

import java.io.*;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.HashMap;
import java.util.Map;

public class PasswordHashingAndSaving {

    private UserInfo userInfo = new UserInfo();

    private static final String ALPHABET = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";

    //metoda koja generira random salt i sprema ga u varijablu salt
    private void generateRandomString() {
        SecureRandom random = new SecureRandom();
        StringBuilder salt = new StringBuilder();

        for (int i = 0; i < 10; i++) {
            int randomIndex = random.nextInt(ALPHABET.length());
            char randomChar = ALPHABET.charAt(randomIndex);
            salt.append(randomChar);
        }

        userInfo.setSalt(salt.toString());
        //System.out.println(userInfo.getSalt());
    }

    //doHashing metoda koja prima string i vraća hashiranu vrijednost uz pomoć MD5 algoritma
    public String doHashing(String password) {

        generateRandomString();
        userInfo.setPassword(password + userInfo.getSalt());
        System.out.println("Hashed password control: " + userInfo.getPassword());

        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(userInfo.getPassword().getBytes());
            byte[] digest = md.digest();
            StringBuilder sb = new StringBuilder();
            for (byte b : digest) {
                sb.append(String.format("%02x", b & 0xff));
            }

            userInfo.setPassword(sb.toString());


        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        return "";
    }

    public boolean doesNicknameExists(String nickname) {
        try (BufferedReader reader = new BufferedReader(new FileReader("hashing.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length > 0 && parts[0].equals(nickname)) {
                    System.out.println("Nickname already exists!");
                    return true; // Nickname već postoji u datoteci
                }
            }return false;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false; // Nickname nije pronađen u datoteci
    }

    public void saveHashAndSaltToTextFile(String nickname) {
            try (BufferedWriter writer = new BufferedWriter(new FileWriter("hashing.txt", true))) {
                String line = nickname + "," + userInfo.getPassword() + "," + userInfo.getSalt();
                writer.write(line);
                writer.newLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    public Map<String, String> readHashAndSaltFromTextFile(String nickname) {
        Map<String, String> userData = new HashMap<>();
        try (BufferedReader reader = new BufferedReader(new FileReader("hashing.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 3 && parts[0].equals(nickname)) {
                    userData.put("nickname", parts[0]);
                    userData.put("hashedPassword", parts[1]);
                    userData.put("salt", parts[2]);
                    System.out.println(userData);
                    return userData;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return userData; // Vrati prazan Map ako nickname nije pronađen
    }

}
