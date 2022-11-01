package company.vk.polis.ads.workshop;

public final class ImprovedInsertionSort {
    public static <E extends Comparable<E>> E[] sort(E[] array) {
        sort(array, 0, array.length);
        return array;
    }

    public static <E extends Comparable<E>> int position(E[] array, E key, int fromInclusive, int toExclusive){
        int l = fromInclusive, r = toExclusive;
        while (l < r){
            int mid = (l + r) / 2;
            int temp = array[mid].compareTo(key);
            if(temp > 0){
                r = mid;
            }else if(temp < 0){
                l = mid + 1;
            }else{
                return mid;
            }
        }
        return l;
    }

    public static <E extends Comparable<E>> void sort(E[] array, int fromInclusive, int toExclusive) {
        for (int i = fromInclusive + 1; i < toExclusive; i++) {
            E key = array[i];
            if(array[i].compareTo(array[i-1]) >= 0){
                continue;
            }
            int cur = position(array, key, fromInclusive, i);
            System.arraycopy(array, cur, array, cur + 1, i - cur);
            array[cur] = key;
        }
    }
}
