package company.vk.polis.ads.workshop.sorting;

public class HeapSort {
    public static <E extends Comparable<E>> E[] sort(E[] heap) {
        makeHeap(heap);
        int length = heap.length - 1;
        while (length > 1) {
            swap(heap, 1, length--);
            sink(heap, 1, length);
        }

        return heap;
    }

    private static <E extends Comparable<E>> void heapSort(E[] mas) {
        makeHeap(mas);
        sort(mas);
    }

    private static <E extends Comparable<E>> void sink(E[] mas, int positionStart, int positionEnd) {
        while (positionStart * 2 <= positionEnd) {
            int j = 2 * positionStart;

            if (j < positionEnd && mas[j].compareTo(mas[j + 1]) < 0) {
                j++;
            }

            if (mas[positionStart].compareTo(mas[j]) >= 0) {
                break;
            }

            swap(mas, positionStart, j);
            positionStart = j;
        }
    }

    private static <E extends Comparable<E>> void swap(E[] mas, int positionOne, int positionTwo) {
        var temp = mas[positionOne];
        mas[positionOne] = mas[positionTwo];
        mas[positionTwo] = temp;
    }

    private static <E extends Comparable<E>> void makeHeap(E[] mas) {
        int length = mas.length - 1;
        for (int i = length / 2; i >= 1; i--) {
            sink(mas, i, length);
        }
    }
<<<<<<< HEAD
}
=======
}
>>>>>>> 020c8f5ca24b8046f661c9a88d1f806157ab3698
