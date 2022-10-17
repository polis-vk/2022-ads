//https://www.eolymp.com/ru/submissions/11825120

import java.util.Scanner;

public class MouseAndSeeds {
    public static void main(String[] args) {
        try (Scanner in = new Scanner(System.in)) {
            int m = in.nextInt();
            int n = in.nextInt();
            int[][] grains = new int[m][n];
            for (int i = m - 1; i >= 0; i--) {
                for (int j = 0; j < n; j++) {
                    grains[i][j] = in.nextInt();
                }
            }
            for (int i = 1; i < m; i++) {
                grains[i][0] += grains[i - 1][0];
            }
            for (int j = 1; j < n; j++) {
                grains[0][j] += grains[0][j - 1];
            }
            for (int i = 1; i < m; i++) {
                for (int j = 1; j < n; j++) {
                    grains[i][j] += Math.max(grains[i - 1][j], grains[i][j - 1]);
                }
            }
            StringBuilder stringBuilder = new StringBuilder();
            int i = m - 1;
            int j = n - 1;
            while (i > 0 && j > 0) {
                if (grains[i][j - 1] < grains[i - 1][j]) {
                    stringBuilder.append("F");
                    i--;
                } else {
                    stringBuilder.append("R");
                    j--;
                }
            }
            while (i > 0) {
                stringBuilder.append("F");
                i--;
            }
            while (j > 0) {
                stringBuilder.append("R");
                j--;
            }
            System.out.println(stringBuilder.reverse());
        }
    }
}