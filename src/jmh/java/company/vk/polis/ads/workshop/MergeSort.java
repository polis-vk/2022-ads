package company.vk.polis.ads.workshop;


public class MergeSort {
    public static <E extends Comparable> E[] sort(E[] arr){
        return sort(arr, 0, arr.length);
    }
    public static <E extends Comparable> E[] sort(E[] arr, int fromInc, int toExc){
        if (fromInc == toExc - 1){
            E[] ans = (E[]) java.lang.reflect.Array.newInstance(arr[fromInc].getClass(), 1);
            ans[0] = arr[fromInc];
            return ans;
        }
        int m = fromInc + ((toExc - fromInc) >>> 1);
        E[] left = sort(arr, fromInc, m);
        E[] right = sort(arr, m, toExc);
        return merge(left, right);
    }

    public static <E extends  Comparable> E[] merge(E[] left, E[] right){

        E[] ans = (E[]) java.lang.reflect.Array.newInstance(left[0].getClass(), left.length + right.length);
        int l = 0;
        int r = 0;
        while (l < left.length && r < right.length){
            if (left[l].compareTo(right[r]) > 0){
                ans[l + r] = right[r];
                r++;
            } else {
                ans[l + r] = left[l];
                l++;
            }
        }

        while (l < left.length){
            ans[l + r] = left[l];
            l++;
        }

        while (r < right.length){
            ans[l + r] = right[r];
            r++;
        }

        return ans;
    }

}
