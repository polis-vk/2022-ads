package company.vk.polis.ads.workshop;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.util.concurrent.ThreadLocalRandom;

import static org.junit.jupiter.api.Assertions.*;

@Disabled("Disabled since 6th homework released")
class MaxSubarrayTest {
    private int[] generateArray(int length) {
        int[] array = new int[length];
        for (int i = 0; i < length; ++i) {
            array[i] = ThreadLocalRandom.current().nextInt() % (Integer.MAX_VALUE / length);
        }
        return array;
    }

    @Test
    public void testMaximumSubarray() {
        final int[] LENGTHS = {10, 100, 1000, 10_000};
        for (int i = 0; i < LENGTHS.length; ++i) {
            for (int length : LENGTHS) {
                int[] array = generateArray(length);

                var expected = bruteForceSolution(array);
                var actual = MaxSubarray.maxSubarray(array);

                assertEquals(expected.sum(), actual.sum());
            }
        }
    }

    private static MaxSubarray.Res bruteForceSolution(int[] array) {
        int left = 0;
        int right = 0;
        int maximumSum = Integer.MIN_VALUE;
        for (int i = 0; i < array.length; ++i) {
            int sum = 0;
            for (int j = i; j < array.length; ++j) {
                sum += array[j];
                if (sum >= maximumSum) {
                    maximumSum = sum;
                    left = i;
                    right = j;
                }
            }
        }
        return new MaxSubarray.Res(left, right, maximumSum);
    }
}
