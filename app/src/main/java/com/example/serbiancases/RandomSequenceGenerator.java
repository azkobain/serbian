package com.example.serbiancases;
import java.util.Random;

public class RandomSequenceGenerator {
    public static int[] generateRandomSequence(int n) {
        int[] sequence = new int[n];
        for (int i = 0; i < n; i++) {
            sequence[i] = i;
        }

        Random random = new Random();
        for (int i = n - 1; i >= 0; i--) {
            int j = random.nextInt(i + 1);

            // Swap elements at indices i and j
            int temp = sequence[i];
            sequence[i] = sequence[j];
            sequence[j] = temp;
        }

        return sequence;
    }
}
