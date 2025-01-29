package com.scene_craft.Finall;

import java.security.SecureRandom;
import java.util.Random;

public class RandomUserIDGenerator {
    private static final String PREFIX = "USER"; // Fixed prefix
    private static final String FIXED_STRING = "MH"; // Fixed string
    private static final Random RANDOM = new SecureRandom();
    private static StringBuilder userID;

    public static String generateRandomUserID() {
        userID = new StringBuilder();

        userID.append(PREFIX); // Append the fixed prefix
        userID.append(String.format("%02d", RANDOM.nextInt(100))); // Append two-digit random number
        userID.append(FIXED_STRING); // Append the fixed string
        userID.append(String.format("%04d", RANDOM.nextInt(10000))); // Append four-digit random number

        return userID.toString();
    }

}
