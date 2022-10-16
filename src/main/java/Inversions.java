import java.util.Scanner;

public class Inversions {
    private static int countInv(int[] data, int startIndex, int endIndex) {
        if (startIndex >= endIndex - 1){
            return 0;
        }
        int midIndex = startIndex + ((endIndex - startIndex) >> 1);
        return countInv(data, startIndex, midIndex) +
                countInv(data, midIndex, endIndex) +
                countSplitInv(data, startIndex, midIndex, endIndex);
    }

    private static int countSplitInv(int[] data, int startIndex, int midIndex ,int endIndex) {
        int sizeLeft = midIndex - startIndex;
        int sizeRight = endIndex - midIndex;
        int[] leftData = new int[sizeLeft];
        int[] rightData = new int[sizeRight];
        System.arraycopy(data, startIndex, leftData, 0, sizeLeft);
        System.arraycopy(data, midIndex, rightData, 0, sizeRight);

        int result = 0;
        int i = 0;
        int j = 0;
        int k = startIndex;
        while (i < sizeLeft && j < sizeRight) {
            if (leftData[i] < rightData[j]) {
                data[k] = leftData[i];
                i++;
            }
            else {
                result += midIndex - i - startIndex;
                data[k] = rightData[j];
                j++;
            }
            k++;
        }
        while (i < sizeLeft) {
            data[k] = leftData[i];
            i++;
            k++;
        }
        while (j < sizeRight) {
            data[k] = rightData[j];
            j++;
            k++;
        }
        return result;
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int[] inputData = new int[n];
        for (int i = 0; i < n; i++) {
            inputData[i] = in.nextInt();
        }
        System.out.println(countInv(inputData, 0, n));
    }
}