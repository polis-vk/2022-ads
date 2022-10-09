package company.vk.polis.ads;

import java.util.ArrayList;
import java.util.List;

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
        Heap<T> heap = new Heap<>(k);
        int i = 0;
        for (; i < k; i++) {
            heap.insertMin(list.get(i));
        }

        for (; i < list.size(); i++) {
            T current = list.get(i);
            if (current.compareTo(heap.peekMin()) > 0) {
                heap.extractMin();
                heap.insertMin(current);
            }
        }

        heap.sort();

        List<T> result = new ArrayList<>();
        for (i = 1; i < heap.getArrayMin().size(); i++) {
            result.add(heap.getArrayMin().get(i));
        }
        return result;
    }
}
