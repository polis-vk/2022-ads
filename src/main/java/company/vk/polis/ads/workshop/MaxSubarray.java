package company.vk.polis.ads.workshop;

public final class MaxSubarray {
    public record Res(int left, int right, int sum) {
    }

    public static Res maxSubarray(int[] array) {
        return maxSubarray(array, 0, array.length);
    }

    private static Res maxSubarray(int[] array, int l, int r) {
        if (l == r - 1) { // Масив из одного элемента сам по себе - максимальный подмассив.
            return new Res(l, r, array[l]);
        }

        int mid = (l + r) >>> 1;
        var left = maxSubarray(array, l, mid); // Левый максимальный подмассив.
        var right = maxSubarray(array, mid, r); // Правый максимальный подмассив.
        var cross = maxSubarrayCross(array, l, mid, r); // Раздельный максимальный подмассив.

        // Как результат - выбираем максимальный из них.
        if (left.sum >= right.sum && left.sum >= cross.sum) {
            return left;
        } else if (right.sum >= left.sum && right.sum >= cross.sum) {
            return right;
        } else {
            return cross;
        }
    }

    private static Res maxSubarrayCross(int[] array, int l, int m, int r) {
        // Считаем влево.
        int sum = 0;
        int leftMaxSum = Integer.MIN_VALUE;
        int leftPosition = 0;
        for (int i = m - 1; i >= l; i--) {
            sum += array[i];
            if (sum > leftMaxSum) {
                leftMaxSum = sum;
                leftPosition = i;
            }
        }

        // Считаем вправо.
        sum = 0;
        int rightMaxSum = Integer.MIN_VALUE;
        int rightPosition = 0;
        for (int i = m; i < r; i++) {
            sum += array[i];
            if (sum > rightMaxSum) {
                rightMaxSum = sum;
                rightPosition = i;
            }
        }

        // Получили индексы внутри которых максимальная сумма.
        return new Res(leftPosition, rightPosition, leftMaxSum + rightMaxSum);
    }
}
