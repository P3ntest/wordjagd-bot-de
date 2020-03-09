package me.P3ntest.game;

import java.util.ArrayList;
import java.util.List;

public class GameField {

    private Character[][] field;

    private List<CharPosition> chars;

    public void printField() {
        for (int x = 0; x < 4; x++) {
            String line = "";
            for (int y = 0; y < 4; y++) {
                line += Character.toUpperCase(field[x][y]) + " ";
            }
            System.out.println(line);
        }
    }

    public GameField(Character[][] field) {
        this.field = field;

        this.chars = new ArrayList<>();
        for (int x = 0; x < 4; x++) {
            for (int y = 0; y < 4; y++) {
                chars.add(new CharPosition(x, y));
            }
        }
    }

    private Character getCharOfPosition(CharPosition charPosition) {
        return this.field[charPosition.x][charPosition.y];
    }

    public boolean containsWord(String word) {
        List<CharPosition> potentialStartingChars = new ArrayList<>();

        this.chars.forEach(charPosition -> {
            if (getCharOfPosition(charPosition) == word.charAt(0))
                potentialStartingChars.add(charPosition);
        });

        for (CharPosition charPosition : potentialStartingChars) {
            List<CharPosition> tempUsed = new ArrayList<>();
            tempUsed.add(charPosition);
            if (checkIfPathLeadesToSucces(tempUsed, word.toLowerCase(), charPosition))
                return true;
        }

        return false;
    }

    List<CharPosition> getRelatives(CharPosition charPosition) {
        List<CharPosition> relatives = new ArrayList<>();
        relatives.add(new CharPosition(charPosition.x + 1, charPosition.y));
        relatives.add(new CharPosition(charPosition.x + 1, charPosition.y + 1));
        relatives.add(new CharPosition(charPosition.x + 1, charPosition.y - 1));
        relatives.add(new CharPosition(charPosition.x, charPosition.y + 1));
        relatives.add(new CharPosition(charPosition.x, charPosition.y - 1));
        relatives.add(new CharPosition(charPosition.x - 1, charPosition.y));
        relatives.add(new CharPosition(charPosition.x - 1, charPosition.y + 1));
        relatives.add(new CharPosition(charPosition.x - 1, charPosition.y - 1));
        List<CharPosition> toBeRemoved = new ArrayList<>();
        for (CharPosition relative : relatives) {
            if (relative.y > 3 || relative.y < 0 || relative.x > 3 || relative.x < 0) {
                toBeRemoved.add(relative);
            }
        }
        toBeRemoved.forEach(charPosition1 -> {
            relatives.remove(charPosition1);
        });
        return relatives;
    }

    boolean containsCharPosition(List<CharPosition> charPositions, CharPosition charPosition) {
        for (CharPosition position : charPositions) {
            if (position.x == charPosition.x && position.y == charPosition.y) {
                return true;
            }
        }
        return false;
    }

    boolean checkIfPathLeadesToSucces(List<CharPosition> used, String word, CharPosition at) {
        if (used.size() >= word.length()) {
            return true;
        }
        for (CharPosition relative : getRelatives(at)) {
            if (!containsCharPosition(used, relative)) {
                Character c1 = Character.toLowerCase(getCharOfPosition(relative));
                Character c2 = Character.toLowerCase(word.charAt(used.size()));
                if (c1.toString().equalsIgnoreCase(c2.toString())) {
                    List<CharPosition> tempUsed = new ArrayList<>();
                    used.forEach(charPosition1 -> tempUsed.add(charPosition1));
                    tempUsed.add(relative);
                    if (checkIfPathLeadesToSucces(tempUsed, word, relative)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }
    
}
