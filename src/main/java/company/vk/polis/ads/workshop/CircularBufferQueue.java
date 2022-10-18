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

    private int size;
    private int head;
    private int tail;
    private E[] buffer;

    public CircularBufferQueue(int maxCapacity) {
        buffer = (E[]) new Object[maxCapacity];
        size = head = tail = 0;
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            private int considered = 0;
            @Override
            public boolean hasNext() {
                return considered < size;
            }

            @Override
            public E next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return buffer[(head + considered++) % buffer.length];
            }
        };
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean offer(E e) {
        if (size == buffer.length) {
            return false;
        }
        buffer[tail] = e;
        tail++;
        tail %= buffer.length;
        size++;
        return true;
    }

    @Override
    public E poll() {
        if (isEmpty()) {
            return null;
        }
        E element = buffer[head];
        head++;
        head %= buffer.length;
        size--;
        return element;
    }

    @Override
    public E peek() {
        if (isEmpty()) {
            return null;
        }
        return buffer[head];
    }
}
