package company.vk.polis.ads;

import java.util.ArrayList;
import java.util.LinkedList;
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
        MinHeap<T> minHeap = new MinHeap<>();
        for (T element : list) {
            if (minHeap.size() < k) {
                minHeap.add(element);
            } else {
                if (element.compareTo(minHeap.peek()) > 0) {
                    minHeap.pop();
                    minHeap.add(element);
                }
            }
        }

        LinkedList<T> ans = new LinkedList<>();

        while (!minHeap.isEmpty()) {
            ans.addFirst(minHeap.pop());
        }

        return ans;
    }


}