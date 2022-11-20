package company.vk.polis.ads.hash;

import java.util.function.BiConsumer;

import org.jetbrains.annotations.Nullable;

/**
 * Map implementation with separate chaining collision resolution approach
 *
 * @param <K> key
 * @param <V> value
 */
public final class SeparateChainingMap<K, V> implements Map<K, V> {
    // Do not edit this field!!!
    private Node<K, V>[] array;
    private int size;
    private int capacity;
    private final float loadFactor;


    /**
     * Создает новый ассоциативный массив в соответствии с expectedMaxSize и loadFactor.
     * Сразу выделяет начальное количество памяти на основе expectedMaxSize и loadFactor.
     *
     * @param expectedMaxSize ожидаемое максимальное количество элементов в ассоциативном массие.
     *                        Это значит, что capacity - размер массива связных списков -
     *                        не будет увеличиваться до тех пор, пока количество элементов
     *                        не станет больше чем expectedMaxSize
     * @param loadFactor      отношение количества элементов к размеру массива связных списков
     */
    public SeparateChainingMap(int expectedMaxSize, float loadFactor) {
        capacity = (int) (expectedMaxSize / loadFactor);
        array = allocate(capacity);
        this.loadFactor = loadFactor;
        size = 0;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean containsKey(K key) {
        return get(key) != null;
    }

    @Nullable
    @Override
    public V get(K key) {
        int inx = getInxOnKey(key);
        var tmp = array[inx];
        while (tmp != null) {
            if (tmp.key.equals(key)) {
                return tmp.value;
            }
            tmp = tmp.next;
        }
        return null;
    }

    /**
     * Если capacity * loadFactor == size() и будет добавлен новый ключ,
     * то нужно выполнить расширение массивов
     */
    @Nullable
    @Override
    public V put(K key, V value) {
        if (Math.ceil(capacity * loadFactor) == size) {
            array = doublingCapacity();
        }
        int inx = getInxOnKey(key);
        var start = array[inx];
        if (start == null) {
            array[inx] = new Node<>(key, value);
        } else {
            while (start.next != null) {
                if (start.key.equals(key)) {
                    V val = start.value;
                    start.value = value;
                    return val;
                }
                start = start.next;
            }
            if (start.key.equals(key)) {
                V val = start.value;
                start.value = value;
                return val;
            }
            start.next = new Node<>(key, value);
        }
        size++;
        return null;
    }

    @Nullable
    @Override
    public V remove(K key) {
        int inx = getInxOnKey(key);
        Node<K, V> prev = null;
        var tmp = array[inx];
        while (tmp != null) {
            if (tmp.key.equals(key)) {
                if (tmp.next != null) {
                    tmp.next.prev = tmp.prev;
                }
                if (prev == null) {
                    array[inx] = tmp.next;
                } else {
                    prev.next = tmp.next;
                }
                size--;
                return tmp.value;
            }
            prev = tmp;
            tmp = tmp.next;
        }
        return null;
    }

    @Override
    public void forEach(BiConsumer<K, V> consumer) {
        for (int i = 0; i < capacity; ++i) {
            var tmp = array[i];
            while (tmp != null) {
                consumer.accept(tmp.key, tmp.value);
                tmp = tmp.next;
            }
        }
    }

    @SuppressWarnings("unchecked")
    private static <K, V> Node<K, V>[] allocate(int capacity) {
        return (Node<K, V>[]) new Node[capacity];
    }

    private static final class Node<K, V> {
        K key;
        V value;
        Node<K, V> prev;
        Node<K, V> next;

        Node(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }

    private Node<K, V>[] doublingCapacity() {
        Node<K, V>[] copy = allocate(capacity * 2);
        capacity *= 2;
        System.arraycopy(array, 0, copy, 0, size);
        return copy;
    }

    private int hash(Object key) {
        int h;
        return (key == null) ? 0 : (h = key.hashCode()) ^ (h >>> 16);
    }

    private int getInxOnKey(Object key) {
        int h = hash(key);
        return h & (capacity - 1);
    }
}
