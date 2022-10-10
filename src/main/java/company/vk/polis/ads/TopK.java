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
     *
     * @param list input list
     * @param k    amount of max values to carry out
     * @param <T>  type of elements
     * @return list with k max elements ordered descending
     */
    public <T extends Comparable<T>> List<T> topK(List<T> list, int k) {
        MyHeap<T> heap = new MyHeap<>(list.size());
        int i = 0;
        for (; i < k; i++) {
            heap.insert(list.get(i));
        }
        for (; i < list.size(); i++) {
            if (list.get(i).compareTo(heap.peek()) > 0) {
                heap.delMin();
                heap.insert(list.get(i));
            }
        }
        List<T> resList = new ArrayList<>();
        while (heap.getSize() > 0) {
            resList.add(heap.delMin());
        }
        Collections.reverse(resList);
        return resList;
    }
}
