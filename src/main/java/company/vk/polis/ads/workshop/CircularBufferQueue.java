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

    private final E[] array;

    private int size;
    private int modCount;
    private int head;
    private int tail;

    @SuppressWarnings("unchecked")
    public CircularBufferQueue(int maxCapacity) {
        this.array = (E[]) new Object[maxCapacity];
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            private final int fixedModCount = modCount;
            private int pointer = head;

            @Override
            public boolean hasNext() {
                if (fixedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                return tail != head && size > 0;
            }

            @Override
            public E next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                E el = array[pointer];
                pointer++;
                pointer %= array.length;
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
        array[tail] = e;
        size++;
        modCount++;
        tail++;
        tail %= array.length;
        return true;

    }

    @Override
    public E poll() {
        if (isEmpty()) {
            return null;
        }
        final var el = array[head];
        size--;
        modCount++;
        head++;
        head %= array.length;
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
