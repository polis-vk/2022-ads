package company.vk.polis.ads;

import java.util.Iterator;

public class Pair<T extends Comparable<T>> {

    private T value;
    private final Iterator<T> iterator;

    public Pair(T value, Iterator<T> iterator) {
        this.value = value;
        this.iterator = iterator;
    }

    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }

    public Iterator<T> getIterator() {
        return iterator;
    }
}
