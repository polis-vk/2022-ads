public class CountInversions {
    private int countInv(int[] arr, int left, int right, int[] temp) {
        if (left + 1 >= right) {
            return 0;
        }
        int mid = (left + right) / 2;
        return countInv(arr, left, mid, temp) +
                countInv(arr, mid, right, temp) +
                countSplitInv(arr, left, mid, right, temp);
    }

    private int countSplitInv(int[] arr, int left, int mid, int right, int[] temp) {
        int firstIter = 0;
        int secondIter = 0;
        int countInv = 0;

        while (left + firstIter < mid && mid + secondIter < right) {
            if (arr[left + firstIter] < arr[mid + secondIter]) {
                temp[firstIter + secondIter] = arr[left + firstIter];
                firstIter++;
                continue;
            }
            temp[firstIter + secondIter] = arr[mid + secondIter];
            secondIter++;
            countInv += mid - left - firstIter;
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
        return countInv;
    }

    private void solve(int[] arr) {
        int count = arr.length;
        int[] temp = new int[count];
        System.out.println(countInv(arr, 0, count, temp));
    }
}
