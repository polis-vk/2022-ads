import java.util.Scanner;

public class Stairs {
    private static int getMax(int[] values, int currentIndex, int maxStep){
        int maxValue = Integer.MIN_VALUE;
        for (int i = 1; i <= maxStep; i++){
            if (currentIndex - i < 0) {
                break;
            }
            maxValue = Math.max(values[currentIndex - i], maxValue);
        }
        return maxValue;
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int size = n + 2;
        int[] stairsValues = new int[size];
        int[] maxValuesForStairs = new int[size];
        stairsValues[0] = 0;
        stairsValues[n + 1] = 0;
        for (int i = 1; i < size - 1; i++){
            stairsValues[i] = in.nextInt();
        }
        int k = in.nextInt();
        for (int i = 1; i < size; i++){
            maxValuesForStairs[i] = getMax(maxValuesForStairs, i, k) + stairsValues[i];
        }
        System.out.println(maxValuesForStairs[n + 1]);
    }
}