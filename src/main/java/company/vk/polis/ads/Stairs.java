package company.vk.polis.ads;

import java.util.Scanner;

public class Stairs {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        final int N = in.nextInt();
        int[] stairs = new int[N + 2];
        for (int i = 0; i < N; i++) {
            stairs[i + 1] = in.nextInt();
        }
        final int k = in.nextInt();
        int[] maximums = new int[k];
        for (int i = 1; i < N + 2; i++) {
            int stairsBeforeMaximum = max(maximums);
            for (int j = 0; j < k - 1; j++) {
                maximums[j] = maximums[j + 1];
            }
            maximums[k - 1] = stairsBeforeMaximum + stairs[i];
        }
        System.out.println(maximums[k - 1]);
    }

    private static int max(int[] array) {
        int maximum = array[0];
        for (int i = 1; i < array.length; i++) {
            if (array[i] > maximum) {
                maximum = array[i];
            }
        }
        return maximum;
    }
}
