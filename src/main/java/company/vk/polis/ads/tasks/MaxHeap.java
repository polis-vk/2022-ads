package company.vk.polis.ads.tasks;

public class MaxHeap {

    private int[] array;
    private int size;

    public MaxHeap(int cap) {
        array = new int[cap + 1];
        size = 0;
    }

    public void insert(int value) {
        array[++size] = value;
        swim(size);
    }

    public int extract() {
        int max = array[1];

        swap(1, size--);
        sink(1);

        return max;
    }

    public void swim(int index) {
        int k = index;

        while (k > 1 && array[k] > array[k / 2]) {
            swap(k, k / 2);
            k /= 2;
        }
    }

    public void sink(int index) {
        int k = index;

        while (2 * k <= size) {
            int j = 2 * k;

            if (j < size && array[j + 1] > array[j]) {
                j++;
            }

            if (array[k] >= array[j]) {
                break;
            }

            swap(k, j);

            k = j;
        }
    }

    public int peek() {
        if (size == 0) {
            return -1;
        }

        return array[1];
    }

    public void swap(int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }
}


