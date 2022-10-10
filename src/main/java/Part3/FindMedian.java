package Part3;

import java.util.Comparator;
import java.util.Scanner;

public class FindMedian {
    private static class Increasing implements Comparator<Integer>{

        @Override
        public int compare(Integer o1, Integer o2) {
            return o1 - o2;
        }
    }

    private static class Decreasing implements Comparator<Integer>{

        @Override
        public int compare(Integer o1, Integer o2) {
            return o2 - o1;
        }
    }

    private static class Median {
        private final Heap maxHeap = new Heap(new Increasing());
        private final Heap minHeap = new Heap(new Decreasing());
        private boolean medianIsUsed = false;
        private int median = -1;

        public int getNextMedian(int value){
            if (!medianIsUsed){
                if (value > median){
                    minHeap.insert(value);
                    median = minHeap.extract();
                }
                else {
                    maxHeap.insert(value);
                    median = maxHeap.extract();
                }
                medianIsUsed = true;
            }
            else {
                medianIsUsed = false;
                if (value > median){
                    minHeap.insert(value);
                    maxHeap.insert(median);
                }
                else {
                    minHeap.insert(median);
                    maxHeap.insert(value);
                }
                median = (maxHeap.peek() + minHeap.peek()) / 2;
            }
            return median;
        }
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        Median result = new Median();
        while (in.hasNextInt()){
            System.out.println(result.getNextMedian(in.nextInt()));
        }
    }
}