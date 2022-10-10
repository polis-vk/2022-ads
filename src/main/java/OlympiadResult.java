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

    private void MergeSort(Result[] arr, int left, int right, Result[] temp) {
        if (left + 1 >= right) {
            return;
        }
        int mid = (left + right) / 2;
        MergeSort(arr, left, mid, temp);
        MergeSort(arr, mid, right, temp);
        merge(arr, left, mid, right, temp);
    }

    private void merge(Result[] arr, int left, int mid, int right, Result[] temp) {
        int firstIter = 0;
        int secondIter = 0;

        while (left + firstIter < mid && mid + secondIter < right) {
            if (arr[left + firstIter].compareTo(arr[mid + secondIter]) >= 0) {
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

    private void solve(Result[] results) {
        Result[] tempArr = new Result[results.length];
        MergeSort(results, 0, results.length, tempArr);
        for (Result result : results) {
            System.out.println(result.id + " " + result.score);
        }
    }
}
