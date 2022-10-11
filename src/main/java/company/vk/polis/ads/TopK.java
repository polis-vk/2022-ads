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
        HeapMin<T> heapMin = new HeapMin<>(k);
        int size = list.size() - 1;
        for (int i = size; i > size - k; i--){
            heapMin.insert(list.remove(i));
        }
        for (int i = k - 1; i >= 0; i--){
            if (list.get(i).compareTo(heapMin.peek()) > 0) {
                heapMin.extract();
                heapMin.insert(list.get(i));
            }
        }
        LinkedList<T> res = new LinkedList<>();
        for (int i = 0; i < k; i++){
            res.addFirst(heapMin.extract());
        }
        return res;
    }
}
