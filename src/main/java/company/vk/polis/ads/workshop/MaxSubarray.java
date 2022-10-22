package company.vk.polis.ads.workshop;

public final class MaxSubarray {
    public record Res(int left, int right, int sum) {
    }

    public static Res maxSubarray(int[] array) {
        return maxSubarray(array, 0, array.length);
    }

    private static Res maxSubarray(int[] array, int l, int r) {
        if (l == r - 1){
            return new Res(l, r, array[l]);
        }
        if (l == r) {
            return new Res(l, l, 0);
        }


        int mid = (l + r) >>> 1;
        Res leftPart = maxSubarray(array, l, mid);
        Res rightPart = maxSubarray(array, mid, r);
        Res cross = maxSubarrayCross(array, l, mid, r);

        if (cross.sum > leftPart.sum) {
            if (cross.sum > rightPart.sum) {
                return cross;
            } else {
                return rightPart;
            }
        } else {
            if (leftPart.sum > rightPart.sum) {
                return leftPart;
            } else {
                return rightPart;
            }
        }

    }

    private static Res maxSubarrayCross(int[] array, int l, int m, int r) {
        int sum = 0;
        int leftMaxSum = Integer.MIN_VALUE;
        int leftPos = 0;
        for (int i = m - 1; i >= l; i--){
           sum += array[i];
           if (sum >= leftMaxSum) {
               leftMaxSum = sum;
               leftPos = i;
           }
        }
        sum = 0;
        int rightMaxSum = Integer.MIN_VALUE;
        int rightPos = 0;
        for (int i = m; i <  r; i++){
            sum += array[i];
            if (sum >= rightMaxSum) {
                rightMaxSum = sum;
                rightPos = i;
            }
        }
        return new Res(leftPos, rightPos+1, leftMaxSum+rightMaxSum);
    }
}
