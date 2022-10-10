public class SimilarArrays {
    private void swap(int[] arr, int first, int second) {
        int temp = arr[first];
        arr[first] = arr[second];
        arr[second] = temp;
    }

    private int partition(int[] arr, int l, int r) {
        int pivotal = arr[l];
        int i = l;
        for (int j = l + 1; j < r; j++) {
            if (arr[j] <= pivotal) {
                swap(arr, ++i, j);
            }
        }
        swap(arr, i, l);
        return i;
    }

    private void quickSort(int[] arr, int l, int r) {
        if (l >= r - 1) {
            return;
        }
        int i = partition(arr, l, r);
        quickSort(arr, l, i);
        quickSort(arr, i + 1, r);
    }

    public void checkArraysOnSimilar(int[] firstArr, int[] secondArr) {
        quickSort(firstArr, 0, firstArr.length);
        quickSort(secondArr, 0, secondArr.length);
        int firstSum = firstArr[0];
        int last = firstArr[0];
        for (int i = 1; i < firstArr.length; i++) {
            if (firstArr[i] == last) {
                continue;
            }
            firstSum += firstArr[i];
            last = firstArr[i];
        }
        int secondSum = secondArr[0];
        last = secondArr[0];
        for (int i = 1; i < secondArr.length; i++) {
            if (secondArr[i] == last) {
                continue;
            }
            secondSum += secondArr[i];
            last = secondArr[i];
        }
        System.out.println(firstSum == secondSum ? "YES" : "NO");
    }
}
