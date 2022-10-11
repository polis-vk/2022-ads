package company.vk.polis.ads;

class WrapperIterator<T extends Comparable<T>> implements Comparable<WrapperIterator<T>> {
    private int index;
    private T value;
    public WrapperIterator(int index, T value) {
        this.index = index;
        this.value = value;
    }

    @Override
    public int compareTo(WrapperIterator<T> o) {
        return value.compareTo(o.value);
    }
    public int getIndex() {
        return index;
    }
    public T getValue() {
        return value;
    }
}
