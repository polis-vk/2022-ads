package company.vk.polis.ads;

import org.junit.jupiter.api.Test;

import java.util.Comparator;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.*;

class TopKTest {
    @Test
    void testTopK() {
        var input = IntStream.range(0, 10_000_000)
                .map(__ -> ThreadLocalRandom.current().nextInt(Integer.MAX_VALUE))
                .boxed()
                .collect(Collectors.toList());
        var expected = dummyTopK(input, 100_000);
        var actual = new TopK().topK(input, 100_000);
        assertEquals(expected, actual);
    }

    private static List<Integer> dummyTopK(List<Integer> list, int k) {
        list.sort(Comparator.reverseOrder());
        return list.subList(0, k);
    }
}
