import java.io.*;
import java.util.StringTokenizer;

public class Median2 {



    private abstract static class Heap {
        private int[] arr;
        private int size;

        public Heap(int size){
            this.arr = new int[size * 2];
            this.size = 0;
        }

        public int size(){
            return size;
        }
        private void swap(int a, int b){
            int tmp = arr[a];
            arr[a] = arr[b];
            arr[b] = tmp;
        }

        abstract void swim(int k);

        abstract void sink(int k);

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
    }



    private static class MaxHeap extends Heap {

        public  MaxHeap(int size) {
            super(size);
        }

        @Override
        void swim(int k) {
            while (k > 1 && super.arr[k] > super.arr[k / 2]){
                super.swap(k, k / 2);
                k /= 2;
            }
        }

        @Override
        void sink(int k) {
            while (2 * k <= super.size()){
                int j = 2 * k;

                if (j < super.size && super.arr[j] < super.arr[j + 1]) {
                    j++;
                }
                if (super.arr[k] >= super.arr[j]) {
                    break;
                }

                super.swap(k, j);
                k = j;
            }
        }
    }


    private static class MinHeap extends Heap {

        public MinHeap(int size) {
            super(size);
        }

        @Override
        void swim(int k) {
            while (k > 1 && super.arr[k] < super.arr[k / 2]){
                super.swap(k, k / 2);
                k /= 2;
            }
        }

        @Override
        void sink(int k) {
            while (2 * k <= super.size()){
                int j = 2 * k;

                if (j < super.size && super.arr[j] > super.arr[j + 1]) {
                    j++;
                }
                if (super.arr[k] <= super.arr[j]) {
                    break;
                }

                super.swap(k, j);
                k = j;
            }

        }

    }



    private static void solve(final FastScanner in, final PrintWriter out) {
        Heap minHeap = new MinHeap(1000000);
        Heap maxHeap = new MaxHeap(1000000);

        int value;

        while (true) {
            try {
                value = in.nextInt();
            } catch (NullPointerException e) {
                break;
            }

            if (minHeap.size == maxHeap.size) {
                maxHeap.insert(value);
                minHeap.insert(maxHeap.extract());
            } else {
                minHeap.insert(value);
                maxHeap.insert(minHeap.extract());
            }

            int median;

            if (minHeap.size > maxHeap.size){
                median = minHeap.arr[1];
            } else {
                median = (minHeap.arr[1] + maxHeap.arr[1]) / 2;
            }
            out.println(median);



        }

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
