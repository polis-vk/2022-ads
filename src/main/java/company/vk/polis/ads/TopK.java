package company.vk.polis.ads;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * Class that allows us to get k max elements ordered descending in source list.
 */
public final class TopK {
    private class MinHeap<T extends Comparable<T>> {
        private final List<T> list = new ArrayList<>();
        private int length = 0;

        public MinHeap(int length) {
            for (int i = 0; i < length + 1; i++) {
                list.add(null);
            }
        }

        private void sink(int index) {
            int parent = index;
            while (2 * parent <= length) {
                int child = 2 * parent;
                if (child < length && list.get(child + 1).compareTo(list.get(child)) < 0) {
                    child++;
                }
                if (list.get(parent).compareTo(list.get(child)) <= 0) {
                    break;
                }
                swap(parent, child);
                parent = child;
            }
        }

        private T peek() {
            return list.get(1);
        }

        private void swim(int index) {
            int currentIndex = index;
            while (currentIndex > 1 && list.get(currentIndex).compareTo(list.get(currentIndex / 2)) < 0) {
                swap(currentIndex, currentIndex / 2);
                currentIndex /= 2;
            }
        }

        private void swap(int left, int right) {
            T buff = list.get(left);
            list.set(left, list.get(right));
            list.set(right, buff);
        }

        public T extract() {
            T result = list.get(1);
            swap(1, length--);
            sink(1);
            return result;
        }

        public void insert(T type) {
            list.set(++length, type);
            swim(length);
        }
    }

    public <T extends Comparable<T>> List<T> topK(List<T> list, int k) {
        MinHeap<T> minHeap = new MinHeap<>(k);
        Iterator<T> iterator = list.iterator();
        for (int i = 0; i < k; i++) {
            if (iterator.hasNext()) {
                minHeap.insert(iterator.next());
            }
        }
        while (iterator.hasNext()) {
            T currentValue = iterator.next();
            if (currentValue.compareTo(minHeap.peek()) > 0) {
                minHeap.extract();
                minHeap.insert(currentValue);
            }
        }
        List<T> result = new LinkedList<>();
        while (minHeap.length > 0) {
            result.add(minHeap.extract());
        }
        Collections.reverse(result);
        return result;
    }
}
