package company.vk.polis.ads;

import java.util.ArrayList;
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

        if (list.size() <= k) {
            for (T item : list) {
                heap.insert(item);
            }
        } else {
            for (int i = 0; i < k; i++) {
                heap.insert(list.get(i));
            }

            for (int i = k; i < list.size(); i++) {
                if (list.get(i).compareTo(heap.min()) > 0) {
                    heap.insert(list.get(i));
                }
            }
        }

        List<T> result = new ArrayList<>(heap.size());
        while(heap.size() != 0) {
            result.add(heap.delMin());
        }

        for (int i = 0; i < result.size() / 2; i++) {
            T temp = result.get(i);
            result.set(i, result.get(result.size() - 1 - i));
            result.set(result.size() - 1 - i, temp);
        }

        return result;
    }
}
