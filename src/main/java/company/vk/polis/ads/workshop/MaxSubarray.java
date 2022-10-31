package company.vk.polis.ads.workshop;

public final class MaxSubarray {
    public record Res(int left, int right, int sum) {
    }

    public static Res maxSubarray(int[] array) {
        return maxSubarray(array, 0, array.length);
    }

    private static Res maxSubarray(int[] array, int l, int r) {
        if (l == r - 1) {
            return new Res(l, r, array[l]);
        }
        int m = (l + r) >> 1;
        Res left = maxSubarray(array, l, m);
        Res right = maxSubarray(array, m, r);
        Res cross = maxSubarrayCross(array, l, m, r);
        if (left.sum >= right.sum && left.sum >= cross.sum) {
            return left;
        } else if (right.sum >= left.sum && right.sum >= cross.sum) {
            return right;
        } else {
            return cross;
        }
    }

    private static Res maxSubarrayCross(int[] array, int l, int m, int r) {
        int sum = 0;
        int leftMaxSum = Integer.MIN_VALUE;
        int leftPos = 0;
        for (int i = m - 1; i >= l; i--) {
            sum += array[i];
            if (sum >= leftMaxSum) {
                leftMaxSum = sum;
                leftPos = i;
            }
        }
        sum = 0;
        int rightMaxSum = Integer.MIN_VALUE;
        int rightPos = 0;
        for (int i = m; i < r; i++) {
            sum += array[i];
            if (sum >= rightMaxSum) {
                rightMaxSum = sum;
                rightPos = i;
            }
        }
        return new Res(leftPos, rightPos, leftMaxSum + rightMaxSum);
    }

}
