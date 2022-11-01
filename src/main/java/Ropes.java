//https://www.eolymp.com/ru/submissions/12010974
import java.util.Scanner;

public class Ropes {
    public static void main(String[] args) {
        try (Scanner in = new Scanner(System.in)) {
            int n = in.nextInt();
            int k = in.nextInt();
            int[] ropes = new int[n];
            int max = Integer.MIN_VALUE;
            for (int i = 0; i < n; i++) {
                int rope = in.nextInt();
                ropes[i] = rope;
                if (max < rope) {
                    max = rope;
                }
            }
            int l = 0;
            int r = max + 1;
            while (l < r - 1) {
                int mid = (l + r) >>> 1;
                int houseCount = 0;
                for (int length : ropes) {
                    int cnt = length / mid;
                    houseCount += cnt;
                }
                if (houseCount < k) {
                    r = mid;
                } else {
                    l = mid;
                }
            }
            System.out.println(l);
        }
    }
}
