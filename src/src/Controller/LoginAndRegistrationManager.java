package Controller;

import Model.UserInfo;

import java.io.*;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.HashMap;
import java.util.Map;

/**
 * This class is responsible for the login and registration manager.
 * It contains the methods that generate random string, do hashing and send post request to the server.
 */

public class LoginAndRegistrationManager {
    //initiation of a new object of UserInfo class
    private UserInfo userInfo = new UserInfo();

    private static final String ALPHABET = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";

    /**
     * This method sends post request to the server.
     * @param nickname
     * @param password
     * @param salt
     * @return
     */
    public void generateRandomString() {
        SecureRandom random = new SecureRandom();
        StringBuilder salt = new StringBuilder();

        for (int i = 0; i < 10; i++) {
            int randomIndex = random.nextInt(ALPHABET.length());
            char randomChar = ALPHABET.charAt(randomIndex);
            salt.append(randomChar);
        }
        userInfo.setSalt(salt.toString());
    }

    /**
     * This method does hashing.
     * @param password
     * @return
     */
    public String doHashing(String password) {
        userInfo.setPassword(password + userInfo.getSalt());
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(userInfo.getPassword().getBytes());
            byte[] digest = md.digest();
            StringBuilder sb = new StringBuilder();
            for (byte b : digest) {
                sb.append(String.format("%02x", b & 0xff));
            }
            userInfo.setPassword(sb.toString());
            System.out.println("Hashed password: " + userInfo.getPassword());
            return userInfo.getPassword();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return "";
    }

    /**
     * This method checks if the nickname exists.
     * @param nickname
     * @return
     */
    public boolean doesNicknameExists(String nickname) {
        File file = new File("hashing.txt");

        // Provjerite postoji li datoteka, a ako ne postoji, stvorite je
        if (!file.exists()) {
            try {
                file.createNewFile(); // Stvaramo datoteku ako ne postoji
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

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
        return false;
    }

    /**
     * This method saves the nickname, hashed password and salt to the text file.
     * @param nickname
     */
    public void saveHashAndSaltToTextFile(String nickname) {
        File file = new File("hashing.txt");

        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
            try (BufferedWriter writer = new BufferedWriter(new FileWriter("hashing.txt", true))) {
                String line = nickname + "," + userInfo.getPassword() + "," + userInfo.getSalt();
                writer.write(line);
                writer.newLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    /**
     * This method reads the nickname, hashed password and salt from the text file.
     * @param nickname
     * @return
     */
    public Map<String, String> readHashAndSaltFromTextFile(String nickname) {
        File file = new File("hashing.txt");

        if (!file.exists()) {
            try {
                file.createNewFile(); // Stvaramo datoteku ako ne postoji
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        Map<String, String> userData = new HashMap<>();
        try (BufferedReader reader = new BufferedReader(new FileReader("hashing.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 3 && parts[0].equals(nickname)) {
                    userData.put("nickname", parts[0]);
                    userData.put("hashedPassword", parts[1]);
                    userData.put("salt", parts[2]);
                    return userData;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return userData; // Returns empty map if the nickname is not found in the text file
    }

    /**
     * This method compares the hashed password from the text file with the hashed password from the login window.
     * @param nickname
     * @param password
     * @return
     */
    public boolean compareHashedPassword(String nickname, String password) {
        Map<String, String> userData = readHashAndSaltFromTextFile(nickname);
        String hashedPassword = userData.get("hashedPassword");
        String salt = userData.get("salt");
        userInfo.setSalt(salt);
        String hashedPasswordFromLoginWindow = doHashing(password);
        if (hashedPassword.equals(hashedPasswordFromLoginWindow)) {
            System.out.println("Password is correct!");
            return true;
        } else {
            System.out.println("Password is incorrect!");
            return false;
        }
    }

    //method that changes user status to logged in
    public void changeUserStatusToLoggedIn() {
        userInfo.setLoggedIn(true);
    }


    //method that checks if the user is logged in
    public boolean isUserLoggedIn() {
        return userInfo.isLoggedIn();
    }

    //method that checks if username is logged in and returns the user nickname
    public String getLoggedInUserNickname() {
        if (userInfo.isLoggedIn()) {
            return userInfo.getNickname();
        } else {
            return null;
        }
    }

    //method that gets the user nickname
    public String getUserNickname() {
        return userInfo.getNickname();
    }

    //mthod that sets the user nickname
    public void setUserNickname(String nickname) {
        userInfo.setNickname(nickname);
    }

}
