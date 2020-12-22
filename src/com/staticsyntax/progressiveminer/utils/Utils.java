package com.staticsyntax.progressiveminer.utils;

import com.staticsyntax.progressiveminer.ProgressiveMiner;

import java.util.Arrays;

public class Utils {

    public static void openBank() {
        try {
            ProgressiveMiner.getApi().getBank().open();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static <T> T[] concat(T[] first, T[] second) {
        T[] result = Arrays.copyOf(first, first.length + second.length);
        System.arraycopy(second, 0, result, first.length, second.length);
        return result;
    }
}
