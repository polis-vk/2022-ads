package company.vk.polis.ads;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Heap<T extends Comparable<T>> {

    private final List<T> arrayMin;
    private static int sizeMin = 0;

    public Heap(int sizeMin) {
        this.arrayMin = new ArrayList<>();
        while (sizeMin >= 0) {
            arrayMin.add(null);
            sizeMin--;
        }
    }

    private void swimMin(int k) {
        while (k > 1 && arrayMin.get(k).compareTo(arrayMin.get(k / 2)) < 0) {
            Collections.swap(arrayMin, k, k / 2);
            k = k / 2;
        }
    }

    public void insertMin(T elem) {
        arrayMin.set(++sizeMin, elem);
        swimMin(sizeMin);
    }

    public void sinkMin(int k) {
        while (2 * k <= sizeMin) {
            int j = 2 * k;
            if (j < sizeMin && arrayMin.get(j).compareTo(arrayMin.get(j + 1)) > 0) {
                j++;
            }
            if (arrayMin.get(k).compareTo(arrayMin.get(j)) <= 0) {
                break;
            }
            Collections.swap(arrayMin, k, j);
            k = j;
        }
    }

    public T extractMin() {
        T min = arrayMin.get(1);
        Collections.swap(arrayMin, 1, sizeMin--);
        sinkMin(1);
        return min;
    }

    public T peekMin() {
        if (sizeMin == 0) {
            return null;
        }
        return arrayMin.get(1);
    }

    public void sort() {
        while (sizeMin > 1) {
            Collections.swap(arrayMin, 1, sizeMin--);
            sinkMin(1);
        }
    }

    public List<T> getArrayMin() {
        return arrayMin;
    }
}
