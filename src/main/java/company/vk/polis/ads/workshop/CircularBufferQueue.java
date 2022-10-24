package company.vk.polis.ads.workshop;

import java.util.*;

/**
 * Queue implementation based on fixed size circular buffer.
 *
 * @param <E> type of elements
 */
public final class CircularBufferQueue<E> extends AbstractQueue<E> implements Queue<E> {
    private final E[] array;
    private final int maxCapacity;
    private int size;
    private int first;
    private int last;
    private int modCount;

    @SuppressWarnings("unchecked")
    public CircularBufferQueue(int maxCapacity) {
        this.maxCapacity = maxCapacity;
        this.array = (E[]) new Object[maxCapacity];
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<>() {
            private int pos = first;
            private int iterCount;
            private final int fixedModCount = modCount;

            @Override
            public boolean hasNext() {
                return iterCount < size;
            }

            @Override
            public E next() {
                if (fixedModCount != modCount) {
                    throw new ConcurrentModificationException();
                } else if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                E currElem = array[pos++];
                pos %= maxCapacity;
                iterCount++;
                return currElem;
            }
        };
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean offer(E e) {
        if (size > 0 && first == last) {
            return false;
        }
        array[last++] = e;
        last %= maxCapacity;
        size++;
        modCount++;
        return true;
    }

    @Override
    public E poll() {
        if (size == 0) {
            throw new IllegalArgumentException();
        }
        E currElem = array[first++];
        first %= maxCapacity;
        size--;
        modCount++;
        return currElem;
    }

    @Override
    public E peek() {
        if (size == 0) {
            throw new IllegalArgumentException();
        }
        return array[first];
    }
}
