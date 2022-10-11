package company.vk.polis.ads;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

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
        MinHeap<T> minHeap = new MinHeap<>(k);
        for (int i = 0; i < k; i++) {
            minHeap.insert(list.get(i));
        }
        for (int i = k; i < list.size(); i++) {
            if (minHeap.peek().compareTo(list.get(i)) < 0) {
                minHeap.extract();
                minHeap.insert(list.get(i));
            }
        }
        List<T> topElements = new ArrayList<>(k);
        for (int i = 0; i < k; i++) {
            topElements.add(minHeap.extract());
        }
        Collections.reverse(topElements);
        return topElements;
    }
}
