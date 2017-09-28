package com.soomla.robertlesser.soomla;

import java.util.Random;

/**
 * Created by robertlesser on 28/09/2017.
 */

public class Utility {

    public static String getRandom(String[] array){
        int rnd = new Random().nextInt(array.length);
        return array[rnd];
    }
}
