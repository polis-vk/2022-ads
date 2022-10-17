//https://www.eolymp.com/ru/submissions/11792214

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.stream.IntStream;

public class BracketSequence {
    private static int[][] d;

    public static void main(String[] args) {
        try (BufferedReader in = new BufferedReader(new InputStreamReader(System.in))) {
            String s = in.readLine();
            if (s == null || s.isEmpty()) {
                System.out.println();
            } else {
                fillDynamicArray(s);
                System.out.println(correctBracketSequence(s, 0, d.length - 1));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void fillDynamicArray(String s) {
        int n = s.length();
        d = new int[n][n];
        IntStream.range(0, n).forEach(i -> d[i][i] = 1);
        for (int i = 1; i < n; i++) {
            int left = 0;
            int right = i;
            while (right < n) {
                d[left][right] = Integer.MAX_VALUE;
                if (isCorrect(s, left, right)) {
                    d[left][right] = d[left + 1][right - 1];
                }
                for (int k = left; k < right; k++) {
                    if (d[left][right] > d[left][k] + d[k + 1][right]) {
                        d[left][right] = d[left][k] + d[k + 1][right];
                    }
                }
                left++;
                right++;
            }
        }
    }

    private static boolean isCorrect(String s, int left, int right) {
        return (s.charAt(left) == '(' && s.charAt(right) == ')')
                || (s.charAt(left) == '[' && s.charAt(right) == ']');
    }

    private static String correctBracketSequence(String s, int l, int r) {
        if (l == r) {
            if (s.charAt(l) == '(' || s.charAt(l) == ')') {
                return "()";
            }
            if (s.charAt(l) == '[' || s.charAt(l) == ']') {
                return "[]";
            }
        }
        if (s.charAt(l) == '(' && s.charAt(r) == ')' && d[l][r] == d[l + 1][r - 1]) {
            return "(" + correctBracketSequence(s, ++l, --r) + ")";
        }
        if (s.charAt(l) == '[' && s.charAt(r) == ']' && d[l][r] == d[l + 1][r - 1]) {
            return "[" + correctBracketSequence(s, ++l, --r) + "]";
        }
        for (int k = l; k < r; k++) {
            if (d[l][k] + d[k + 1][r] == d[l][r]) {
                return correctBracketSequence(s, l, k) + correctBracketSequence(s, ++k, r);
            }
        }
        return "";
    }
}