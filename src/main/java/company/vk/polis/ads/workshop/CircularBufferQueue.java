package company.vk.polis.ads.workshop;

import java.util.AbstractQueue;
import java.util.Iterator;
import java.util.Queue;

/**
 * Queue implementation based on fixed size circular buffer.
 *
 * @param <E> type of elements
 */
public final class CircularBufferQueue<E> extends AbstractQueue<E> implements Queue<E> {
    public CircularBufferQueue(int maxCapacity) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Iterator<E> iterator() {
        throw new UnsupportedOperationException();
    }

    @Override
    public int size() {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean offer(E e) {
        throw new UnsupportedOperationException();

    }

    @Override
    public E poll() {
        throw new UnsupportedOperationException();

    }

    @Override
    public E peek() {
        throw new UnsupportedOperationException();
    }
}
