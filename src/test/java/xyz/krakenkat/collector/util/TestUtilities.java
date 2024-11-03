package xyz.krakenkat.collector.util;

import lombok.SneakyThrows;
import org.springframework.core.io.ClassPathResource;

import java.nio.charset.StandardCharsets;
import java.util.Random;

public class TestUtilities {

    private static final String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
    private static final Random RANDOM = new Random();

    // Method to get json content from json file and parse as String to compare against actual result.
    @SneakyThrows
    public static String fromFile(String path) {
        String folder = "json/";
        return new String(new ClassPathResource(folder + path).getInputStream().readAllBytes(), StandardCharsets.UTF_8);
    }

    // Method to generate a random key
    public static String generateRandomKey(int length) {
        StringBuilder sb = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            int randomIndex = RANDOM.nextInt(CHARACTERS.length());
            sb.append(CHARACTERS.charAt(randomIndex));
        }
        return sb.toString();
    }

    private TestUtilities() {}
}
