package ru.chupakabr.codinggame.java.odesk;

import java.io.*;
import java.util.*;

/**
 * Using default package and class name "Solution" because it's the requirement.
 *
 * Created by myltik
 * Created on 10/19/13 11:13 AM
 */
public class Solution {

    /**
     * File buffered reader
     */
    private final BufferedReader fileReader;

    /**
     * Work available in the dictionary.
     * Is read from input file header.
     */
    private final int wordsAvailable;

    /**
     * Available letters to use
     */
    private char[] lettersAvailable;

    /**
     * A char which is used to mark already used chars
     */
    private final char USED_CHAR = '1';

    /**
     * The same available letters count per each input file
     */
    private static final int AVAILABLE_LETTERS_COUNT = 7;

    /**
     * Weights table
     */
    private static final Map<Character, Integer> WEIGHT_TABLE;
    static {
        WEIGHT_TABLE = new HashMap<Character, Integer>(26);

        WEIGHT_TABLE.put('e', 1);
        WEIGHT_TABLE.put('a', 1);
        WEIGHT_TABLE.put('i', 1);
        WEIGHT_TABLE.put('o', 1);
        WEIGHT_TABLE.put('n', 1);
        WEIGHT_TABLE.put('r', 1);
        WEIGHT_TABLE.put('t', 1);
        WEIGHT_TABLE.put('l', 1);
        WEIGHT_TABLE.put('s', 1);
        WEIGHT_TABLE.put('u', 1);

        WEIGHT_TABLE.put('d', 2);
        WEIGHT_TABLE.put('g', 2);

        WEIGHT_TABLE.put('b', 3);
        WEIGHT_TABLE.put('c', 3);
        WEIGHT_TABLE.put('m', 3);
        WEIGHT_TABLE.put('p', 3);

        WEIGHT_TABLE.put('f', 4);
        WEIGHT_TABLE.put('h', 4);
        WEIGHT_TABLE.put('v', 4);
        WEIGHT_TABLE.put('w', 4);
        WEIGHT_TABLE.put('y', 4);

        WEIGHT_TABLE.put('k', 5);

        WEIGHT_TABLE.put('j', 8);
        WEIGHT_TABLE.put('x', 8);

        WEIGHT_TABLE.put('q', 10);
        WEIGHT_TABLE.put('z', 5);
    }

    private String bestWord;
    private int bestWordWeight;

    /**
     * App entry point
     * @param args Command line args
     */
    public static void main(String[] args) {
        try {
            Solution app = new Solution();
            app.run();
        } catch (Throwable t) {
            t.printStackTrace();
            e("");
        }
    }

    /**
     * Log error message
     * @param msg
     */
    private static final void e(String msg) {
        System.out.println("ERR " + msg);
    }

    /**
     * Log debug message
     * @param msg
     */
    private static final void d(String msg) {
        System.out.println("DEBUG " + msg);
    }

    /**
     * Log info message
     * @param msg
     */
    private static final void i(String msg) {
        System.out.println(msg);
    }

    /**
     * Default constructor
     */
    public Solution() throws IOException {
        this.fileReader = new BufferedReader(new InputStreamReader(System.in));

        this.wordsAvailable = Integer.parseInt(fileReader.readLine());

        bestWord = null;
        bestWordWeight = 0;
    }

    /**
     * Process specified input file
     */
    void run() throws IOException {
//        d("Processing " + inputFile.getAbsolutePath() + " file with " + wordsAvailable + " words available: " + new String(lettersAvailable));

        List<String> loadedWords = new ArrayList<String>(wordsAvailable);
        String line;
        for (int i = 0; i < wordsAvailable; ++i) {
            line = fileReader.readLine();
            loadedWords.add(line);
        }

        line = fileReader.readLine();
        lettersAvailable = line.toCharArray();

        processLoadedWords(loadedWords);

        i(bestWord);
    }

    /**
     * Process currently loaded words and try to find the largest one.
     * Update bestWord and bestWordWeight values if needed.
     * @param loadedWords
     */
    private void processLoadedWords(List<String> loadedWords) {
        int weight;
        for (String word : loadedWords) {
            if (word.length() <= AVAILABLE_LETTERS_COUNT) {
                weight = weight(word);

//                d("w(" + word + ")=" + weight);

                if (weight > bestWordWeight) {
                    bestWord = word;
                    bestWordWeight = weight;
                }
            }
        }
    }

    /**
     * Calculate weight of a word
     * @param word
     * @return Word's weight
     */
    private int weight(String word) {
        boolean found;
        int weight = 0;
        char ch;
        char[] lettersCopy = lettersAvailable.clone();;

        for (int i = 0; i < word.length(); ++i) {
            found = false;
            ch = word.charAt(i);

            for (int k = 0; k < lettersCopy.length; ++k) {
                if (lettersCopy[k] == ch) {
                    found = true;
                    lettersCopy[k] = USED_CHAR;
                    break;
                }
            }

            if (!found) {
                return 0;
            }
            weight += WEIGHT_TABLE.get(ch);
        }

        return weight;
    }
}
