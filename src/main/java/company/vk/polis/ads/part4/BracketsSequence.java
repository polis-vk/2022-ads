package company.vk.polis.ads.part4;

import java.util.Scanner;


//https://www.eolymp.com/ru/submissions/11808758
public class BracketsSequence {
    private static int[][] leastNumOfBrackets;
    private static int[][] seqRecovery;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        char[] charArr = sc.nextLine().toCharArray();
        int length = charArr.length;
        if (length == 0) {
            return;
        }

        leastNumOfBrackets = new int[length][length];
        seqRecovery = new int[length][length];

        for (int i = 0; i < length; i++) {
            for (int j = 0; j < length; j++) {
                seqRecovery[i][j] = Integer.MIN_VALUE;
                if (i > j) {
                    leastNumOfBrackets[i][j] = 0;
                } else if (i == j) {
                    leastNumOfBrackets[i][j] = 1;
                } else {
                    leastNumOfBrackets[i][j] = Integer.MAX_VALUE;
                }
            }
        }
        minSequence(charArr, 0, length - 1);
        displaySeq(charArr, 0, length - 1);
    }

    private static void displaySeq(char[] arrBrackets, int left, int right) {
        if (left > right) {
            return;
        }
        if (left == right) {
            if (arrBrackets[left] == '(' || arrBrackets[left] == ')') {
                System.out.print("()");
            } else if (arrBrackets[left] == '[' || arrBrackets[left] == ']') {
                System.out.print("[]");
            } else {
                System.out.print(arrBrackets[left]);
            }
        } else if (seqRecovery[left][right] == Integer.MIN_VALUE) {
            System.out.print(arrBrackets[left]);
            displaySeq(arrBrackets, left + 1, right - 1);
            System.out.print(arrBrackets[right]);
        } else {
            displaySeq(arrBrackets, left, seqRecovery[left][right]);
            displaySeq(arrBrackets, seqRecovery[left][right] + 1, right);
        }
    }

    private static int minSequence(char[] arrayBrackets, int left, int right) {
        if (leastNumOfBrackets[left][right] != Integer.MAX_VALUE) {
            return leastNumOfBrackets[left][right];
        }
        if ((arrayBrackets[left] == '(' && arrayBrackets[right] == ')') ||
                (arrayBrackets[left] == '[' && arrayBrackets[right] == ']')) {
            leastNumOfBrackets[left][right] = Math.min(leastNumOfBrackets[left][right],
                    minSequence(arrayBrackets, left + 1, right - 1));
        }

        for (int i = left; i < right; i++) {
            int res = minSequence(arrayBrackets, left, i) + minSequence(arrayBrackets, i + 1, right);
            if (res < leastNumOfBrackets[left][right]) {
                leastNumOfBrackets[left][right] = res;
                seqRecovery[left][right] = i;
            }
        }
        return leastNumOfBrackets[left][right];
    }
}