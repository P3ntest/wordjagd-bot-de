package me.P3ntest.threads;

import me.P3ntest.game.GameField;

import javax.swing.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class OutputStreamThread extends Thread {

    JTextField output;
    String wordField;

    public OutputStreamThread(JTextField output, String wordField) {
        this.output = output;
        this.wordField = wordField;
    }

    public void run() {
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

            while (fileScanner.hasNextLine()) {
                String word = fileScanner.nextLine();
                System.out.println(word);
                if (gameField.containsWord(word.toLowerCase())) {
                    outputText += word + " ";
                    output.setText(outputText);
                }
            }
            fileScanner.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
