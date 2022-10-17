package company.vk.polis.ads;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Class that allows us to get k max elements ordered descending in source list.
 */
public final class TopK {
    /**
     * Top-K with O(n logk) complexity.
     * @param list input list
     * @param k amount of max values to carry out
     * @return list with k max elements ordered descending
     * @param <T> type of elements
     */
    public <T extends Comparable<T>> List<T> topK(List<T> list, int k) {
        MinHeap<T> heap = new MinHeap<>(100);

        for (int i = 0; i < k; i++) {
            heap.insert(list.get(i));
        }

        for (int i = k; i < list.size(); i++) {
            if (list.get(i).compareTo(heap.first()) > 0) {
                heap.insert(list.get(i));
            }
        }

        List<T> massive = new ArrayList<>();
        while (heap.size() > 0) {
            massive.add(heap.extract());
        }

        Collections.reverse(massive);
        return massive;
    }
}
