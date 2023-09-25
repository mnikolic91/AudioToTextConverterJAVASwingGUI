package Controller;

import Model.UserInfo;

import java.io.*;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.HashMap;
import java.util.Map;

/*
    * This class is used for hashing the password and saving it to the text file
 */

public class LoginAndRegistrationManager {
    //initiation of a new object of UserInfo class
    private UserInfo userInfo = new UserInfo();

    private static final String ALPHABET = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";

    //generateRandomString method that generates random string of 10 characters which is used to create a unique "salt" for each user
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

    //doHashing method that hashes the password and salt and saves it to the userInfo object
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

    //doesNicknameExists method that checks if the nickname already exists in the text file
    public boolean doesNicknameExists(String nickname) {
        try (BufferedReader reader = new BufferedReader(new FileReader("hashing.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length > 0 && parts[0].equals(nickname)) {
                    System.out.println("Nickname already exists!");
                    return true; //Nickname is found in the text file
                }
            }return false;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false; // Nickname is not found in the text file
    }

    //saveHashAndSaltToTextFile method that saves the nickname, hashed password and salt to the text file
    public void saveHashAndSaltToTextFile(String nickname) {
            try (BufferedWriter writer = new BufferedWriter(new FileWriter("hashing.txt", true))) {
                String line = nickname + "," + userInfo.getPassword() + "," + userInfo.getSalt();
                writer.write(line);
                writer.newLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    //readHashAndSaltFromTextFile method that reads the nickname, hashed password and salt from the text file
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
