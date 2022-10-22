package company.vk.polis.ads.workshop;

@SuppressWarnings("unchecked")
public class PyramidSort {
    public static <E extends Comparable<E>> E[] sort(E[] data) {
        E[] array = (E[]) new Comparable[data.length + 1];
        System.arraycopy(data, 0, array, 1, data.length);
        heapSort(array);
        System.arraycopy(array, 1, data, 0, data.length);
        return data;
    }

    private static <E extends Comparable<E>> void swap(E[] data, int index1, int index2){
        E copy = data[index1];
        data[index1] = data[index2];
        data[index2] = copy;
    }

    private static <E extends Comparable<E>> void sink(E[] heap, int indexStart, int indexEnd){
        while(getLeftChild(indexStart) <= indexEnd){
            int j = getLeftChild(indexStart);
            if(j < indexEnd && heap[j].compareTo(heap[j + 1]) < 0){
                j++;
            }
            if (heap[indexStart].compareTo(heap[j]) >= 0){
                break;
            }
            swap(heap, indexStart, j);
            indexStart = j;
        }
    }

    private static <E extends Comparable<E>> void makeHeap(E [] data){
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

    private static <E extends Comparable<E>> void heapSort(E[] data){
        makeHeap(data);
        int endIndex = data.length - 1;
        while (endIndex > 1){
            swap(data, 1, endIndex--);
            sink(data, 1, endIndex);
        }
    }
}

