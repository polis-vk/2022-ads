public class BracketSequence {
    private String createRightSequence(int[][] d, String sequence, int l, int r) {
        if (l > r) return "";
        if (l == r) {
            if (sequence.charAt(l) == '(' || sequence.charAt(l) == ')') {
                return "()";
            }
            if (sequence.charAt(l) == '[' || sequence.charAt(l) == ']') {
                return "[]";
            }
        }
        int border = (isMatchBrackets(sequence.charAt(l), sequence.charAt(r)) ? d[l + 1][r - 1] : Integer.MAX_VALUE);
        if (border == d[l][r]) {
            return sequence.charAt(l) + createRightSequence(d, sequence, l + 1, r - 1) + sequence.charAt(r);
        }
        for (int i = l; i < r; i++) {
            if (d[l][i] + d[i + 1][r] == d[l][r]) {
                return createRightSequence(d, sequence, l, i) + createRightSequence(d, sequence, i + 1, r);
            }
        }
        return "";
    }

    private boolean isMatchBrackets(char c1, char c2) {
        if (c1 == '(' && c2 == ')') {
            return true;
        }
        return c1 == '[' && c2 == ']';
    }

    private void solve(String sequence) {
        int size = sequence.length();
        if (size == 0) {
            return;
        }
        int[][] d = new int[size][size];

        for (int i = 0; i < size; i++) {
            d[i][i] = 1;
        }
        for (int i = 0; i < size; i++) {
            int row = 0;
            int col = i + 1;

            while (col < size) {
                int min = Integer.MAX_VALUE;

                if (isMatchBrackets(sequence.charAt(row), sequence.charAt(col))) {
                    min = d[row + 1][col - 1];
                }
                for (int k = row; k < col; k++) {
                    min = Math.min(d[row][k] + d[k + 1][col], min);
                }
                d[row][col] = min;
                row++;
                col++;
            }
        }

        System.out.println(createRightSequence(d, sequence, 0, size - 1));
    }
}
