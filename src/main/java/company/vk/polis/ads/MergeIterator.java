package company.vk.polis.ads;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * Iterator that merges k input iterators ordered ascending.
 * Each value returned by #next() is greater than or equal to previous one.
 * Total cost of iteration is O(n logk).
 *
 * @param <T> type of elements
 */
public final class MergeIterator<T extends Comparable<T>> implements Iterator<T> {
    private final List<Iterator<T>> iterators;
    private MyHeap heap;
    private boolean isEnd;
    private class pairValue {
        T value;
        Iterator<T> iteratorFrom;
        pairValue(T value , Iterator<T> iteratorFrom) {
            this.value = value;
            this.iteratorFrom = iteratorFrom;
        }
    }

    private class MyHeap {
        private ArrayList<pairValue> values;
        public int size;

        MyHeap() {
            this.values = new ArrayList<pairValue>();
            values.add(new pairValue(null, null));
            this.size = 0;
        }
        void swim(int k) {
            while(k > 1 && (values.get(k).value.compareTo(values.get(k/2).value)) < 0 ? true : false) {
                swap(values, k , k/2);
                k = k / 2;
            }
        }
        void sink(int k) {
            while (2 * k <= size) {
                int j = 2 * k;
                if(j < size && ( values.get(j).value.compareTo(values.get(j + 1).value)) > 0 ? true : false) j++;
                if((values.get(k).value.compareTo(values.get(j).value)) <= 0 ? true : false) {
                    break;
                }
                swap(values, k, j);
                k = j;
            }
        }
        void insert(pairValue v) {

            values.add(++size, v);
            swim(size);
        }
        pairValue extract() {
            pairValue max = values.get(1);
            swap(values, 1, size--);
            sink(1);
            return max;
        }

        void swap(ArrayList<pairValue> array, int son, int father) {
            pairValue temp = array.get(son);
            array.set(son, array.get(father));
            array.set(father, temp);
        }


    }

    /**
     * Constructor
     *
     * @param iterators k input iterators of ascending ordered elements
     */
    public MergeIterator(List<Iterator<T>> iterators) {
        this.iterators = iterators;
        this.heap = new MyHeap();
        for (Iterator<T> iter:
             iterators) {
            if(iter.hasNext()) {
                heap.insert(new pairValue(iter.next(), iter));
            }
        }
    }

    @Override
    public boolean hasNext() {
        return heap.size > 0;
    }


    /**
     * Returns next element in ascending order with O(log k) complexity
     *
     * @return next
     */
    @Override
    public T next() {
        if(!hasNext()) {
            throw new NoSuchElementException();
        }
        pairValue result = heap.extract();
        Iterator<T> currentIter = result.iteratorFrom;
        if(currentIter.hasNext()) {
            heap.insert(new pairValue(currentIter.next(), currentIter));
        } else {
            for (Iterator<T> iter : iterators) {
                if (iter.hasNext()) {
                    heap.insert(new pairValue(iter.next(), iter));
                    break;
                }
            }
        }
        return result.value;
    }
}
