package company.vk.polis.ads.heap;

import it.unimi.dsi.fastutil.ints.IntArrayList;
import it.unimi.dsi.fastutil.ints.IntComparators;
import org.junit.jupiter.api.Test;

import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.*;

class TopKTest {
    private static final int SEQUENCE_SIZE = nextInt(150_000_000, 200_000_000);
    private static final int TOP_K = nextInt(7_500_000, 10_000_000);
    private static final int TOP_K_MIN_VALUE_BOUND = nextInt(Integer.MAX_VALUE / 2 - 1000, Integer.MAX_VALUE / 2);

    @Test
    void testTopK() {
        var expected = new IntArrayList(TOP_K);
        var sequence = IntStream.concat(
            IntStream.generate(() -> nextInt(0, TOP_K_MIN_VALUE_BOUND))
                .limit(SEQUENCE_SIZE - TOP_K),
            IntStream.generate(() -> {
                int i = nextInt(TOP_K_MIN_VALUE_BOUND, Integer.MAX_VALUE);
                expected.add(i);
                return i;
            }).limit(TOP_K)
        ).iterator();
        var actual = new TopK().topK(sequence, TOP_K);
        expected.sort(IntComparators.OPPOSITE_COMPARATOR);
        assertEquals(expected, actual);
    }

    private static int nextInt(int from, int to) {
        return ThreadLocalRandom.current().nextInt(to - from) + from;
    }
}
