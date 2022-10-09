package company.vk.polis.ads;

import java.util.Comparator;
import java.util.List;

/**
 * Class that allows us to get k max elements ordered descending in source list.
 */
public final class TopK {
    /**
     * Top-K with O(n logk) complexity.
     *
     * @param list input list
     * @param k amount of max values to carry out
     * @return list with k max elements ordered descending
     * @param <T> type of elements
     */
    private static final int maxSize = 1000000;

    public <T extends Comparable<T>> List<T> topK(List<T> list, int k) {
        MinHeap<T> minHeap = new MinHeap<>(maxSize);
        for (int i = 0; i < k; i++) {
            minHeap.insert(list.get(i));
        }

        for (int i = k; i < list.size(); i++) {
            T element = list.get(i);
            if (element.compareTo(minHeap.getFirstElement()) > 0) {
                minHeap.extract();
                minHeap.insert(element);
            }
        }
        return minHeap.toList(new Comparator<T>() {
            @Override
            public int compare(T o1, T o2) {
                return -o1.compareTo(o2);
            }
        });
    }
}
