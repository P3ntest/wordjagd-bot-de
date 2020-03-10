package me.P3ntest.threads;

import javafx.application.Application;
import javafx.stage.Stage;
import me.P3ntest.Main;
import me.P3ntest.game.GameField;

import javax.swing.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class OutputStreamThread extends Thread {

    JTextArea output;
    String wordField;

    public OutputStreamThread(JTextArea output, String wordField) {
        this.output = output;
        this.wordField = wordField;
    }

    public void run() {
        System.out.println("Starting streaming task.");

        Character[][] field = new Character[4][4];

        int at = 0;
        for (int x = 0; x < 4; x++) {
            for (int y = 0; y < 4; y++) {
                field[x][y] = wordField.charAt(at);
                at++;
            }
        }

        GameField gameField = new GameField(field);
        gameField.printField();



        try {

            //File file = new File();

            Scanner fileScanner = new Scanner(this.getClass().getClassLoader().getResourceAsStream("words.txt"));

//            Scanner fileScanner  = new Scanner(
//                    new InputStreamReader(new FileInputStream(words),"ISO-8859-1"));

            List<String> finalWords = new ArrayList<>();

            while (fileScanner.hasNextLine()) {
                String word = fileScanner.nextLine();
                if (gameField.containsWord(word.toLowerCase())) {
                    finalWords.add(word.toLowerCase());
                }
            }
            fileScanner.close();

            finalWords.sort(new OutputStreamThreadLengthComparator());

            int chars = 0;
            int breakChars = 150;
            String outputText = "";
            int currLen = finalWords.get(0).length();
            for (String finalWord : finalWords) {
                boolean lenChange = false;
                if (finalWord.length() != currLen) {
                    lenChange = true;
                    currLen = finalWord.length();
                }

                if (lenChange)
                    outputText += "\n\n";

                outputText += finalWord + "   ";
                chars += finalWord.length() + 3;
                if (chars >= breakChars && !lenChange) {
                    outputText += "\n";
                    chars = 0;
                }
                output.setText(outputText);
                System.out.println("Word: " + finalWord + " | lc: " + lenChange);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println("Finished streaming words.");
    }

}
