//https://www.eolymp.com/ru/submissions/11828987

import java.util.Arrays;
import java.util.Scanner;
import java.util.stream.IntStream;

public class CountInversions {
    public static void main(String[] args) {
        try (Scanner in = new Scanner(System.in)) {
            int n = in.nextInt();
            int[] array = IntStream.range(0, n).map(i -> in.nextInt()).toArray();
            System.out.println(countInv(array, 0, n - 1));
        }
    }

    private static int countInv(int[] a, int i, int j) {
        if (i >= j) {
            return 0;
        }
        int mid = (i + j) / 2;
        return countInv(a, i, mid) + countInv(a, mid + 1, j) + countSplitInv(a, i, mid, j);
    }

    private static int countSplitInv(int[] a, int l, int mid, int r) {
        int[] left = Arrays.copyOfRange(a, l, mid + 1);
        int[] right = Arrays.copyOfRange(a, mid + 1, r + 1);
        int i = 0;
        int j = 0;
        int k = l;
        int swaps = 0;
        while (i < left.length && j < right.length) {
            if (left[i] <= right[j]) {
                a[k++] = left[i++];
            } else {
                a[k++] = right[j++];
                swaps += (mid + 1) - (l + i);
            }
        }
        while (i < left.length) {
            a[k++] = left[i++];
        }
        while (j < right.length) {
            a[k++] = right[j++];
        }
        return swaps;
    }
}
