package company.vk.polis.ads.part4;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Problem solution template.
 *
 * @author Dmitry Schitinin
 *
 * https://www.eolymp.com/ru/submissions/11837504
 */
public final class Brackets {
    private Brackets() {
        // Should not be instantiated
    }

    private static boolean assertBracketPair(int i, int j, StringBuilder str) {
        return (str.charAt(i) == '(' && str.charAt(j) == ')' || str.charAt(i) == '[' && str.charAt(j) == ']');
    }

    private static void restore(int i, int j, StringBuilder str, int[][] arr, ArrayList<Integer> posList) {
        if (i == j) {
            posList.add(i + posList.size());
        } else if (j - i > 1 || !assertBracketPair(i, j, str)) {
            int min = Integer.MAX_VALUE;
            int minIndex = j;
            for (int index = i; index < j; index++) {
                int curr = arr[i][index] + arr[index + 1][j];
                if (curr < min) {
                    min = curr;
                    minIndex = index;
                }
            }
            if (assertBracketPair(i, j, str) && arr[i][minIndex] + arr[minIndex + 1][j] > 0) {
                restore(i + 1, j - 1, str, arr, posList);
            } else {
                restore(i, minIndex, str, arr, posList);
                restore(minIndex + 1, j, str, arr, posList);
            }
        }
    }

    private static void solve(final PrintWriter out) throws IOException {
        Scanner scanner = new Scanner(System.in);
        String inputStr = scanner.nextLine();
        if (inputStr.equals("")) {
            return;
        }

        StringBuilder sb = new StringBuilder(inputStr);
        int[][] arr = new int[sb.length()][sb.length()];
        for (int i = 0; i < sb.length(); i++) { // инициализация диагонали матрицы
            arr[i][i] = 1;
        }

        int i = 0;
        int j = 1;
        int prevJ = 1;
        while (j < sb.length()) { // построение матрицы решений
            int min = Integer.MAX_VALUE;
            int minIndex = 0;
            for (int index = i; index < j; index++) {
                int curr = arr[i][index] + arr[index + 1][j];
                if (curr < min) {
                    min = curr;
                    minIndex = index;
                }
            }
            if (assertBracketPair(i, j, sb) && arr[i][minIndex] + arr[minIndex + 1][j] > 0) {
                if (j - i > 1) {
                    arr[i][j] = arr[i + 1][j - 1];
                }
            } else {
                arr[i][j] = min;
            }

            if (j == sb.length() - 1) { // изменение текущих индексов
                i = 0;
                j = prevJ + 1;
                prevJ++;
            } else {
                i++;
                j++;
            }
        }

        ArrayList<Integer> posList = new ArrayList<>();
        restore(0, sb.length() - 1, sb, arr, posList); // восстановление позиции скобок
        for (Integer pos : posList) {
            switch (sb.charAt(pos)) {
                case ('(') -> sb.insert(pos + 1, ')');
                case ('[') -> sb.insert(pos + 1, ']');
                case (')') -> sb.insert(pos, "(");
                case (']') -> sb.insert(pos, "[");
            }
        }

        out.println(sb);
    }

    public static void main(final String[] arg) {
        try (PrintWriter out = new PrintWriter(System.out)) {
            solve(out);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
