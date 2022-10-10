package company.vk.polis.ads;

import java.util.ArrayList;
import java.util.Arrays;
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
 * @param <T> type of elements
 * @return list with k max elements ordered descending
 */
    public <T extends Comparable<T>> List<T> topK(List<T> list, int k) {
        MinHeap<T> minHeap = new MinHeap<>(k);
        for (int i = 0; i < k; i++) {
            minHeap.insert(list.get(i));
        }
        for (int i = k; i < list.size(); i++) {
            if(list.get(i).compareTo(minHeap.root()) > 0){
                minHeap.delMin();
                minHeap.insert(list.get(i));
            }
        }
        List<T> result = new ArrayList<>();
        for (int i = 0; i < k; i++) {
            result.add(minHeap.delMin());
        }
        return list;
    }
}
