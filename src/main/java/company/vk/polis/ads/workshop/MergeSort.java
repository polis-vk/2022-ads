package company.vk.polis.ads.workshop;

public class MergeSort {
    public static <E extends Comparable<E>> E[] sort(E[] array){
        sort(array, 0, array.length);
        return array;
    }

    public static <E extends Comparable<E>> void sort(E[] array, int low, int high){
        if(low == high - 1){
            return;
        }
        int mid = low + (high - low) / 2;
        sort(array, low, mid);
        sort(array, mid, high);
        merge(array, low, mid, high);
    }

    private static <E extends Comparable<E>> void merge(E[] array, int low, int mid, int high){
        E[] l = (E[]) new Comparable[mid - low];
        E[] r = (E[]) new Comparable[high - mid];
        System.arraycopy(array, low, l, 0, l.length);
        System.arraycopy(array, mid, r, 0, r.length);
        int pt1 = 0;
        int pt2 = 0;
        int ptc = low;
        while(pt1 < l.length && pt2 < r.length){
            if(l[pt1].compareTo(r[pt2]) < 0){
                array[ptc++] = l[pt1++];
            }else{
                array[ptc++] = r[pt2++];
            }
        }
        while(pt1 < l.length){
            array[ptc++] = l[pt1++];
        }
        while(pt2 < r.length){
            array[ptc++] = r[pt2++];
        }
    }
}
