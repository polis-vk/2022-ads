package company.vk.polis.ads;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;
import java.util.Scanner;

/**
 * Problem solution template.
 *
 * @author Dmitry Schitinin
 */
public final class Task1 {
    private Task1() {
        // Should not be instantiated
    }

    public static void main(final String[] arg) {

        Scanner scanner = new Scanner(System.in);

        String input = scanner.nextLine();

        if (input.isEmpty()) {
            System.out.println("");
            return;
        }

        int N = input.length();
        String[][] seq = new String[N][N];

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {

                if (i != j) {
                    seq[i][j] = "";
                } else {
                    char sym = input.charAt(i);
                    if (sym == '[' || sym == ']') {
                        seq[i][j] = "[]";
                    } else if (sym == '(' || sym == ')') {
                        seq[i][j] = "()";
                    }
                }

            }
        }

        for (int i = 1; i < N; i++) {
            for (int j = 0; j < N - i; j++) {

                if ( (input.charAt(j) == '[' && input.charAt(j + i) == ']')
                    || (input.charAt(j) == '(' && input.charAt(j + i) == ')')
                ) {
                    seq[j][j + i] = input.charAt(j) +
                            seq[j + 1][j + i - 1] + input.charAt(j + i);
                } else {
                    seq[j][j + i] = seq[j][j] + seq[j + 1][j + i];
                }

                for (int k = j; k < j + i; k++) {
                    String temp = seq[j][k] + seq[k + 1][j + i];

                    if (temp.length() < seq[j][j + i].length()) {
                        seq[j][j + i] = temp;
                    }
                }
            }
        }

        String output = seq[0][N - 1];
        System.out.println(output);

    }
}
