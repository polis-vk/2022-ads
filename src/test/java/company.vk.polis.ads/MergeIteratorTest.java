package company.vk.polis.ads;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.*;

class MergeIteratorTest {
    private static List<Iterator<Integer>> iterators;

    @BeforeEach
    void beforeEach() {
        iterators = new ArrayList<>(List.of(
            IntStream.range(0, Integer.MAX_VALUE / 4)
                .filter(i -> i % 2 == 0)
                .iterator(),
            IntStream.range(Integer.MAX_VALUE / 4, Integer.MAX_VALUE / 2)
                .filter(i -> i % 3 == 0)
                .iterator(),
            IntStream.range(Integer.MAX_VALUE / 8, Integer.MAX_VALUE / 4)
                .filter(i -> i % 5 == 0)
                .iterator(),
            IntStream.range(Integer.MAX_VALUE / 2, Integer.MAX_VALUE)
                .filter(i -> i % 7 == 0)
                .iterator(),
            IntStream.range(0, Integer.MAX_VALUE / 2)
                .iterator()
        ));
        Collections.shuffle(iterators);
    }

    @Test
    void testIteratorContract() {
        var iter = new MergeIterator<Byte>(List.of());
        assertFalse(iter.hasNext());
        assertThrows(NoSuchElementException.class, iter::next);

        iter = new MergeIterator<>(List.of(List.of((byte) 1).iterator()));
        assertTrue(iter.hasNext());
        assertNotNull(iter.next());
    }

    @Test
    void testMerge() {
        var iter = new MergeIterator<>(iterators);
        var prev = iter.next();
        while (iter.hasNext()) {
            var next = iter.next();
            assertTrue(prev <= next, prev + " > " + next);
            prev = next;
        }
        assertTrue(iterators.stream().noneMatch(Iterator::hasNext), "Some source iterators are not fully consumed");
    }
}
