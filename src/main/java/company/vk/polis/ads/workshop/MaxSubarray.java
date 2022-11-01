package company.vk.polis.ads.workshop;

public final class MaxSubarray {
    public record Res(int left, int right, int sum) {
    }

    public static Res maxSubarray(int[] array) {
        return maxSubarray(array, 0, array.length);
    }

    private static Res maxSubarray(int[] array, int l, int r) {
        if(l == r - 1){
            return new Res(l, r, array[l]);
        }
        int mid = (l + r) / 2;
        var left = maxSubarray(array, l, mid);
        var right = maxSubarray(array, mid, r);
        var intersect = intersectSubArray(array, l, r, mid);
        if(left.sum >= right.sum && left.sum >= intersect.sum){
            return left;
        }else if(right.sum >= left.sum && right.sum >= intersect.sum){
            return right;
        }else{
            return intersect;
        }
    }

    private static Res intersectSubArray(int[] array, int l, int r, int m){
        int left = Integer.MIN_VALUE;
        int posl = 0;
        int sum = 0;

        for (int i = m - 1; i >= l ; i--) {
            sum += array[i];
            if(sum > left){
                left = sum;
                posl = i;
            }
        }
        sum = 0;
        int right = Integer.MIN_VALUE;
        int posr = 0;
        for (int i = m; i < r; i++) {
            sum += array[i];
            if(sum > right){
                right = sum;
                posr = i;
            }
        }
        return new Res(posl, posr, left + right);
    }
}
