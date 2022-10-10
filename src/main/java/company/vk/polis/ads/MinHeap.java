package company.vk.polis.ads;

import java.util.ArrayList;
import java.util.NoSuchElementException;

public class MinHeap<T extends Comparable<T>> {
    private static final int DEFAULT_STARTING_VALUE = 1;

    private final ArrayList<T> data;
    private int currentIndex;
    private int size;

    public MinHeap(int capacity) {
        data = new ArrayList<>(capacity + 1);
        for (int i = 0; i < capacity + 1; i++){
            data.add(null);
        }
        currentIndex = DEFAULT_STARTING_VALUE;
        size = 0;
    }

    public int getSize() {
        return size;
    }

    public void insert(T value){
        data.set(currentIndex, value);
        swim(currentIndex);
        currentIndex++;
        size++;
    }

    public T extract(){
        if (size == 0) {
            throw new NoSuchElementException();
        }
        T minValue = data.get(DEFAULT_STARTING_VALUE);
        data.set(DEFAULT_STARTING_VALUE, data.get(currentIndex - 1));
        currentIndex--;
        sink(DEFAULT_STARTING_VALUE);
        size--;
        return minValue;
    }

    public T peek(){
        if (size == 0) {
            throw new NoSuchElementException();
        }
        return data.get(DEFAULT_STARTING_VALUE);
    }

    private void sink(int index){
        while (getLeftChild(index) <= currentIndex - DEFAULT_STARTING_VALUE) {
            int i = getLeftChild(index);
            if (i < currentIndex - DEFAULT_STARTING_VALUE && data.get(i + 1).compareTo(data.get(i)) < 0){
                i++;
            }
            if (data.get(index).compareTo(data.get(i)) <= 0){
                break;
            }
            swap(index, i);
            index = i;
        }
    }

    private void swap(int index1, int index2){
        T copy = data.get(index1);
        data.set(index1, data.get(index2));
        data.set(index2, copy);
    }

    private void swim(int index){
        int copyOfIndex = index;
        for (int i = getParent(index); copyOfIndex > DEFAULT_STARTING_VALUE; i = getParent(i)){
            if (data.get(copyOfIndex).compareTo(data.get(i)) < 0){
                swap(copyOfIndex, i);
                copyOfIndex = i;
                continue;
            }
            break;
        }
    }

    private static int getParent(int childIndex){
        return childIndex / 2;
    }

    private static int getLeftChild(int parentIndex){
        return parentIndex * 2;
    }
}

