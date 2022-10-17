package company.vk.polis.ads;

import java.util.Arrays;

public class MinHeap<T extends  Comparable<T>> {
        private T[] arr;
        private int size;

        public MinHeap(int size){
            this.arr = (T[]) new Comparable[size * 2];
            this.size = 0;
        }

        public int size(){
            return size;
        }
        private void swap(int a, int b){
            T tmp = arr[a];
            arr[a] = arr[b];
            arr[b] = tmp;
        }

    void swim(int k) {
        while (k > 1 && arr[k].compareTo(arr[k / 2]) < 0){
            swap(k, k / 2);
            k /= 2;
        }
    }


    void sink(int k) {
        while (2 * k <= size()){
            int j = 2 * k;

            if (j < size && arr[j].compareTo(arr[j + 1]) > 0) {
                j++;
            }
            if (arr[k].compareTo( arr[j]) < 1) {
                break;
            }

            swap(k, j);
            k = j;
        }

    }

        public void insert(T k) {
            if (size == arr.length - 1) {
                arr = Arrays.copyOf(arr, arr.length * 3 / 2);
            }
            arr[++size] = k;
            swim(size);
        }

        public T extract(){
            T max = arr[1];
            swap(1, size--);
            sink(1);
            return max;

        }

        public T first(){
            return arr[1];
        }
    }

