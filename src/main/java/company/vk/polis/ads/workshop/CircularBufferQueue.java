package company.vk.polis.ads.workshop;

import java.util.AbstractQueue;
import java.util.EmptyStackException;
import java.util.Iterator;
import java.util.Queue;

/**
 * Queue implementation based on fixed size circular buffer.
 *
 * @param <E> type of elements
 */
public final class CircularBufferQueue<E> extends AbstractQueue<E> implements Queue<E> {

    private final E[] array_;
    private int head;
    private int tail;

    public CircularBufferQueue(int maxCapacity) {
        array_ = (E[]) new Object[maxCapacity + 1];
        head = 0;
        tail = 0;
    }

    private class IteratorQueue implements Iterator<E>{
        private int currentElement = head;

        @Override
        public boolean hasNext() {
            return currentElement != tail;
        }

        @Override
        public E next() {
            E element = array_[currentElement];
            currentElement = (currentElement + 1) % array_.length;
            return element;
        }
    }

    @Override
    public Iterator<E> iterator() {
        return new IteratorQueue();
    }

    @Override
    public int size() {
        if(tail >= head){
            return tail - head;
        }
        return array_.length - head + tail;
    }

    @Override
    public boolean offer(E e) {
        if(size() != (array_.length - 1)){
            array_[tail] = e;
            tail = (tail + 1) % array_.length;
            return true;
        }
        return false;
    }

    @Override
    public E poll() {
        if(size() == 0){
            throw new EmptyStackException();
        }
        E element = peek();
        head = (head + 1) % array_.length;
        return element;
    }

    @Override
    public E peek() {
        return array_[head];
    }
}
