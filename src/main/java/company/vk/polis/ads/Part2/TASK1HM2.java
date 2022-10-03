import java.util.Scanner;

public class TASK1HM2 {
    static int maxValue(int[] array){
        int max = Integer.MIN_VALUE;
        for (int j : array) {
            max = Math.max(max, j);
        }
        return max;
    }
    static int minValue(int[] array){
        int min = Integer.MAX_VALUE;
        for (int j : array) {
            min = Math.min(min, j);
        }
        return min;
    }

    static void sort(int[] array){
        int max = maxValue(array);
        int min = minValue(array);
        int lengthCount = max - min + 1;
        int[] count = new int[lengthCount];
        int out[] = new int[array.length];
        for (int j : array) {
            count[j - min]++;
        }
        for(int i = 1; i < count.length; i++){
            count[i] += count[i-1];
        }
        for(int i = array.length - 1; i >= 0; i--){
            out[count[array[i] - min] - 1] = array[i];
            count[array[i] - min]--;
        }
        for(int i = 0; i < array.length; i++){
            array[i] = out[i];
        }
    }
    static void printArray(int[] array){
        System.out.println();
        for (int i : array) {
            System.out.print(i + " ");
        }
    }
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int[] mas = new int[n];
        for(int i = 0; i < mas.length; i++){
            mas[i] = in.nextInt();
        }
        sort(mas);
        printArray(mas);
    }
}
