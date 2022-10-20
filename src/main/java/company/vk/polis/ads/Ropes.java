package company.vk.polis.ads;

import java.util.Scanner;

public class Ropes {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        final int n = in.nextInt();
        final int k = in.nextInt();
        int[] ropes = new int[n];
        ropes[0] = in.nextInt();
        int max = ropes[0];
        for (int i = 1; i < n; i++) {
            ropes[i] = in.nextInt();
            if (ropes[i] > max) {
                max = ropes[i];
            }
        }

        int l = 0;
        int r = max + 1;

        while (l < r - 1) {
            int ropeLength = l + (r - l) / 2;
            int houseCount = 0;
            for (int i = 0; i < n; i++) {
                houseCount += ropes[i] / ropeLength;
            }
            if (houseCount >= k) {
                l = ropeLength;
            } else {
                r = ropeLength;
            }
        }
        System.out.println(l);
    }
}