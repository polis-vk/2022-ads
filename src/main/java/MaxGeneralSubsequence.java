public class MaxGeneralSubsequence {
    private static void getMaxLength(int[] firstSequence, int[] secondSequence) {
        int firstCount = firstSequence.length;
        int secondCount = secondSequence.length;
        int[][] d = new int[firstCount + 1][secondCount + 1];

        for (int i = 1; i < firstCount + 1; i++) {
            for (int j = 1; j < secondCount + 1; j++) {
                if (firstSequence[i - 1] == secondSequence[j - 1]) {
                    d[i][j] = d[i - 1][j - 1] + 1;
                    continue;
                }
                d[i][j] = Math.max(d[i - 1][j], d[i][j - 1]);
            }
        }
        System.out.println(d[firstCount][secondCount]);
    }
}
