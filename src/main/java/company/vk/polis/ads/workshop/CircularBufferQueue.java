package company.vk.polis.ads.workshop;

import java.util.AbstractQueue;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Queue;

/**
 * Queue implementation based on fixed size circular buffer.
 *
 * @param <E> type of elements
 */
public final class CircularBufferQueue<E> extends AbstractQueue<E> implements Queue<E> {
    private final E[] array ;
    private int size;
    private int tail = -1;
    private int head;

    @SuppressWarnings("unchecked")
    public CircularBufferQueue(int maxCapacity) {
        array = (E[]) new Object[maxCapacity];
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<>() {
            private int iterHead = head;
            @Override
            public boolean hasNext() {
                return iterHead <= tail;
            }

            @Override
            public E next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                var el = array[iterHead];
                iterHead++;
                iterHead %= array.length;
                return el;
            }
        };
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean offer(E e) {
        if (size == array.length) {
            return false;
        }
        size++;
        tail++;
        tail %= array.length;
        array[tail] = e;
        return true;
    }

    @Override
    public E poll() {
        if (isEmpty()) {
            return null;
        }
        var el = array[head];
        head++;
        head %= array.length;
        size--;
        return el;
    }

    @Override
    public E peek() {
        if (isEmpty()) {
            return null;
        }
        return array[head];
    }
}
