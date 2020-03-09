package me.P3ntest;

import com.sun.media.sound.SF2SoundbankReader;
import me.P3ntest.game.GameField;
import me.P3ntest.gui.BotAppForm;

import javax.swing.*;
import java.io.File;
import java.util.Scanner;

public class Main {

    private static Scanner userInputScanner;

    public static void main(String[] args) {

//        JFrame frame = new JFrame("appaa");
//        frame.setContentPane(new BotAppForm().rootPanel);
//        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        frame.pack();
//        frame.setVisible(true);

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

//        Character[][] field = new Character[4][4];
//        field[0][0] = 'n'; field[1][0] = 'w'; field[2][0] = 'i'; field[3][0] = 'y';
//        field[0][1] = 'n'; field[1][1] = 'e'; field[2][1] = 'f'; field[3][1] = 'ü';
//        field[0][2] = 'ü'; field[1][2] = 'n'; field[2][2] = 'c'; field[3][2] = 'h';
//        field[0][3] = 'e'; field[1][3] = 'u'; field[2][3] = 't'; field[3][3] = 'g';


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
