package company.vk.polis.ads.workshop;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.util.Arrays;
import java.util.Random;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.*;

@Disabled("Disabled since 6th homework released")
class ImprovedInsertionSortTest {
    @Test
    void test() {
        var array = IntStream.generate(() -> new Random().nextInt()).limit(100_000).boxed().toList();
        var expected = array.toArray(Integer[]::new);
        var actual = array.toArray(Integer[]::new);
        Arrays.sort(expected);
        ImprovedInsertionSort.sort(actual);
        assertArrayEquals(expected, actual);
    }

    @Test
    void testBestCase() {
        var array = IntStream.range(0, 10_000_000).boxed().toArray(Integer[]::new);
        assertTimeout(Duration.ofMillis(500), () -> ImprovedInsertionSort.sort(array));
    }
}
