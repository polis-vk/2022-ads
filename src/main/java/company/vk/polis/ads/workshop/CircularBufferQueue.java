package company.vk.polis.ads.workshop;

import java.util.AbstractQueue;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Queue;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

/**
 * Queue implementation based on fixed size circular buffer.
 *
 * @param <E> type of elements
 */
public final class CircularBufferQueue<E> extends AbstractQueue<E> implements Queue<E> {
    private final E[] array;
    int head = 0;
    int tail = 0;
    int size = 0;
    public CircularBufferQueue(int maxCapacity) {
        array = (E[]) new Object[maxCapacity];
    }

    @Contract(value = " -> new", pure = true)
    @Override
    public @NotNull Iterator<E> iterator() {
        return new CircularBufferIterator();
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean offer(E e) {

        if (size < array.length) {
            array[head] = e;
            head++;
            head %= array.length;
            size++;
            return true;
        }

        return false;
    }

    @Override
    public E poll() {
        E ans = null;
        if (size != 0) {
            ans = array[tail];
            tail++;
            tail %= array.length;
            size--;
        }

        return ans;

    }

    @Override
    public E peek() {
        E ans = null;
        if (size != 0) {
            ans = array[tail];
        }
        return ans;
    }

    private class CircularBufferIterator implements Iterator<E> {
        int size;
        int tail;

        public CircularBufferIterator() {
            size = CircularBufferQueue.this.size;
            tail = CircularBufferQueue.this.tail;
        }
        @Override
        public boolean hasNext() {
            return size != 0;
        }

        @Override
        public E next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            E ans = CircularBufferQueue.this.array[tail];
            tail += 1;
            tail %= CircularBufferQueue.this.array.length;
            return ans;
        }
    }
}
