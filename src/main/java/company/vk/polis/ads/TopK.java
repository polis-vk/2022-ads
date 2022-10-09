package company.vk.polis.ads;

import java.util.LinkedList;
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
        Heap<T> heap = new Heap<T>(k);
        for (int i = 0; i < list.size() && i < k; i++) {
            heap.insert(list.get(i));
        }
        for (int i = k; i < list.size(); i++) {
            if (list.get(i).compareTo(heap.peek()) > 0) {
                heap.delMin();
                heap.insert(list.get(i));
            }
        }
        LinkedList<T> resList = new LinkedList<T>();
        for (int i = 0; i < k; i++) {
            resList.addFirst(heap.delMin());
        }
        return resList;
    }
}
