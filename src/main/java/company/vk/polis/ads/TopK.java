package company.vk.polis.ads;

import java.util.*;

/**
 * Class that allows us to get k max elements ordered descending in source list.
 */
class Heap<T extends Comparable<T>> {

    private final List<T> heapList;
    private int currentSize;

    public Heap(int size) {
        this.currentSize = 0;
        this.heapList = new ArrayList<>();
        for (int i = 0; i < size + 1; i++) {
            heapList.add(null);
        }
    }

    public void insert(T value) {
        heapList.set(++currentSize, value);
        swim(currentSize);
    }

    public T exctract() {
        if (currentSize == 0) {
            throw new RuntimeException();
        }
        if (currentSize == 1) {
            currentSize--;
        }
        T root = heapList.get(1);
        Collections.swap(heapList, 1, currentSize--);
        sink(1);
        return root;
    }

    private void swim(int k) {
        while (k > 1 && heapList.get(k).compareTo(heapList.get(k / 2)) < 0) {
            Collections.swap(heapList, k, k / 2);
            k /= 2;
        }
    }

    private void sink(int k) {
        while (2 * k <= currentSize) {
            int j = 2 * k;
            if (j < currentSize && heapList.get(j + 1).compareTo(heapList.get(j)) < 0) {
                j++;
            }
            if (heapList.get(k).compareTo(heapList.get(j)) <= 0) {
                break;
            }
            Collections.swap(heapList, k, j);
            k = j;
        }
    }

    public T getFirstElement() {
        return heapList.get(1);
    }

    public int getSize() {
        return currentSize;
    }
}

public final class TopK {
    public <T extends Comparable<T>> List<T> topK(List<T> list, int k) {
        Heap<T> minHeap = new Heap<>(k);
        ListIterator<T> listIterator = list.listIterator();
        while (listIterator.hasNext() && minHeap.getSize() < k) {
            minHeap.insert(listIterator.next());
        }
        while (listIterator.hasNext()) {
            if (listIterator.next().compareTo(minHeap.getFirstElement()) > 0) {
                minHeap.exctract();
                minHeap.insert(listIterator.next());
            }
        }
        List<T> topKList = new ArrayList<>();
        while (minHeap.getSize() > 0) {
            topKList.add(minHeap.exctract());
        }
        Collections.reverse(topKList);
        return topKList;
    }
}
