package company.vk.polis.ads;

import java.util.List;
import java.util.Comparator;
import java.util.Iterator;
import java.util.ArrayList;
import java.util.stream.Collectors;

/**
 * Class that allows us to get k max elements ordered descending in source list.
 */
public final class TopK {
    /**
     * Top-K with O(n logk) complexity.
     *
     * @param list input list
     * @param k    amount of max values to carry out
     * @param <T>  type of elements
     * @return list with k max elements ordered descending
     */
    public <T extends Comparable<T>> List<T> topK(List<T> list, int k) {
        MinHeap<T> heap = new MinHeap<>(k);
        Iterator<T> iterator = list.iterator();
        while (iterator.hasNext() && heap.size() < k) {
            heap.add(iterator.next());
        }
        T currElement;
        while (iterator.hasNext()) {
            currElement = iterator.next();
            if (heap.peek().compareTo(currElement) < 0) {
                heap.remove();
                heap.add(currElement);
            }
        }

        List<T> largest = new ArrayList<>();
        while (heap.size() > 0) {
            largest.add(heap.remove());
        }
        return largest.stream()
                .sorted(Comparator.reverseOrder())
                .collect(Collectors.toList());
    }
}

