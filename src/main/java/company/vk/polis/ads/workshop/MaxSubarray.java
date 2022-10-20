package company.vk.polis.ads.workshop;

public final class MaxSubarray {
    public record Res(int left, int right, int sum) {
    }

    public static Res maxSubarray(int[] array) {
        return maxSubarray(array, 0, array.length);
    }

    private static Res maxSubarray(int[] array, int l, int r) {
        if (l + 1 == r) {
            return new Res(l, r, array[l]);
        }
        int mid = (l + r) >> 1;
        var left = maxSubarray(array, l, mid);
        var right = maxSubarray(array, mid, r);
        var cross = maxSubarrayCross(array, l, mid, r);
        if (left.sum >= right.sum && left.sum >= cross.sum) {
            return left;
        } else if (right.sum >= left.sum && right.sum >= cross.sum) {
            return right;
        } else {
            return cross;
        }
    }

    private static Res maxSubarrayCross(int [] array,int l, int w, int r) {
        int sum = 0;
        int leftMaxSum = Integer.MIN_VALUE;
        int leftPosition = 0;
        for(int i = w - 1; i >= l; i--) {
            sum += array[i];
            if (sum > leftMaxSum) {
                leftMaxSum = sum;
                leftPosition = i;
            }
        }

        sum = 0;
        int rightMaxSum = Integer.MIN_VALUE;
        int rightPosition = 0;
        for(int i = w; i < r; i++) {
            sum += array[i];
            if (sum > rightMaxSum) {
                rightMaxSum = sum;
                rightPosition = i;
            }
        }

        return new Res(leftPosition, rightPosition, leftMaxSum + rightMaxSum);
    }
}

