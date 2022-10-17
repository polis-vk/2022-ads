public class Stairs {
    private int getMax(int i, int[] d, int step) {
        int max = d[i - step];
        for (int j = i - step + 1; j < i; j++) {
            if (d[j] > max) {
                max = d[j];
            }
        }
        return max;
    }

    private void getMaxSum(int[] stairs, int step) {
        int count = stairs.length;
        int[] d = new int[count + 2 * step - 2];
        d[step - 1] = stairs[0];
        for (int i = step; i < d.length; i++) {
            d[i] = getMax(i, d, step);
            if (i - step + 1 < stairs.length) {
                d[i] += stairs[i - step + 1];
            }
        }
        System.out.println(d[d.length - 1]);
    }
}
