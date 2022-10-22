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
    private int head;
    private int tail;
    private int size;
    private int modCounter;
    private final E[] array;

    public CircularBufferQueue(int maxCapacity) {
        array = (E[])new Object[maxCapacity];
    }

    @Override
    public Iterator<E> iterator() {
        return new CircularBufferQueueIterator();
    }

    private class CircularBufferQueueIterator implements Iterator<E>{
        private int currentIndex = head;
        private final int amountOfModifications = modCounter;

        @Override
        public boolean hasNext() {
            return currentIndex != tail && size != 0;
        }

        @Override
        public E next() {
            if (amountOfModifications != modCounter){
                throw new ConcurrentModificationException();
            }
            if (!hasNext()){
                throw new NoSuchElementException();
            }
            final var currentElement = array[currentIndex++];
            currentIndex %= array.length;
            return currentElement;
        }
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean offer(E e) {
        if (array.length == size){
            return false;
        }
        array[tail++] = e;
        size++;
        tail %= array.length;
        modCounter++;
        return true;

    }

    @Override
    public E poll() {
        if (isEmpty()){
            return null;
        }
        final var headElem = array[head++];
        size--;
        head %= array.length;
        modCounter++;
        return headElem;
    }

    @Override
    public E peek() {
        if (isEmpty()){
            return null;
        }
        return array[head];
    }
}
