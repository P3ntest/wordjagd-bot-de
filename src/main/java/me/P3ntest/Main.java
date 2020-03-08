package me.P3ntest;

import me.P3ntest.game.GameField;

public class Main {

    public static void main(String[] args) {

        Character[][] field = new Character[4][4];
        field[0][0] = 'a'; field[1][0] = 'd'; field[2][0] = 'a'; field[3][0] = 'a';
        field[0][1] = 'r'; field[1][1] = 'd'; field[2][1] = 'a'; field[3][1] = 'a';
        field[0][2] = 'a'; field[1][2] = 'e'; field[2][2] = 'a'; field[3][2] = 'a';
        field[0][3] = 'e'; field[1][3] = 'a'; field[2][3] = 'a'; field[3][3] = 'a';


        GameField gameField = new GameField(field);
        gameField.printField();
        System.out.println(gameField.containsWord("derd"));
    }

}
