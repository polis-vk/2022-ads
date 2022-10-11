package company.vk.polis.ads;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.IntStream;

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
        if (list.size() <= k) {
            list.forEach(minHeap::insert);
        } else {
            IntStream.range(0, k).mapToObj(list::get).forEach(minHeap::insert);
            IntStream.range(k, list.size()).filter(i -> list.get(i).compareTo(minHeap.getMin()) > 0).forEach(i -> minHeap.insert(list.get(i)));
        }
        List<T> result = new ArrayList<>(minHeap.getSize());
        while (minHeap.getSize() != 0) {
            result.add(minHeap.popMin());
        }
        Collections.reverse(result);
        return result;
    }
}