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
        MinHeap<T> minHeap = new MinHeap<>(k);
        int counter = list.size() - 1;
        for (int i = 0; i < k; i++){
            minHeap.insert(list.get(counter));
            list.remove(counter);
            counter--;
        }
        for (;counter >= 0; counter--){
            if (minHeap.peek().compareTo(list.get(counter)) < 0){
                minHeap.extract();
                minHeap.insert(list.get(counter));
            }
        }
        LinkedList<T> result = new LinkedList<>();
        for (int i = 0; i < k; i++){
            result.addFirst(minHeap.extract());
        }
        return result;
    }
}
