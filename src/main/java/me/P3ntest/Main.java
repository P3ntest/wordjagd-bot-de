package me.P3ntest;

import me.P3ntest.game.GameField;
import me.P3ntest.gui.BotForm;
import me.P3ntest.threads.OutputStreamThread;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    private static Scanner userInputScanner;

    @Deprecated
    public static List<String> getWordsOfField(String inputField) {
        Character[][] field = new Character[4][4];

        int at = 0;
        for (int x = 0; x < 4; x++) {
            for (int y = 0; y < 4; y++) {
                field[x][y] = inputField.charAt(at);
                at++;
            }
        }

        GameField gameField = new GameField(field);
        gameField.printField();

        List<String> wordList = new ArrayList<>();

        try {
            File words = new File("words.txt");
            Scanner fileScanner = new Scanner(words);
            while (fileScanner.hasNextLine()) {
                String word = fileScanner.nextLine();
                if (gameField.containsWord(word.toLowerCase())) {
                    wordList.add(word);
                }
            }
            fileScanner.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return wordList;
    }

    static OutputStreamThread currThread = null;

    public static void streamOutput(String inputField, JTextArea output) {
        if (currThread != null) {
            currThread.stop();
        }
        currThread = new OutputStreamThread(BotForm._instance.output, inputField);
        currThread.start();
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            BotForm botForm = new BotForm();
            botForm.setVisible(true);

            try {
                UIManager.setLookAndFeel(
                        UIManager.getSystemLookAndFeelClassName());
            } catch (Exception e) {
                e.printStackTrace();
            }

        });

        userInputScanner = new Scanner(System.in);

        String inputField = userInputScanner.next();

        Character[][] field = new Character[4][4];

        int at = 0;
        for (int x = 0; x < 4; x++) {
            for (int y = 0; y < 4; y++) {
                field[x][y] = inputField.charAt(at);
                at++;
            }
        }

        GameField gameField = new GameField(field);
        gameField.printField();

        System.out.println();

        try {
            File words = new File("words.txt");
            Scanner fileScanner = new Scanner(words);
            while (fileScanner.hasNextLine()) {
                String word = fileScanner.nextLine();
                if (gameField.containsWord(word.toLowerCase())) {
                    System.out.println(word);
                }
            }
            fileScanner.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
