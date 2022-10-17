//https://www.eolymp.com/ru/submissions/11828498

import java.util.Scanner;
import java.util.stream.IntStream;

public class Stairs {
    public static void main(String[] args) {
        try (Scanner in = new Scanner(System.in)) {
            int n = in.nextInt();
            int[] stairs = new int[n + 2];
            stairs[0] = 0;
            stairs[n + 1] = 0;
            IntStream.rangeClosed(1, n).forEach(i -> stairs[i] = in.nextInt());
            int k = in.nextInt();
            int max;
            int j = 0;
            for (int i = 1; i <= n + 1; i++) {
                max = stairs[j];
                for (j = i - k; j < i; j++) {
                    if (j < 0) {
                        j = 0;
                    }
                    if (max < stairs[j]) {
                        max = stairs[j];
                    }
                }
                stairs[i] = max + stairs[i];
            }
            System.out.println(stairs[n + 1]);
        }
    }
}
