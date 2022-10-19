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

    private final E[] array;
    private int size;
    private int head;
    private int tail;

    @SuppressWarnings("unchecked")
    public CircularBufferQueue(int maxCapacity) {
        array = (E[]) new Object[maxCapacity];
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            int i = head; // Изначально указываем на голову очереди.

            @Override
            public boolean hasNext() {
                // Если указываем на хвост - значит очередь пуста.
                return i != tail && size != 0;
            }

            @Override
            public E next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }

                final var res = array[i];
                i++;
                i %= array.length; // Если вышли за границу - делаем оборот.

                return res;
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

        array[tail] = e; // Записываем в хвост.
        size++;
        tail++;
        tail %= array.length; // Если вышли за границу - делаем оборот.

        return true;
    }

    @Override
    public E poll() {
        if (isEmpty()) {
            return null;
        }

        final var el = array[head]; // Читаем с головы.
        head++;
        head %= array.length; // Если вышли за границу - делаем оборот.
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
