package company.vk.polis.ads;


import java.util.*;

public class MinHeap<T extends Comparable<T>> {
    private Object[] heap;
    private int heapSize;
    private static final int two = 2;
    public MinHeap(int size) {
        this.heapSize = 0;
        heap = (new Object[size]);
    }

    public Object[] getHeap() {
        return heap;
    }

    private T getModified(int i) {
        return (T)heap[i];
    }

    private int parent(int i) {
        if (i == 0) {
            return 0;
        }
        return (i - 1) / two;
    }

    private int leftChild(int i) {
        return 2 * i + 1;
    }

    private int rightChild(int i) {
        return 2 * i + 2;
    }

    private void swap(Object[] heap, int i, int j) {
        Object temp = heap[i];
        heap[i] = heap[j];
        heap[j] = temp;
    }

    private void heapifyUp(int i) {
        if (i > 0 && getModified(parent(i)).compareTo(getModified(i)) < 0) {
            swap(heap, i, parent(i));
            heapifyUp(parent(i));
        }
    }

    private void heapifyDown(int i) {
        int left = leftChild(i);
        int right = rightChild(i);
        int smallest = i;
        if (left < heapSize && getModified(left).compareTo(getModified(i)) < 0) {
            smallest = left;
        }
        if (right < heapSize && getModified(right).compareTo(getModified(smallest)) < 0) {
            smallest = right;
        }
        if (smallest != i) {
            swap(heap, i, smallest);
            heapifyDown(smallest);
        }
    }

    public void insert(T element) {
        if (heapSize == heap.length - 1) {
            heap = Arrays.copyOf(heap, heap.length * two);
        }
        heapSize++;
        heap[heapSize - 1] = element;
        heapifyUp(heapSize - 1);
    }

    public T extract() {
        if (heapSize == 0) {
            throw new NoSuchElementException();
        }
        T min = getFirstElement();
        heap[0] = getModified(heapSize - 1);
        heapSize--;
        heapifyDown(0);
        return min;
    }

    public T getFirstElement() {
        return getModified(0);
    }

    public int getHeapSize() {
        return heapSize;
    }

    public List<T> toList(Comparator<T> comparator){
        List<T> list = new ArrayList<>();
        for (int i = 0; i< heapSize;i++){
            list.add(getModified(i));
        }
        list.sort(comparator);
        return list;
    }

}
