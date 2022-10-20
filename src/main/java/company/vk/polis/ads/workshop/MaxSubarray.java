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
        Res left = maxSubarray(array, l, mid);
        Res right = maxSubarray(array, mid, r);
        Res max = (left.sum > right.sum) ? left : right;
        if (left.right == right.left) {
            if (left.sum + right.sum > max.sum) {
                max = new Res(left.left, right.right, left.sum + right.sum);
            }
        }
        return max;
    }
}
