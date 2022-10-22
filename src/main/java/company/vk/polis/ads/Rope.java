package company.vk.polis.ads;

import java.util.Scanner;

public class Rope {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int k = in.nextInt();
        long sum = 0;
        long[] ropes = new long[n];
        for (int i = 0; i < n; i++){
            ropes[i] = in.nextLong();
            sum += ropes[i];
        }
        long right = (sum / k) + 1;
        if (right == 1) {
            System.out.println(0);
            return;
        }
        long left = 1;
        while (left < right) {
            long mid = (right + left) >>> 1;
            int counter = 0;
            for (long len: ropes){
                counter += len / mid;
            }
            if (counter >= k) {
                left = mid + 1;
            }
            else {
                right = mid;
            }
        }
        System.out.println(right - 1);
    }
}
