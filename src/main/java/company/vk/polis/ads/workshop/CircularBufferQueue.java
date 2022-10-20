package company.vk.polis.ads.workshop;

import java.util.AbstractQueue;
import java.util.EmptyStackException;
import java.util.Iterator;
import java.util.Queue;

import javax.swing.text.html.HTMLDocument;

/**
 * Queue implementation based on fixed size circular buffer.
 *
 * @param <E> type of elements
 */
public final class CircularBufferQueue<E> extends AbstractQueue<E> implements Queue<E> {

    E[] arr;
    int start;
    int end;

    public CircularBufferQueue(int maxCapacity) {
        arr = (E[]) new Object[maxCapacity + 1];
        start = 0;
        end = 0;
    }

    @Override
    public Iterator<E> iterator() {
       return new QueueIterator();
    }

    private class QueueIterator implements Iterator<E> {

        private int inx = start;
        @Override
        public boolean hasNext() {
            return inx != end;
        }

        @Override
        public E next() {
            E tmp = arr[inx];
            inx = (inx + 1) % arr.length;
            return tmp;
        }
    }

    @Override
    public int size() {
        if (end >= start) {
            return end - start;
        } else {
            return arr.length - start + end;
        }
    }

    @Override
    public boolean offer(E e) {
        if (size() != (arr.length - 1)) {
            arr[end] = e;
            end = (end + 1) % arr.length;
            return true;
        }
        return false;
    }

    @Override
    public E poll() {
        if (size() == 0) {
            throw new EmptyStackException();
        }
        E tmp = peek();
        start = (start + 1) % arr.length;
        return tmp;
    }

    @Override
    public E peek() {
        return arr[start];
    }

}
