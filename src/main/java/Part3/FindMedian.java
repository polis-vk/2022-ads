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
        private final Heap maxHeap;
        private final Heap minHeap;
        private int counter;
        private int median;

        public Median(){
            maxHeap = new Heap(new Increasing());
            minHeap = new Heap(new Decreasing());
            counter = 0;
            median = -1;
        }

        public void insert(int value){
            counter++;
            if (isOdd(counter)){
                if (value > median){
                    minHeap.insert(value);
                    median = minHeap.extract();
                }
                else {
                    maxHeap.insert(value);
                    median = maxHeap.extract();
                }
            }
            else {
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
        }

        public int getMedian(){
            return median;
        }

        private boolean isOdd(int item){
            return item % 2 == 1;
        }
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        Median result = new Median();
        while (in.hasNextInt()){
            result.insert(in.nextInt());
            System.out.println(result.getMedian());
        }
    }
}