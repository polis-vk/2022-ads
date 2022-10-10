package Part3;

import java.util.Comparator;
import java.util.Scanner;

public class Hipuy {
    private static class Increasing implements Comparator<Integer> {

        @Override
        public int compare(Integer o1, Integer o2) {
            return o1 - o2;
        }
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        Heap myHeap = new Heap(n, new Increasing());
        for (int i = 0; i < n; i++){
            switch (in.nextInt()) {
                case 0 -> myHeap.insert(in.nextInt());
                case 1 -> System.out.println(myHeap.extract());
            }
        }
    }
}
