package company.vk.polis.ads.workshop;

import java.util.AbstractQueue;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Queue;

import javax.print.DocFlavor;

/**
 * Queue implementation based on fixed size circular buffer.
 *
 * @param <E> type of elements
 */
public final class CircularBufferQueue<E> extends AbstractQueue<E> implements Queue<E> {
    private final Object[] array;
    private int size;
    private int head;
    private int tail;

    public CircularBufferQueue(int maxCapacity) {
        array = new Object[maxCapacity];
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            int currentIndex = head;

            @Override
            public boolean hasNext() {
                return currentIndex < tail && size != 0 ;
            }

            @Override
            public E next() {
                if (!hasNext()){
                    throw new NoSuchElementException();
                }
                final var el =  (E) array[currentIndex];
                currentIndex++;
                currentIndex %= array.length;
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
        array[tail] = e;
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
        head++;
        head %= array.length;
        size--;
        return (E) el;
    }

    @Override
    public E peek() {
        if (isEmpty()) {
            return null;
        }
        return (E) array[head];
    }
}
