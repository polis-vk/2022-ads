package Part3;

import java.util.Scanner;

public class HeapSort {
    private static void swap(int[] data, int index1, int index2){
        int copy = data[index1];
        data[index1] = data[index2];
        data[index2] = copy;
    }

    private static void sink(int[] heap, int indexStart, int indexEnd){
        while(getLeftChild(indexStart) <= indexEnd){
            int j = getLeftChild(indexStart);
            if(j < indexEnd && heap[j] < heap[j + 1]){
                j++;
            }
            if (heap[indexStart] >= heap[j]){
                break;
            }
            swap(heap, indexStart, j);
            indexStart = j;
        }
    }

    private static void makeHeap(int [] data){
        final int endIndex = data.length - 1;
        for (int k = getParent(endIndex); k >= 1; k--){
            sink(data, k, endIndex);
        }
    }

    private static int getParent(int indexOfChild){
        return indexOfChild / 2;
    }

    private static int getLeftChild(int indexOfParent){
        return 2 * indexOfParent;
    }

    private static void heapSort(int[] data){
        makeHeap(data);
        int endIndex = data.length - 1;
        while (endIndex > 1){
            swap(data, 1, endIndex--);
            sink(data, 1, endIndex);
        }
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int[] data = new int[n + 1];
        for (int i = 1; i < n + 1; i++){
            data[i] = in.nextInt();
        }
        heapSort(data);
        boolean flag = false;
        for (int i = 1; i < data.length; i++) {
            if (flag){
                System.out.print(" ");
            }
            flag = true;
            System.out.print(data[i]);
        }
    }
}
