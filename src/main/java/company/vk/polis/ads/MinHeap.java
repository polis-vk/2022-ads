package company.vk.polis.ads;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.NoSuchElementException;

class MinHeap<T extends Comparable<T>> {
    public static final int DEFAULT_SIZE = 64;
    private ArrayList<T> arr;
    private int lastPointer;


    public MinHeap(){
        this(DEFAULT_SIZE);
    }


    public MinHeap(int size) {
        this.arr = new ArrayList<>(size);
        this.lastPointer = -1;
    }

    public void add(T value){
        try {
            arr.set(++lastPointer, value);
        } catch (IndexOutOfBoundsException e){
            arr.add(value);
        }
        swim(lastPointer);
    }

    public T peek(){
        if (lastPointer == -1){
            throw new NoSuchElementException();
        }

        return arr.get(0);
    }

    public T pop() {
        if (lastPointer == -1){
            throw new NoSuchElementException();

        }

        T ans = arr.get(0);

        arr.set(0, arr.get(lastPointer--));

        sink(0);

        return ans;
    }

    public int size(){
        return lastPointer + 1;
    }

    public boolean isEmpty (){
        return lastPointer == -1;
    }

    private void sink(int index){
        int leftChildIndex = index * 2 + 1;
        int rightChildIndex = index * 2 + 2;

        if (leftChildIndex <= lastPointer && arr.get(leftChildIndex).compareTo(arr.get(index)) < 0 &&
                arr.get(leftChildIndex).compareTo(arr.get(rightChildIndex)) < 0){
            swap(leftChildIndex, index);
            sink(leftChildIndex);
        } else if (rightChildIndex <= lastPointer && arr.get(rightChildIndex).compareTo(arr.get(index)) < 0) {
            swap(rightChildIndex, index);
            sink(rightChildIndex);
        }
    }

    private void swim(int index){
        int parentIndex = (index - 1) / 2;

        if (arr.get(index).compareTo(arr.get(parentIndex)) < 0)  {
            swap(index, parentIndex);
            swim(parentIndex);
        }


    }

    private void swap(int index1, int index2){
        T tmp = arr.get(index1);
        arr.set(index1, arr.get(index2));
        arr.set(index2, tmp);
    }
}