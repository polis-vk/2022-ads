package company.vk.polis.ads.heap;

import java.util.Iterator;
import java.util.List;

/**
 * Class that allows us to get k max elements ordered descending in source list.
 */
public final class TopK {
    /**
     * Top-K with O(n log(k)) complexity.
     *
     * @param elems input elements iterator
     * @param k     amount of max values to carry out
     * @param <T>   type of elements
     * @return list with k max elements ordered descending
     */
    public <T extends Comparable<T>> List<T> topK(Iterator<T> elems, int k) {
        throw new UnsupportedOperationException("Implement me");
    }
}
