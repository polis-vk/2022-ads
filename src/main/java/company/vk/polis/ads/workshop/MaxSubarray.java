package company.vk.polis.ads.workshop;

public final class MaxSubarray {
    public record Res(int left, int right, int sum) {
    }

    public static Res maxSubarray(int[] array) {
        return maxSubarray(array, 0, array.length);
    }

    private static Res maxSubarray(int[] array, int l, int r) {
        int minSumIndex = -1;
        int minSum = 0;

        int maxSum = Integer.MIN_VALUE;
        int maxL = -1;
        int maxR = -1;

        int sum = 0;
        int currentSum;

        for (int i = l; i < r; i++) {
            sum += array[i];

            currentSum = sum - minSum;

            if (currentSum > maxSum) {
                maxSum = currentSum;
                maxL = minSumIndex + 1;
                maxR = i;
            }

            if (sum < minSum) {
                minSum = sum;
                minSumIndex = i;
            }
        }

        return new Res(maxL, maxR + 1, maxSum);
    }
}
