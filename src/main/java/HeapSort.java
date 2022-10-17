import java.io.*;
import java.util.StringTokenizer;

public class HeapSort {


    private static class IntHeap {
        private int[] arr;
        private int size;

        public IntHeap(int size){
            this.arr = new int[size * 2];
        }

        public int size(){
            return size;
        }
        private  void swap(int a, int b){
            int tmp = arr[a];
            arr[a] = arr[b];
            arr[b] = tmp;
        }

        private void swim(int k) {
            while (k > 1 && arr[k] > arr[k / 2]) {
                swap(k, k / 2);
                k /= 2;
            }
        }

        private void sink(int k){
            while (2 * k <= size){
                int j = 2 * k;

                if (j < size && arr[j] < arr[j + 1]) {
                    j++;
                }
                if (arr[k] >= arr[j]) {
                    break;
                }

                swap(k, j);
                k = j;
            }
        }

        public void insert(int k) {
            arr[++size] = k;
            swim(size);
        }

        public int extract(){
            int max = arr[1];
            swap(1, size--);
            sink(1);
            return max;

        }
        private static void heapSort(int[] arr) {
            IntHeap heap = new IntHeap(arr.length);
            heap.arr = arr;
            heap.size = arr.length - 1;


            for (int i = arr.length / 2; i >= 1; i--) {
                    heap.sink(i - 1);
            }

            while (heap.size > 0){
                heap.swap(0, heap.size--);
                heap.sink(0);
            }
        }
    }
    private static void solve(final FastScanner in, final PrintWriter out) {


        int[] arr = new int[in.nextInt()];

        for (int i = 0; i < arr.length; i++) {
            arr[i] = in.nextInt();
        }

        IntHeap.heapSort(arr);

        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < arr.length; i ++) {
            sb.append(arr[i]).append(" ");
        }

        out.println(sb);
    }


    private static final class FastScanner {
        private final BufferedReader reader;
        private StringTokenizer tokenizer;

        FastScanner(final InputStream in) {
            reader = new BufferedReader(new InputStreamReader(in));
        }

        String next() {
            while (tokenizer == null || !tokenizer.hasMoreTokens()) {
                try {
                    tokenizer = new StringTokenizer(reader.readLine());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return tokenizer.nextToken();
        }

        int nextInt() {
            return Integer.parseInt(next());
        }
    }

    public static void main(final String[] arg) {
        final FastScanner in = new FastScanner(System.in);
        try (PrintWriter out = new PrintWriter(System.out)) {
            solve(in, out);
        }
    }




}
