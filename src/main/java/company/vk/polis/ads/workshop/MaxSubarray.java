package company.vk.polis.ads.workshop;

public final class MaxSubarray {
    public record Res(int left, int right, int sum) {}

    public static Res maxSubarray(int[] array) {
        return maxSubarray(array, 0, array.length);
    }

    private static Res maxSubarray(int[] array, int l, int r) {
        if (l == r - 1){
            return new Res(l, r, array[l]);
        }
        int mid = (l + r) >>> 1;
        var leftPart = maxSubarray(array, l, mid);
        var rightPart = maxSubarray(array, mid, r);
        var crossPart = maxSubarray(array, l, mid, r);
        if (leftPart.sum >= rightPart.sum && leftPart.sum >= crossPart.sum){
            return leftPart;
        }
        else if (rightPart.sum >= leftPart.sum && rightPart.sum >= crossPart.sum){
            return rightPart;
        }
        return crossPart;
    }

    private static Res maxSubarray(int[] array, int l, int m, int r) {
        int maxLeftSum = Integer.MIN_VALUE;
        int leftPosition = 0;
        int currentSum = 0;
        for (int i = m - 1; i >= l; i--){
            currentSum += array[i];
            if (currentSum >= maxLeftSum){
                maxLeftSum = currentSum;
                leftPosition = i;
            }
        }
        int rightPosition = 0;
        int maxRightSum = Integer.MIN_VALUE;
        currentSum = 0;
        for (int i = m; i < r; i++){
            currentSum += array[i];
            if (currentSum >= maxRightSum){
                maxRightSum = currentSum;
                rightPosition = i;
            }
        }
        return new Res(leftPosition, rightPosition, maxLeftSum + maxRightSum);
    }
}
