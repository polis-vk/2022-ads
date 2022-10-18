package company.vk.polis.ads;

import java.util.Scanner;

/**
 * Problem solution template.
 *
 * @author Dmitry Schitinin
 */
public final class Task5 {
    private Task5() {
        // Should not be instantiated
    }

    private static int countInversions(int[] array, int from, int to, int[] temp) {

        if (to <= from + 1) {
            return 0;
        }

        int mid = from + ((to - from) >> 1);

        int first = countInversions(array, from, mid, temp);
        int second = countInversions(array, mid, to, temp);
        int split = countSplit(array, from, mid, to, temp);

        return first + split + second;
    }

    private static int countSplit(int[] array, int from, int mid, int to, int[] temp) {

        int i = from;
        int j = mid;
        int res = 0;

        while (i < mid && j < to) {
            if (array[i] < array[j]) {
                temp[i + j - from - mid] = array[i++];
            } else {
                temp[i + j - from - mid] = array[j++];
                res += mid - i;
            }
        }

        while (i < mid) {
            temp[i + j - from - mid] = array[i++];
        }

        while (j < to) {
            temp[i + j - from - mid] = array[j++];
        }

        for (i = from; i < to; i++) {
            array[i] = temp[i - from];
        }

        return res;
    }

    public static void main(final String[] arg) {

        Scanner scanner = new Scanner(System.in);

        int N = scanner.nextInt();

        int[] array = new int[N];
        int[] temp = new int[N];

        for (int i = 0; i < N; i++) {
            array[i] = scanner.nextInt();
        }

        int output = countInversions(array, 0, N, temp);
        System.out.println(output);
    }
}
