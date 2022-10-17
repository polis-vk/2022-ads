import java.util.Scanner;

public class Inversion_E_Olimp{
    public static int[] mergeArrayAndCountInverse(int[] array) {
        countOfInverses = 0;
        return sort(array,0,array.length - 1);
    }

    static int[] sort(int[] array, int low, int high) {
        if (low > high - 1) {
            return new int[] { array[low] };
        }
        int mid = low + (high - low) / 2;

        return mergeHalfs(sort(array,low,mid), sort(array,mid + 1, high));
    }

    static int[] mergeHalfs(int[] array1, int[] array2) {
        int pointer1 = 0;
        int pointer2 = 0;
        int[] finalMergeArray = new int[array1.length + array2.length];
        int pointerOnMergeArray = 0;

        while (pointer1 < array1.length && pointer2 < array2.length) {

            if (array1[pointer1] <= array2[pointer2]) {
                finalMergeArray[pointerOnMergeArray++] = array1[pointer1++];
            } else {
                finalMergeArray[pointerOnMergeArray++] = array2[pointer2++];
                countOfInverses += (array1.length - pointer1);
            }
        }

        while (pointer1 < array1.length) {
            finalMergeArray[pointerOnMergeArray++] = array1[pointer1++];
        }

        while (pointer2 < array2.length) {
            finalMergeArray[pointerOnMergeArray++] = array2[pointer2++];
        }

        return finalMergeArray;
    }

    private static long countOfInverses;

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int[] mas = new int[n];
        for (int i = 0; i < n; i++) {
            mas[i] = in.nextInt();
        }
        mergeArrayAndCountInverse(mas);
        System.out.println(countOfInverses);
    }
}
