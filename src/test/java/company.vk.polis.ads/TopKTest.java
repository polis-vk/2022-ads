package company.vk.polis.ads;

import it.unimi.dsi.fastutil.ints.IntArrayList;
import it.unimi.dsi.fastutil.ints.IntComparators;
import it.unimi.dsi.fastutil.ints.IntList;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.*;

@Disabled("Disabled since 4th homework released")
class TopKTest {
    private static final int SEQUENCE_SIZE = 100_000_000;
    private static final int TOP_K = 1_000_000;

    @Test
    void testTopK() {
        var input = IntStream.generate(() -> ThreadLocalRandom.current().nextInt(Integer.MAX_VALUE))
                .limit(SEQUENCE_SIZE)
                .collect(() -> new IntArrayList(SEQUENCE_SIZE), IntList::add, IntList::addAll);
        var expected = dummyTopK(input, TOP_K);
        var actual = new TopK().topK(input, TOP_K);
        assertEquals(expected, actual);
    }

    private static IntList dummyTopK(IntList list, int k) {
        list.sort(IntComparators.OPPOSITE_COMPARATOR);
        return list.subList(0, k);
    }
}
