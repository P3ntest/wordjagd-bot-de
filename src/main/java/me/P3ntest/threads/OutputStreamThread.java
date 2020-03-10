package me.P3ntest.threads;

import me.P3ntest.game.GameField;

import javax.swing.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
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

        String outputText = "";

        try {
            File words = new File("words.txt");

            Scanner fileScanner  = new Scanner(
                    new InputStreamReader(new FileInputStream(words),"ISO-8859-1"));

            int chars = 0;
            int breakChars = 50;
            while (fileScanner.hasNextLine()) {
                String word = fileScanner.nextLine();
                if (gameField.containsWord(word.toLowerCase())) {
                    outputText += word + "   ";
                    chars += word.length() + 3;
                    if (chars >= breakChars) {
                        chars = 0;
                        outputText += "\n";
                    }
                    output.setText(outputText);
                }
            }
            fileScanner.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println("Finished streaming words.");
    }

}
