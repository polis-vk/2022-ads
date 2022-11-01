package company.vk.polis.ads.workshop;

import java.util.*;

/**
 * Queue implementation based on fixed size circular buffer.
 *
 * @param <E> type of elements
 */
public final class CircularBufferQueue<E> extends AbstractQueue<E> implements Queue<E> {
    private E[] array;
    private int size;

    private int head;
    private int tail;

    private int modCnt;

    public CircularBufferQueue(int maxCapacity) {
        array = (E[]) new Object[maxCapacity];
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            final int fixedModCnt = modCnt;
            int position = head;

            @Override
            public boolean hasNext() {
                return position != tail && size != 0;
            }

            @Override
            public E next() {
                if (fixedModCnt != modCnt) {
                    throw new ConcurrentModificationException();
                }

                if (!hasNext()) {
                    throw new NoSuchElementException();
                }

                var element = array[position++];
                position %= array.length;
                return element;
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

        array[tail++] = e;
        tail %= array.length;
        modCnt++;
        return true;
    }

    @Override
    public E poll() {
        if (isEmpty()) {
            return null;
        }

        final var element = array[head++];
        head %= array.length;
        size--;
        modCnt++;
        return element;
    }

    @Override
    public E peek() {
        if (isEmpty()) {
            return null;
        }

        return array[head];
    }
}