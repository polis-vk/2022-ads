package company.vk.polis.ads;

import java.util.Scanner;

public class InversionsCounter {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        final int n = in.nextInt();
        int[] array = new int[n];
        for (int i = 0; i < n; i++) {
            array[i] = in.nextInt();
        }
        System.out.println(inversionsCount(array));
    }

    public static int inversionsCount(int[] array) {
        int[] tempArray = new int[array.length];
        for (int i = 0; i < array.length; i++) {
            tempArray[i] = array[i];
        }
        return inversionsCount(array, tempArray, 0, array.length - 1);
    }

    private static int inversionsCount(int[] array, int[] tempArray,
                                            int fromInclusive, int toInclusive) {
        if (fromInclusive >= toInclusive) {
            return 0;
        }
        int mid = (fromInclusive + toInclusive) / 2;
        int result = inversionsCount(array, tempArray, fromInclusive, mid) +
                inversionsCount(array, tempArray, mid + 1, toInclusive);
        int i = fromInclusive;
        int j = mid + 1;
        int t = fromInclusive;
        while (i <= mid && j <= toInclusive) {
            if (tempArray[j] < tempArray[i]) {
                result += mid - i + 1;
                array[t++] = tempArray[j++];
            } else {
                array[t++] = tempArray[i++];
            }
        }

        while (i <= mid) {
            array[t++] = tempArray[i++];
        }

        while (j <= toInclusive) {
            array[t++] = tempArray[j++];
        }

        for (int k = fromInclusive; k <= toInclusive; k++) {
            tempArray[k] = array[k];
        }
        return result;
    }
}
