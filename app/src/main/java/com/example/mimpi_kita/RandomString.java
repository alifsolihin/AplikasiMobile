package com.example.mimpi_kita;

import java.util.Locale;
import java.util.Random;

public class RandomString {
    private final String LETTERS="abcdefghijklmnopqrstuvwxyz";
    private final String NUMBER="0123456789";
    private final char[] ALPHANUMERIC=(LETTERS+LETTERS.toUpperCase()+NUMBER).toCharArray();

    public String generateAlphaNumeric(int length){
        StringBuilder result = new StringBuilder();
        for (int i=0; i<length ;i++){
            result.append(ALPHANUMERIC[new Random().nextInt(ALPHANUMERIC.length)]);
        }
        return result.toString();
    }
}
