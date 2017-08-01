package sample;

import java.util.Random;

public class RandomNumberFromTo {

    public static int nextInt(int low, int high) {
        Random generator = new Random();
        return low + generator.nextInt(high - low + 1);
    }
}
