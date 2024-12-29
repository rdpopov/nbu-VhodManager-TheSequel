package org.example.utils;

import java.util.Random;
import java.util.Date;

public class VladoRandoma {
    private static String letters = "abcdefghijklmnopqrstuvwxyz";
    private static String upperLetters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static String numbers = "1234567890";
    private static String alnum = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
    private static Random random;

    public static Random getRandom() {
        if (random == null) {
            random = new Random(42069);
        }
        return random;
    }
    public static String generateRandomChars (String candidateChars, int length) {
        StringBuilder sb = new StringBuilder ();
        Random random = getRandom();
        for (int i = 0; i < length; i ++) {
            sb.append (candidateChars.charAt(random
                                .nextInt(candidateChars.length())));
        }

        return sb.toString ();
    }

    public static String generateName() {
        return generateRandomChars(upperLetters,1) + generateRandomChars(letters, 5);
    }

    public static String generateStringNumbber(int len) {
        return generateRandomChars(numbers,len);
    }

    public static Double randomSmallDouble() {
        Random random = getRandom();
        return Double.valueOf(random.nextInt(100));
    }

    public static Integer randomInt(int cap) {
        Random random = getRandom();
        return random.nextInt(cap) + 1;
    }

    public static String generateAdress() {
        return generateName() + 
            ", st. " + generateName() +
            "nr " + generateStringNumbber(2);
    }

    public static Date generateDate() {
        long ms = -946771200000L + (Math.abs(getRandom().nextLong()) % (70L * 365 * 24 * 60 * 60 * 1000));
        return new Date(ms);
    }

}
