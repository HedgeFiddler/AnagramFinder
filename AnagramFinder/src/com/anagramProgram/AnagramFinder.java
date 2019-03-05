package com.anagramProgram;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.HashMap;

public class AnagramFinder {

    public static void main(String[] args) {
        long startTime = System.nanoTime();
        String searchedWord = "";
        if(args.length <2){
            System.out.println("Missing dictionary file path or searched word");
            return;
        }
        else if(args.length > 2){
            searchedWord += args[1] + " " + args[2];
        } else{
            searchedWord += args[1];
        }
        String result = "";
        File dictionaryFile = new File(args[0]);

        try {
            for (String line: Files.readAllLines(dictionaryFile.toPath(), StandardCharsets.ISO_8859_1)){
                if(isAnagram(searchedWord, line) && !searchedWord.equals(line)){
                    result += "," + line.toLowerCase();
                }
            }
        } catch (IOException e) {
            System.out.println("No such file, check the entered file path");
        }

        long stop = System.nanoTime() - startTime;
        System.out.println(stop + result);

    }

    private static boolean isAnagram(String s1, String s2) {
        HashMap<Character, Integer> letters = new HashMap<>();

        if (s1.length() != s2.length()) {
            return false;
        }
        for (Character c : s1.toCharArray()) {
            letters.put(Character.toLowerCase(c), letters.getOrDefault(Character.toLowerCase(c), 0) + 1);
        }
        for (Character c : s2.toCharArray()) {
            letters.put(Character.toLowerCase(c), letters.getOrDefault(Character.toLowerCase(c), 0) - 1);
        }
        for (Integer v : letters.values()) {
            if (v != 0) {
                return false;
            }
        }
        return true;
    }
}
