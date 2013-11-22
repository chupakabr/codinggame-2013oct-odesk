package ru.chupakabr.codinggame.java.training;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Using default package and class name "Solution" because it's the requirement.
 *
 * Created by myltik
 * Created on 11/22/13 05:58 PM
 */
public class Solution {

    /**
     * File buffered reader
     */
    private final BufferedReader fileReader;

    private final int firstNum;
    private final int secondNum;

    private final int secondLineLength;

    private final String okResult;
    private final String failResult;


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
     * Log info message
     * @param msg
     */
    private static final void i(String msg) {
        System.out.println(msg);
    }

    /**
     * Default constructor.
     * Read values from input stream.
     */
    public Solution() throws IOException {
        this.fileReader = new BufferedReader(new InputStreamReader(System.in));

        String[] splittedLine = fileReader.readLine().split(" ");
        this.firstNum = Integer.parseInt(splittedLine[0]);
        this.secondNum = Integer.parseInt(splittedLine[1]);

        this.secondLineLength = fileReader.readLine().length();

        splittedLine = fileReader.readLine().split(" ");
        this.okResult = splittedLine[0];
        this.failResult = splittedLine[1];
    }

    /**
     * Actual processing
     */
    void run() throws IOException {
        if (secondLineLength == firstNum+secondNum) {
            i(okResult);
        } else {
            i(failResult);
        }
    }
}
