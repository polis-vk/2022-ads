import java.util.Arrays;
import java.util.Scanner;
// https://www.eolymp.com/ru/submissions/11787329
public final class Brackets {

    private static boolean checkPair(char l, char r) {
        return l == '(' && r == ')' || l == '[' && r == ']';
    }

    private static String getValidString(String str, int[][] p, int l, int r) {
        StringBuilder res = new StringBuilder("");
        if (l > r) {
            return res.toString();
        }
        if (l == r) {
            if (str.charAt(l) == '(' || str.charAt(l) == ')') {
                res.append("()");
            } else {
                res.append("[]");
            }
        } else if (p[l][r] == -1) {
            res.append(str.charAt(l));
            res.append(getValidString(str, p,l + 1, r - 1));
            res.append(str.charAt(r));
        } else {
            res.append(getValidString(str, p, l, p[l][r]));
            res.append(getValidString(str, p,p[l][r] + 1, r));
        }
        return res.toString();
    }

    public static void main(final String[] arg) {
        Scanner sc = new Scanner(System.in);
        String str = sc.nextLine();
        if (str == null || str.length() == 0) {
            System.out.println("");
            return;
        }
        int n = str.length();
        int[][] d = new int[n][n];
        int[][] p = new int[110][110];
        for (int i = 0; i < n; i++) {
            d[i][i] = 1;
        }
        for (int[] row : p) {
            Arrays.fill(row, -1);
        }

        for (int col = 1; col < n; col++) {
            int curCol = col;
            int row = 0;
            while (curCol < n) {
                int answer = Integer.MAX_VALUE;
                if (checkPair(str.charAt(row), str.charAt(curCol))) {
                    d[row][curCol] = d[row + 1][curCol - 1];
                    answer = d[row][curCol];
                }
                for (int i = row; i < curCol; i++) {
                    if (answer >  d[row][i] + d[i + 1][curCol]) {
                        answer = d[row][i] + d[i + 1][curCol];
                        p[row][curCol] = i;
                    }
                }
                d[row][curCol] = answer;
                row++;
                curCol++;
            }
        }
        System.out.println(getValidString(str, p, 0, n - 1));
    }
}