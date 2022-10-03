public class OlympiadResult {
    public static class Result implements Comparable<Result> {
        int id;
        int score;

        Result(int id, int score) {
            this.id = id;
            this.score = score;
        }

        @Override
        public int compareTo(Result o) {
            if (this.score < o.score) {
                return -1;
            } else if (this.score == o.score) {
                return -Integer.compare(this.id, o.id);
            } else {
                return 1;
            }
        }
    }

    private static void MergeSort(Result[] arr, int left, int right) {
        if (left + 1 >= right) {
            return;
        }
        int mid = (left + right) / 2;
        MergeSort(arr, left, mid);
        MergeSort(arr, mid, right);
        merge(arr, left, mid, right);
    }

    private static void merge(Result[] arr, int left, int mid, int right) {
        int firstIter = 0;
        int secondIter = 0;
        Result[] mergeResult = new Result[right - left];

        while (left + firstIter < mid && mid + secondIter < right) {
            if (arr[left + firstIter].compareTo(arr[mid + secondIter]) >= 0) {
                mergeResult[firstIter + secondIter] = arr[left + firstIter];
                firstIter++;
                continue;
            }
            mergeResult[firstIter + secondIter] = arr[mid + secondIter];
            secondIter++;
        }

        while (left + firstIter < mid) {
            mergeResult[firstIter + secondIter] = arr[left + firstIter];
            firstIter++;
        }

        while (mid + secondIter < right) {
            mergeResult[firstIter + secondIter] = arr[mid + secondIter];
            secondIter++;
        }

        System.arraycopy(mergeResult, 0, arr, left, firstIter + secondIter);
    }

    private static void solve(Result[] results) {
        MergeSort(results, 0, results.length);
        for (Result result : results) {
            System.out.println(result.id + " " + result.score);
        }
    }
}
