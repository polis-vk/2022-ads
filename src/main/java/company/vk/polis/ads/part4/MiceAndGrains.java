package company.vk.polis.ads.part4;

import java.util.Scanner;

//https://www.eolymp.com/ru/submissions/11826006
public class MiceAndGrains {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int m = sc.nextInt();
        int n = sc.nextInt();
        int[][] board = new int[m + 1][n + 1];
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                board[m - i + 1][j] = sc.nextInt();
            }
        }
        boolean[] result = maxGrains(board, m + 1, n + 1);
        for (int i = 0; i < m + n - 2; i++) {
            if (!result[i]) {
                System.out.print('F');
            } else {
                System.out.println('R');
            }
        }
    }

    private static boolean[] maxGrains(int[][] board, int m, int n) {
        boolean[] steps = new boolean[m + n - 2];
        int step = 0;
        int max = Integer.MIN_VALUE;
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                board[i][j] = Math.max(board[i - 1][j], board[i][j - 1]) + board[i][j];
                steps[step] = board[i - 1][j] <= board[i][j - 1];
            }
        }
        return steps;
    }
}
