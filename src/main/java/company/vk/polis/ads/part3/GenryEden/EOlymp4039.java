package company.vk.polis.ads.part3.GenryEden;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.NoSuchElementException;
import java.util.StringTokenizer;

/**
 * Problem solution template.
 *
 * @author Dmitry Schitinin
 *
 */

// submission url: https://www.eolymp.com/ru/submissions/11714138
public final class EOlymp4039 {
    private EOlymp4039() {
        // Should not be instantiated
    }

    private static void solve(final FastScanner in, final PrintWriter out) {
        // Write me
        int n = in.nextInt();
        MaxHeap heap = new MaxHeap(n);
        for (int i = 0; i < n; i++){
            int cmd = in.nextInt();
            if (cmd == 0){
                int value = in.nextInt();
                heap.add(value);
            } else {
                out.println(heap.pop());
            }
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

class MaxHeap{
    public static final int DEFAULT_SIZE = 64;
    private int[] arr;
    private int lastPointer;


    public MaxHeap(){
        this(DEFAULT_SIZE);
    }


    public MaxHeap(int size) {
        this.arr = new int[size];
        this.lastPointer = -1;
    }

    public void add(int value){
        if (lastPointer >= arr.length - 1){
            this.resize();
        }

        arr[++lastPointer] = value;

        swim(lastPointer);
    }

    public int pop() {
        if (lastPointer == -1){
            throw new NoSuchElementException();
        }

        int ans = arr[0];

        arr[0] = arr[lastPointer--];

        sink(0);

        return ans;
    }

    public int length(){
        return lastPointer + 1;
    }

    public boolean isEmpty (){
        return lastPointer == -1;
    }

    private void sink(int index){
        int leftChildIndex = index * 2 + 1;
        int rightChildIndex = index * 2 + 2;

        if (leftChildIndex <= lastPointer && arr[leftChildIndex] > arr[index] && arr[leftChildIndex] > arr[rightChildIndex]){
            swap(leftChildIndex, index);
            sink(leftChildIndex);
        } else if (rightChildIndex <= lastPointer && arr[rightChildIndex] > arr[index]) {
            swap(rightChildIndex, index);
            sink(rightChildIndex);
        }
    }

    private void swim(int index){
        int parentIndex = (index - 1) / 2;

        if (arr[index] > arr[parentIndex])  {
            swap(index, parentIndex);
            swim(parentIndex);
        }


    }

    private void swap(int index1, int index2){
        int tmp = arr[index1];
        arr[index1] = arr[index2];
        arr[index2] = tmp;
    }
    private void resize(){
        arr = Arrays.copyOf(arr, arr.length * 2);
    }
}