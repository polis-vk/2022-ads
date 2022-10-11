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
        MinHeap<T> heap = new MinHeap<>();
        for (int i = 0; i < list.size(); i++) {
            T currElem = list.get(i);
            if (i < k) {
                heap.insert(currElem);
            } else {
                if (currElem.compareTo(heap.getRoot()) > 0) {
                    heap.extract();
                    heap.insert(currElem);
                }
            }
        }
        List<T> result = new ArrayList<>();
        for (int i = 0; i < k; i++) {
            result.add(heap.extract());
        }
        Collections.reverse(result);
        return result;
    }
}
