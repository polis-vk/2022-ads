import java.io.*;
import java.util.StringTokenizer;

public class MouseAndSeeds {
    private static String getExcellentPath(int[][] resultsMoving, int column, int rows) {
        StringBuilder resultPath = new StringBuilder();
        int i = column - 1;
        int j = rows - 1;

        while (j > 0 && i > 0) {
            if (resultsMoving[i - 1][j] > resultsMoving[i][j - 1]) {
                i--;
                resultPath.append('F');
            } else {
                j--;
                resultPath.append('R');
            }
        }

        while (j > 0) {
            j--;
            resultPath.append('R');
        }

        while (i > 0) {
            i--;
            resultPath.append('F');
        }

        return resultPath.reverse().toString();
    }

    private static void solve(final FastScanner in, final PrintWriter out) {
        int column = in.nextInt();
        int rows = in.nextInt();
        int[][] gameBoard = new int[column][rows];

        for (int i = column - 1; i >= 0; i--) {
            for (int j = 0; j < rows; j++) {
                gameBoard[i][j] = in.nextInt();
            }
        }

        for (int i = 1; i < column; i++) {
            gameBoard[i][0] = gameBoard[i][0] + gameBoard[i - 1][0];
        }
        for (int j = 1; j < rows; j++) {
            gameBoard[0][j] = gameBoard[0][j] + gameBoard[0][j - 1];
        }
        for (int i = 1; i < column; i++) {
            for (int j = 1; j < rows; j++) {
                gameBoard[i][j] = Integer.max(gameBoard[i - 1][j], gameBoard[i][j - 1]) + gameBoard[i][j];
            }
        }

        out.println(getExcellentPath(gameBoard, column, rows));
    }

    private static final class FastScanner {
        private final BufferedReader reader;
        private StringTokenizer tokenizer;

        FastScanner(final InputStream in) {
            reader = new BufferedReader(new InputStreamReader(in));
        }

        String next() {
            while (tokenizer == null || !tokenizer.hasMoreTokens()) {
                try {
                    tokenizer = new StringTokenizer(reader.readLine());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return tokenizer.nextToken();
        }

        int nextInt() {
            return Integer.parseInt(next());
        }
    }

    public static void main(final String[] arg) {
        final FastScanner in = new FastScanner(System.in);
        try (PrintWriter out = new PrintWriter(System.out)) {
            solve(in, out);
        }
    }
}