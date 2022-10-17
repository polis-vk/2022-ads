package company.vk.polis.ads.part4;

import java.util.Scanner;

//https://www.eolymp.com/ru/submissions/11822218
public class SubSequence {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int lengthFirstSeq = sc.nextInt();
        int[] firstSeq = new int[lengthFirstSeq + 1];
        for (int i = 1; i <= lengthFirstSeq; i++) {
            firstSeq[i] = sc.nextInt();
        }

        int lengthSecondSeq = sc.nextInt();
        int[] secondSeq = new int[lengthSecondSeq + 1];
        for (int i = 1; i <= lengthSecondSeq; i++) {
            secondSeq[i] = sc.nextInt();
        }

        int maxSubSequence = subsequence(firstSeq, lengthFirstSeq, secondSeq, lengthSecondSeq);
        System.out.println(maxSubSequence);
    }

    private static int subsequence(int[] firstSeq, int lengthFirstSeq, int[] secondSeq, int lengthSecondSeq) {
        int[][] subSequences = new int[lengthFirstSeq + 1][lengthSecondSeq + 1];
        for (int i = 1; i <= lengthFirstSeq; i++) {
            for (int j = 1; j <= lengthSecondSeq; j++) {
                if (firstSeq[i] == secondSeq[j]) {
                    subSequences[i][j] = 1 + subSequences[i - 1][j - 1];
                } else {
                    subSequences[i][j] = Math.max(subSequences[i - 1][j], subSequences[i][j - 1]);
                }
            }
        }
        return subSequences[lengthFirstSeq][lengthSecondSeq];
    }
}
