package com.staticsyntax.progressiveminer.utils;

import com.staticsyntax.progressiveminer.ProgressiveMiner;

public class CommonMethods {

    public static void openBank() {
        try {
            ProgressiveMiner.getApi().getBank().open();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
