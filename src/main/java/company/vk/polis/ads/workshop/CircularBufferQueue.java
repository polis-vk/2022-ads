package company.vk.polis.ads.workshop;

import java.util.AbstractQueue;
import java.util.Iterator;
import java.util.Queue;
import java.util.NoSuchElementException;

/**
 * Queue implementation based on fixed size circular buffer.
 *
 * @param <E> type of elements
 */
public final class CircularBufferQueue<E> extends AbstractQueue<E> implements Queue<E> {
    private final E[] dataElement;
    private int size;
    private int tail;
    private int head;

    public CircularBufferQueue(int maxCapacity) {
        dataElement = (E[]) new Object[maxCapacity];
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            int i = head;

            @Override
            public boolean hasNext() {
                return i != tail && size != 0;
            }

            @Override
            public E next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                final E elem = dataElement[i];
                i++;
                i %= dataElement.length;
                return elem;
            }
        };
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean offer(E e) {
        if (size == dataElement.length) {
            return false;
        }
        dataElement[tail] = e;
        size++;
        tail++;
        tail %= dataElement.length;
        return true;
    }

    @Override
    public E poll() {
        if (isEmpty()) {
            return null;
        }
        final E elem = dataElement[head];
        size--;
        head++;
        head %= dataElement.length;
        return elem;
    }

    @Override
    public E peek() {
        if (isEmpty()) {
            return null;
        }
        return dataElement[head];
    }
}

