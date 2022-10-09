package company.vk.polis.ads.part3.nikitry;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Problem solution template.
 *
 * @author Dmitry Schitinin
 */
// https://www.eolymp.com/ru/submissions/11726981
public final class Median {

    private static void solve(final Scanner in, final PrintWriter out) {
        boolean even = false;
        UniversalHeap maxHeap = new UniversalHeap(false);
        UniversalHeap minHeap = new UniversalHeap(true);
        int median = 0;
        int lengthSubsequence = 0;
        int currentInput;
        while (in.hasNext()) {
            currentInput = in.nextInt();
            lengthSubsequence++;
            if (lengthSubsequence == 1) {
                median = currentInput;
                out.println(median);
                continue;
            }
            if (even) {
                if (median >= currentInput) {
                    maxHeap.insert(currentInput);
                    median = maxHeap.extract();
                } else {
                    minHeap.insert(currentInput);
                    median = minHeap.extract();
                }
            } else {
                if (currentInput >= median) {
                    minHeap.insert(currentInput);
                    maxHeap.insert(median);
                } else {
                    maxHeap.insert(currentInput);
                    minHeap.insert(median);
                }
                median = (Math.abs(minHeap.getRoot() + maxHeap.getRoot())) / 2;
            }
            out.println(median);
            even = !even;
        }
    }

    public static void main(final String[] arg) {
        final Scanner in = new Scanner(System.in);
        try (PrintWriter out = new PrintWriter(System.out)) {
            solve(in, out);
        }
    }
}

final class UniversalHeap {

    private final List<Integer> heap = new ArrayList<>();
    private final boolean flag; // true for minHeap

    public UniversalHeap(boolean flag) {
        this.flag = flag;
        heap.add(0);
    }

//    void showHeap() {
//        for (Integer integer : heap) {
//            System.out.print(integer + " ");
//        }
//    }

    private void sink(int k, int n) {
        while (2 * k <= n) {
            int j = 2 * k;
            if (flag) {
                if (j < n && heap.get(j) > heap.get(j + 1)) j++;
                if (heap.get(k) < heap.get(j)) break;
            } else {
                if (j < n && heap.get(j) < heap.get(j + 1)) j++;
                if (heap.get(k) > heap.get(j)) break;
            }
            swap(k, j);
            k = j;
        }
    }

    int getRoot() {
        return heap.get(1);
    }


    private void swap(int k, int j) {
        int temp = heap.get(k);
        heap.set(k, heap.get(j));
        heap.set(j, temp);
    }

    private void swim(int pos) {
        if (flag) {
            while (pos > 1 && heap.get(pos) < heap.get(pos / 2)) {
                swap(pos, pos / 2);
                pos = pos / 2;
            }
        } else {
            while (pos > 1 && heap.get(pos) > heap.get(pos / 2)) {
                swap(pos, pos / 2);
                pos = pos / 2;
            }
        }

    }

    void insert(int input) {
        heap.add(input);
        swim(heap.size() - 1);
    }

    int extract() {
        int max = heap.get(1);
        swap(1, size());
        heap.remove(size());
        sink(1, size());
        return max;
    }

    private int size() {
        return heap.size() - 1;
    }
}

