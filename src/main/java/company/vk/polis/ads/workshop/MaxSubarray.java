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
        int m = (l + r) / 2;
        var left = maxSubarray(array, l, m);
        var right = maxSubarray(array, m, r);
        var cross = maxSubarrayCross(array, l, m, r);
        if (left.sum() >= right.sum && left.sum() >= cross.sum()) {
            return left;
        } else if (right.sum() >= left.sum() && right.sum() >= cross.sum()) {
            return right;
        }
        return cross;
    }

    private static Res maxSubarrayCross(int[] array, int fromInclusive, int m, int toExclusive) {
        int sum = 0;
        int left = m;
        int leftSum = Integer.MIN_VALUE;
        for (int i = m - 1; i >= fromInclusive; i--) {
            sum += array[i];
            if (sum >= leftSum) {
                leftSum = sum;
                left = i;
            }
        }

        sum = 0;
        int right = m - 1;
        int rightSum = Integer.MIN_VALUE;
        for (int i = m; i < toExclusive; i++) {
            sum += array[i];
            if (sum > rightSum) {
                rightSum = sum;
                right = i;
            }
        }
        return new Res(left, right, leftSum + rightSum);
    }
}
