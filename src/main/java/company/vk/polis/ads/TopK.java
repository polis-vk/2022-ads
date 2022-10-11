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

        List<T> heapArray = new ArrayList<>();
        Heap<T> minHeap = new Heap(k + 1);

        for (int i = 0; i < k; i++) {
            minHeap.insert(list.get(i));
        }

        for (int i = k; i < list.size(); i++) {
           if (list.get(i).compareTo(minHeap.getMin()) > 0) {
               minHeap.returnMin();
               minHeap.insert(list.get(i));
           }
        }

        for (int i = 0; i < k; i++) {
            heapArray.add(0, minHeap.returnMin());
        }

        return heapArray;
    }



}
