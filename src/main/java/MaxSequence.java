import java.util.Scanner;

public class MaxSequence {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int[] firstSequence = new int[n];
        for (int i = 0; i < n; i++){
            firstSequence[i] = in.nextInt();
        }
        int m = in.nextInt();
        int[] secondSequence = new int[m];
        for (int i = 0; i < m; i++){
            secondSequence[i] = in.nextInt();
        }
        int[][] maxSequence = new int[m][n];
        for (int i = 0; i < m; i++){
            for (int j = 0; j < n; j++){
                if (firstSequence[j] == secondSequence[i]){
                    if (i < 1 || j < 1){
                        maxSequence[i][j] = 1;
                    }
                    else {
                        maxSequence[i][j] = 1 + maxSequence[i-1][j-1];
                    }
                }
                else {
                    if (i >= 1 && j >= 1){
                        maxSequence[i][j] = Math.max(maxSequence[i][j-1], maxSequence[i-1][j]);
                    }
                    else if (i < 1 && j < 1){
                        maxSequence[i][j] = 0;
                    }
                    else if (i < 1){
                        maxSequence[i][j] = maxSequence[i][j - 1];
                    }
                    else {
                        maxSequence[i][j] = maxSequence[i - 1][j];
                    }
                }
            }
        }
        System.out.println(maxSequence[m-1][n-1]);
    }
}