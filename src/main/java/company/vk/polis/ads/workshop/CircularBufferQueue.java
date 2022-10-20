package company.vk.polis.ads.workshop;

import java.util.AbstractQueue;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Queue;

/**
 * Queue implementation based on fixed size circular buffer.
 *
 * @param <E> type of elements
 */
public final class CircularBufferQueue<E> extends AbstractQueue<E> implements Queue<E> {

    private final E[] data;

    private int size;

    private int head;
    private int tail;

    private int modCount;

    @SuppressWarnings("unchecked")
    public CircularBufferQueue(int maxCapacity) {
        data = (E[]) new Object[maxCapacity];
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<>() {

            private int head = CircularBufferQueue.this.head;
            private int currentSize = size;
            private final int fixedModCount = modCount;

            @Override
            public boolean hasNext() {
                if (fixedModCount != modCount) {
                    throw new ConcurrentModificationException("Don't modify array while iterating!");
                }
                return currentSize != 0;
            }

            @Override
            public E next() {
                if (hasNext()) {
                    E element = data[head];
                    head++;
                    head %= data.length;
                    currentSize--;
                    return element;
                }
                throw new NoSuchElementException("There are no more elements left!");
            }
        };
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean offer(E e) {
        if (size == data.length) {
            return false;
        }

        data[tail] = e;
        tail++;
        tail %= data.length;
        size++;
        modCount++;
        return true;
    }

    @Override
    public E poll() {
        if (size == 0) {
            return null;
        }
        E element = data[head];
        head++;
        head %= data.length;
        size--;
        modCount++;
        return element;
    }

    @Override
    public E peek() {
        if (size == 0) {
            return null;
        }
        return data[head];
    }
}
