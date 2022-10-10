package Part3;

import java.util.Comparator;

public class Heap {
    private static final int DEFAULT_STARTING_VALUE = 1;
    private static final int DEFAULT_ARRAY_SIZE = 15;
    private static final double GOLDEN_RATIO = 1.618D;

    int[] data;
    int counter;
    int capacity;
    Comparator<Integer> cmp;

    public Heap(Comparator<Integer> cmp){
        this(DEFAULT_ARRAY_SIZE, cmp);
    }

    public Heap(int size, Comparator<Integer> cmp){
        capacity = size + DEFAULT_STARTING_VALUE;
        data = new int[capacity];
        counter = DEFAULT_STARTING_VALUE;
        this.cmp = cmp;
    }

    public void insert(int value){
        if (counter == capacity){
            ensureCapacity();
        }
        data[counter] = value;
        swim(counter);
        counter++;
    }

    public int extract(){
        int maxValue = data[DEFAULT_STARTING_VALUE];
        data[DEFAULT_STARTING_VALUE] = data[counter - 1];
        counter--;
        sink(DEFAULT_STARTING_VALUE);
        return maxValue;
    }

    public int peek(){
        return data[DEFAULT_STARTING_VALUE];
    }

    private void sink(int index){
        while (getLeftChild(index) <= counter - DEFAULT_STARTING_VALUE) {
            int i = getLeftChild(index);
            if (i < counter - DEFAULT_STARTING_VALUE && cmp.compare(data[i  + 1], data[i]) > 0){
                i++;
            }
            if (cmp.compare(data[index], data[i]) >= 0){
                break;
            }
            swap(index, i);
            index = i;
        }
    }

    private void ensureCapacity(){
        capacity *= GOLDEN_RATIO;
        int[] copy = new int[capacity];
        for (int i = DEFAULT_STARTING_VALUE; i < data.length; i++){
            copy[i] = data[i];
        }
        data = copy;
    }

    private void swap(int index1, int index2){
        int copy = data[index1];
        data[index1] = data[index2];
        data[index2] = copy;
    }

    private void swim(int index){
        int copyOfIndex = index;
        for (int i = getParent(index); copyOfIndex > DEFAULT_STARTING_VALUE; i = getParent(i)){
            if (cmp.compare(data[copyOfIndex], data[i]) > 0){
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
