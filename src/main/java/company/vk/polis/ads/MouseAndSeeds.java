package company.vk.polis.ads;

import java.util.Scanner;

public class MouseAndSeeds {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        final int m = in.nextInt();
        final int n = in.nextInt();
        int[][] temple = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                temple[i][j] = in.nextInt();
            }
        }
        String[][] maxWays = new String[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                maxWays[i][j] = "";
            }
        }
        for (int i = m - 2; i >= 0; i--) {
            maxWays[i][0] = maxWays[i + 1][0] + "F";
            temple[i][0] += temple[i + 1][0];
        }
        for (int j = 1; j < n; j++) {
            maxWays[m - 1][j] = maxWays[m - 1][j - 1] + "R";
            temple[m - 1][j] += temple[m - 1][j - 1];
        }

        for (int i = m - 2; i >= 0; i--) {
            for (int j = 1; j < n; j++) {
                if (temple[i + 1][j] > temple[i][j - 1]) {
                    temple[i][j] += temple[i + 1][j];
                    maxWays[i][j] = maxWays[i + 1][j] + "F";
                } else {
                    temple[i][j] += temple[i][j - 1];
                    maxWays[i][j] = maxWays[i][j - 1] + "R";
                }
            }
        }
        System.out.println(maxWays[0][n - 1]);
    }
}
