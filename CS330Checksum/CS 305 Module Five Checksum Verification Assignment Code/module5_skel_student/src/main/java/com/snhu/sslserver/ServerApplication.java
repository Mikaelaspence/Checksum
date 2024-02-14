package com.snhu.sslserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@SpringBootApplication
public class ServerApplication {

	//Starting Springboot to run
    public static void main(String[] args) {
        SpringApplication.run(ServerApplication.class, args);
    }
    

    // Helper function to convert bytes to hexadecimal string
    public static String bytesToHex(byte[] hashBytes) {
        StringBuilder hexString = new StringBuilder();
        for (byte hashByte : hashBytes) {
            String hex = Integer.toHexString(0xff & hashByte);
            if (hex.length() == 1) {
                hexString.append('0');
            }
            hexString.append(hex);
        }
        return hexString.toString();
    }
}

@RestController
class ServerController {

    @RequestMapping("/hash")
    public String generateHash() {
        // Create MessageDigest object for SHA-256 algorithm
        MessageDigest digest;
        try {
            digest = MessageDigest.getInstance("SHA-256");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return "Could not initialize";
        }

        String data = "Hello Mikaela Spence!";

        // Generate hash value of byte type from the data
        byte[] hashBytes = digest.digest(data.getBytes());

        // Convert hash value to hexadecimal string using helper function above
        String checksum = ServerApplication.bytesToHex(hashBytes);

        // Return "Hello 'my name'" and its SHA-256 checksum
        return "<p>Data: " + data + "</p><p>Checksum: " + checksum + "</p>";
    }

}



