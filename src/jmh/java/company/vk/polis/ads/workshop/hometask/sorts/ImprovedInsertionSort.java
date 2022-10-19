package company.vk.polis.ads.workshop.hometask.sorts;

public final class ImprovedInsertionSort {

    public static Integer[] sort(Integer[] array) {
        sort(array, 0, array.length);
        return array;
    }

    public static void sort(Integer[] array, int fromInclusive, int toExclusive) {
        for (int i = fromInclusive + 1; i < toExclusive; ++i) { // Массив из одного элемента уже отсортирован.
            Integer key = array[i];

            if (key >= array[i - 1]) { // Обрабатываем случай, когда элемент уже на своем месте.
                continue;
            }

            int insertionPosition = insertionPosition(array, key, fromInclusive, i);
            // Сдвигаем часть массива, чтобы вставить элемент в нужное место.
            System.arraycopy(array, insertionPosition, array, insertionPosition + 1, i - insertionPosition);
            array[insertionPosition] = key;
        }
    }

    private static <E extends Comparable<E>> int insertionPosition(E[] array, E key, int fromInclusive, int toExclusive) {
        int l = fromInclusive;
        int r = toExclusive;

        while (l < r) {
            int mid = (l + r) >>> 1; // Деление на 2 с защитой от переполнения.
            var el = array[mid];
            final var cmp = el.compareTo(key);

            if (cmp > 0) { // Искомое место слева.
                r = mid;
            } else if (cmp < 0) { // Искомое место справа.
                l = mid + 1;
            } else {
                return mid; // Мы нашли место.
            }
        }

        return l; // Когда мы "схлопнемся" - место найдено.
    }
}
