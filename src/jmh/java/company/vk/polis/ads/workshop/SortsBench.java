package company.vk.polis.ads.workshop;

import java.util.Arrays;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.IntStream;

public class SortsBench {

    public static void main(String[] args) {
        Integer[] arr = IntStream.generate(() -> ThreadLocalRandom.current().nextInt()).limit(100).boxed().toArray(Integer[]::new);
        System.out.println(Arrays.toString(arr));
        HeapSort.sort(arr);
        System.out.println(Arrays.toString(arr));
        for (int i = 0; i < arr.length - 1; i++) {
            if (arr[i] > arr[i + 1]) {
                System.out.println("NOT SORTED in " + i);
            }
        }
    }

}
