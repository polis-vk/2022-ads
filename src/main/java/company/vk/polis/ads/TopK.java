package company.vk.polis.ads;

import java.util.List;
import java.util.*;

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

        Heap<T> heap = new Heap<>(k);
        Iterator<T> iter = list.iterator();
        List<T> result = new LinkedList<>();

        for (int i = 0; i < k; i++) {
            if (iter.hasNext()) {
                heap.insert(iter.next());
            }
        }

        while (iter.hasNext()) {
            T cur = iter.next();
            if (cur.compareTo(heap.peek()) > 0) {
                heap.extract();
                heap.insert(cur);
            }
        }

        while (!heap.isEmpty()) {
            result.add(heap.extract());
        }

        Collections.reverse(result);
        return result;
    }
}
