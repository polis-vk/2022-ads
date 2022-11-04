package company.vk.polis.ads.workshop;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.util.ArrayDeque;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

@Disabled("Disabled since 6th homework released")
public class CircularBufferQueueTest {
    @Test
    void test() {
        var expected = new ArrayDeque<Integer>(100_000);
        var actual = new CircularBufferQueue<Integer>(100_000);
        IntStream.range(0, 100_000)
                .forEach(i -> assertEquals(expected.offer(i), actual.offer(i), "offer(" + i + ")"));
        assertFalse(actual.offer(1), "offer");
        assertEquals(expected.size(), actual.size(), "size");
        var expectedIter = expected.iterator();
        var actualIter = actual.iterator();
        while (expectedIter.hasNext() && actualIter.hasNext()) {
            assertEquals(expectedIter.next(), actualIter.next());
        }
        while (!expected.isEmpty() || !actual.isEmpty()) {
            assertEquals(expected.peek(), actual.peek(), "peek");
            assertEquals(expected.poll(), actual.poll(), "poll");
        }
        IntStream.range(0, 50_000)
                .forEach(i -> assertEquals(expected.offer(i), actual.offer(i), "offer(" + i + ")"));
        for (int i = 0; i < 25_000; ++i) {
            assertEquals(expected.peek(), actual.peek(), "peek");
            assertEquals(expected.poll(), actual.poll(), "poll");
        }
        IntStream.range(0, 25_000)
                .forEach(i -> assertEquals(expected.offer(i), actual.offer(i), "offer(" + i + ")"));
        assertEquals(expected.size(), actual.size(), "size");
        expectedIter = expected.iterator();
        actualIter = actual.iterator();
        while (expectedIter.hasNext() && actualIter.hasNext()) {
            assertEquals(expectedIter.next(), actualIter.next());
        }
    }
}
