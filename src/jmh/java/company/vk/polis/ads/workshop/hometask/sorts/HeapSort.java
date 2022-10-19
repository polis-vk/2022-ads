package company.vk.polis.ads.workshop.hometask.sorts;

public class HeapSort {

    public static Integer[] sort(Integer[] array) {
        // Временный массив с лишним нулем, который отсортируем heapSort'ом.
        Integer[] tempArray = new Integer[array.length + 1];
        // Копируем переданный массив во временный (после лишнего нуля).
        System.arraycopy(array, 0, tempArray, 1, array.length);
        Heap heap = new Heap(tempArray);
        heap.sort();

        // Копируем уже отсортированный временный массив обратно.
        System.arraycopy(tempArray, 1, array, 0, array.length);
        return array;
    }

    private static class Heap {

        private final Integer[] a;
        private int size;

        public Heap(Integer[] a) {
            this.size = a.length - 1; // Подразумевается, что нам будет подан массив с "лишним" нулевым элементом.
            this.a = a;

            for (int k = size / 2; k >= 1; k--) {
                sink(k);
            }
        }

        public void sort() {
            while (size > 1) {
                swap(1, size--);
                sink(1);
            }
        }

        public void swap(int i, int j) {
            int temp = a[i];
            a[i] = a[j];
            a[j] = temp;
        }

        public void sink(int index) {
            int k = index;

            while (2 * k <= size) {
                int j = 2 * k;
                if (j < size && a[j + 1] > a[j]) {
                    j++;
                }
                if (a[k] >= a[j]) {
                    break;
                }
                swap(k, j);
                k = j;
            }
        }
    }
}
