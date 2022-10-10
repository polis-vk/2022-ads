package company.vk.polis.ads;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import org.w3c.dom.css.ElementCSSInlineStyle;

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
        Iterator<T> iterator = list.iterator();

        while (iterator.hasNext() && minHeap.size() < k) {
            minHeap.insert(iterator.next());
        }

        while (iterator.hasNext()) {
            T current = iterator.next();
            if (current.compareTo(minHeap.peek()) > 0) {
                minHeap.extract();
                minHeap.insert(current);
            }

        }
        List<T> result = new ArrayList<>();

        while (!minHeap.isEmpty()) {
            result.add(minHeap.extract());
        }

        Collections.reverse(result);
        return result;
    }
}

class MinHeap<T extends Comparable<T>> {
    private List<T> a;
    private int n;

    public MinHeap() {
        a = new ArrayList<>();
        a.add(null);
        n = 0;
    }

    public boolean isEmpty() {
        return n == 0;
    }

    public int size() {
        return n;
    }

    public void insert(T value) {
        a.add(value);
        swim(++n);
    }

    public T peek() {
        return a.get(1);
    }

    public T extract() {
        T max = a.get(1);
        swap(1, n);

        a.remove(n);
        n--;
        sink(1);
        return max;
    }


    private void swim(int k) {
        while (k > 1 && a.get(k).compareTo(a.get(k / 2)) < 0) {
            swap(k, k / 2);
            k = k / 2;
        }
    }

    private void sink(int k) {
        while (2 * k <= n) {
            int j = 2 * k;
            if (j < n && a.get(j).compareTo(a.get(j + 1)) > 0) j++;
            if (a.get(k).compareTo(a.get(j)) <= 0) break;
            swap(k, j);
            k = j;
        }
    }

    private void swap(int m, int n) {
        T temp = a.get(m);
        a.set(m, a.get(n));
        a.set(n, temp);
    }

}