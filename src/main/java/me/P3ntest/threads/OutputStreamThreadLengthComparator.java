package me.P3ntest.threads;

import java.util.Comparator;

public class OutputStreamThreadLengthComparator implements Comparator<String> {
    @Override
    public int compare(String s, String t1) {
        return t1.length() - s.length();
    }
}
