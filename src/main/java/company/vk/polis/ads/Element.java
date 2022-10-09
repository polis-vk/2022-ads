package company.vk.polis.ads;

public class Element<T extends Comparable<T>> implements Comparable<Element<T>> {
    private T value;
    private int sourceIndex;


    public Element(T val, int sourceIndex) {
        this.value = val;
        this.sourceIndex = sourceIndex;
    }

    @Override
    public int compareTo(Element<T> t) {
        return value.compareTo(t.value);
    }

    public int getSourceIndex(){
        return sourceIndex;
    }

    public T getValue(){
        return value;
    }
}
