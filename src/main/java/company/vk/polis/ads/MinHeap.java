package company.vk.polis.ads;

import java.util.ArrayList;

public class MinHeap<T extends Comparable<T>> {
    private final ArrayList<T> arr = new ArrayList<>();
    private int n = 0;
    private int size = 0;

    public int getSize() {
        return size;
    }

    public T getRoot() {
        return arr.get(1);
    }

    public void insert(T value) {
        while (arr.size() <= n * 2 + 1) {
            arr.add(null);
        }
        arr.set(++n, value);
        swim(n);
        size++;
    }

    public T extract() {
        T result = arr.get(1);
        int j = (size > 1 && arr.get(n + 1) != null && arr.get(n).compareTo(arr.get(n + 1)) < 0) ? n + 1 : n;
        arr.set(1, arr.get(j));
        arr.set(j, null);
        n--;
        size--;
        sink(1);
        return result;
    }

    public void swim(int k) {
        while (k > 1 && arr.get(k) != null && arr.get(k).compareTo(arr.get(k / 2)) < 0) {
            swap(k, k / 2);
            k = k / 2;
        }
    }

    public void sink(int k) {
        while (2 * k <= n) {
            int j = 2 * k;
            T left = arr.get(j);
            T right = arr.get(j + 1);
            if (j < n && right != null && left.compareTo(right) > 0) {
                j++;
            }
            if (arr.get(j) != null && arr.get(k).compareTo(arr.get(j)) < 1) {
                break;
            }
            swap(k, j);
            k = j;
        }
    }

    private void swap(int k, int j) {
        T temp = arr.get(k);
        arr.set(k, arr.get(j));
        arr.set(j, temp);
    }
}
