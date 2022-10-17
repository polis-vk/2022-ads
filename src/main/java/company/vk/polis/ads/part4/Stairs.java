package company.vk.polis.ads.part4;

import java.util.Scanner;

//https://www.eolymp.com/ru/submissions/11820579
public class Stairs {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int numsCount = sc.nextInt();
        int[] stairs = new int[numsCount + 2];
        for (int i = 1; i <= numsCount; i++) {
            stairs[i] = sc.nextInt();
        }
        stairs[0] = 0;
        stairs[numsCount + 1] = 0;

        int step = sc.nextInt();

        int from = 0;
        for (int to = 1; to <= numsCount + 1; to++) {
            int currentStep = step;
            int max = stairs[from];
            int indexMax = from;
            while (currentStep > 0) {
                from = Math.max(to - currentStep, 0);
                if (from - indexMax < step) {
                    if (stairs[from] > max) {
                        max = stairs[from];
                        indexMax = from;
                    }
                } else {
                    int indexAvailableMax = findIndexMax(stairs, step, from + 1);
                    int availableMax = stairs[indexAvailableMax];
                    if (stairs[from] > availableMax) {
                        max = stairs[from];
                        indexMax = from;
                    } else {
                        max = availableMax;
                        indexMax = indexAvailableMax;
                    }
                }
                currentStep--;
            }
            stairs[to] += max;
        }
        System.out.println(stairs[numsCount + 1]);
    }

    private static int findIndexMax(int[] array, int step, int currIndex) {
        int max = Integer.MIN_VALUE;
        int indexMax = -1;
        for (int i = currIndex - step; i < currIndex; i++) {
            max = Math.max(max, array[i]);
            indexMax = i;
        }
        return indexMax;
    }
}