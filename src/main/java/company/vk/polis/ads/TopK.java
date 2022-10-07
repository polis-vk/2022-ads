package company.vk.polis.ads;

import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

/**
 * Class that allows us to get k max elements ordered descending in source list.
 */
public final class TopK {
    private static class Descend implements Comparator<Integer> {
        @Override
        public int compare(Integer t1, Integer t2) {
            return t2 - t1;
        }
    }

    /**
     * Top-K with O(n logk) complexity.
     *
     * @param list input list
     * @param k    amount of max values to carry out
     * @param <T>  type of elements
     * @return list with k max elements ordered descending
     */
    public <T extends Comparable<T>> List<T> topK(List<T> list, int k) {
        GenericHeap<T> minHeap = new GenericHeap<>(k);
        for (var el : list) {
            if (minHeap.getHeapSize() < k) {
                minHeap.insert(el);
            } else {
                if (minHeap.getTop().compareTo(el) < 0) {
                    minHeap.extract();
                    minHeap.insert(el);
                }
            }
        }

        LinkedList<T> res = new LinkedList<>();
        while (minHeap.getHeapSize() > 0) {
            res.addFirst(minHeap.extract());
        }

        return res;
    }
}
