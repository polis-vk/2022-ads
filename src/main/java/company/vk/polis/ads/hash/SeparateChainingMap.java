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
    private int capacity;
    private int size;
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
        this.loadFactor = loadFactor;
        this.capacity = (int) (expectedMaxSize / loadFactor);
        this.array = allocate(capacity);
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
        Node<K, V> current = array[indexOf(key)];
        while (current != null) {
            if (current.key.equals(key)) {
                return current.value;
            }
            current = current.next;
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
        if (capacity * loadFactor <= size) {
            expandArray();
        }

        int index = indexOf(key);
        Node<K, V> node = new Node<>(key, value);
        Node<K, V> current = array[index];
        Node<K, V> prev = null;

        if (current == null) {
            array[index] = node;
            size++;
            return null;
        }

        while (current != null) {
            if (current.key.equals(key)) {
                V oldValue = current.value;
                current.value = value;
                return oldValue;
            }
            prev = current;
            current = current.next;
        }

        prev.next = node;
        node.prev = prev;
        size++;
        return null;
    }

    @Nullable
    @Override
    public V remove(K key) {
        int index = indexOf(key);
        Node<K, V> current = array[index];

        while (current != null) {
            if (current.key.equals(key)) {
                V value = current.value;
                if (current.prev != null) {
                    current.prev.next = current.next;
                } else {
                    array[index] = current.next;
                }
                if (current.next != null) {
                    current.next.prev = current.prev;
                }
                size--;
                return value;
            }
            current = current.next;
        }

        return null;
    }

    @Override
    public void forEach(BiConsumer<K, V> consumer) {
        for (Node<K, V> bucket : array) {
            Node<K, V> current = bucket;
            while (current != null) {
                consumer.accept(current.key, current.value);
                current = current.next;
            }
        }
    }

    @SuppressWarnings("unchecked")
    private static <K, V> Node<K, V>[] allocate(int capacity) {
        return (Node<K, V>[]) new Node[capacity];
    }

    private int hash(K key) {
        int h;
        return (key == null) ? 0 : (h = key.hashCode()) ^ (h >>> 16);
    }

    private int indexOf(K key) {
        return hash(key) & (capacity - 1);
    }

    private void expandArray() {
        capacity *= 2;
        size = 0;
        Node<K, V>[] oldArray = array;
        array = allocate(capacity);

        for (Node<K, V> bucket : oldArray) {
            Node<K, V> current = bucket;
            while (current != null) {
                put(current.key, current.value);
                current = current.next;
            }
        }
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
}
