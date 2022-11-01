package company.vk.polis.ads.sortings;

public class MergeSort {
    public static Integer[] getSorted(Integer[] array) {
        Integer[] tempArr = new Integer[array.length];
        sort(array, 0, array.length, tempArr);
        return array;
    }

    public static void sort(Integer[] arr, int left, int right, Integer[] temp) {
        if (left + 1 >= right) {
            return;
        }
        int mid = (left + right) / 2;
        sort(arr, left, mid, temp);
        sort(arr, mid, right, temp);
        merge(arr, left, mid, right, temp);
    }

    private static void merge(Integer[] arr, int left, int mid, int right, Integer[] temp) {
        int firstIter = 0;
        int secondIter = 0;

        while (left + firstIter < mid && mid + secondIter < right) {
            if (arr[left + firstIter] <= arr[mid + secondIter]) {
                temp[firstIter + secondIter] = arr[left + firstIter];
                firstIter++;
                continue;
            }
            temp[firstIter + secondIter] = arr[mid + secondIter];
            secondIter++;
        }

        while (left + firstIter < mid) {
            temp[firstIter + secondIter] = arr[left + firstIter];
            firstIter++;
        }

        while (mid + secondIter < right) {
            temp[firstIter + secondIter] = arr[mid + secondIter];
            secondIter++;
        }

        System.arraycopy(temp, 0, arr, left, firstIter + secondIter);
    }
}
