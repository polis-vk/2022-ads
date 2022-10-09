package company.vk.polis.ads;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class HeapKeyValue<T extends Comparable<T>> {

    private final List<Pair<T>> arrayMin;
    private static int sizeMin = 0;

    public HeapKeyValue(int sizeMin) {
        this.arrayMin = new ArrayList<>();
        while (sizeMin >= 0) {
            arrayMin.add(null);
            sizeMin--;
        }
    }

    private void swimMin(int k) {
        while (k > 1 && arrayMin.get(k).getValue().compareTo(arrayMin.get(k / 2).getValue()) < 0) {
            Collections.swap(arrayMin, k, k / 2);
            k = k / 2;
        }
    }

    public void insertMin(Pair<T> elem) {
        arrayMin.set(++sizeMin, elem);
        swimMin(sizeMin);
    }

    public void sinkMin(int k) {
        while (2 * k <= sizeMin) {
            int j = 2 * k;
            if (j < sizeMin && arrayMin.get(j).getValue().compareTo(arrayMin.get(j + 1).getValue()) > 0) {
                j++;
            }
            if (arrayMin.get(k).getValue().compareTo(arrayMin.get(j).getValue()) <= 0) {
                break;
            }
            Collections.swap(arrayMin, k, j);
            k = j;
        }
    }

    public Pair<T> extractMin() {
        Pair<T> min = arrayMin.get(1);
        Collections.swap(arrayMin, 1, sizeMin--);
        sinkMin(1);
        return min;
    }

    public int getSizeMin() {
        return sizeMin;
    }

}
