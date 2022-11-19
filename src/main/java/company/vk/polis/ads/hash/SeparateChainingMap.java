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
    private static final int EXPANSION_COEFFICIENT = 2;
    private static final int INITIAL_LENGTH = 16;
    private final float loadFactor;
    private final int expectedMaxSize;
    private int size;
    // Do not edit this field!!!
    private Node<K, V>[] array;

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
        this.expectedMaxSize = expectedMaxSize;
        this.loadFactor = loadFactor;
        array = allocate(INITIAL_LENGTH);
        size = 0;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean containsKey(K key) {
        return getNode(key) != null;
    }

    @Nullable
    @Override
    public V get(K key) {
        Node<K, V> node = getNode(key);
        if (node == null) {
            return null;
        }
        return node.value;
    }

    /**
     * Если capacity * loadFactor == size() и будет добавлен новый ключ,
     * то нужно выполнить расширение массивов
     */
    @Nullable
    @Override
    public V put(K key, V value) {
        Node<K, V> node = getNode(key);
        if (node == null) {
            if (size >= expectedMaxSize && array.length * loadFactor <= size) {
                expand();
            }
            put(array, key, value);
            size++;
            return null;
        }
        V oldValue = node.value;
        node.value = value;
        return oldValue;
    }

    @Nullable
    @Override
    public V remove(K key) {
        int hashIndex = hashIndex(key, array.length);
        if (array[hashIndex] == null) {
            return null;
        }
        if (array[hashIndex].key.equals(key)) {
            V oldValue = array[hashIndex].value;
            array[hashIndex] = array[hashIndex].next;
            if (array[hashIndex] != null) {
                array[hashIndex].prev = null;
            }
            size--;
            return oldValue;
        }
        Node<K, V> node = array[hashIndex];
        while (node.next != null && !node.next.key.equals(key)) {
            node = node.next;
        }
        if (node.next == null) {
            return null;
        }
        V oldValue = node.next.value;
        node.next = node.next.next;
        if (node.next != null) {
            node.next.prev = node;
        }
        size--;
        return oldValue;
    }

    @Override
    public void forEach(BiConsumer<K, V> consumer) {
        for (int i = 0; i < array.length; i++) {
            Node<K, V> node = array[i];
            while (node != null) {
                consumer.accept(node.key, node.value);
                node = node.next;
            }
        }
    }

    private void expand() {
        Node<K, V>[] expandedArray = allocate(array.length * EXPANSION_COEFFICIENT);
        for (int i = 0; i < array.length; i++) {
            Node<K, V> node = array[i];
            while (node != null) {
                put(expandedArray, node.key, node.value);
                node = node.next;
            }
        }
        array = expandedArray;
    }

    private void put(Node<K, V>[] array, K key, V value) {
        int hashIndex = hashIndex(key, array.length);
        Node<K, V> newNode = new Node<>(key, value);
        Node<K, V> node = array[hashIndex];
        if (node == null) {
            array[hashIndex] = newNode;
            return;
        }
        while (node.next != null) {
            node = node.next;
        }
        node.next = newNode;
    }

    private int hashIndex(K key, int m) {
        return (key.hashCode() & 0x7fffffff) % m;
    }

    private Node<K, V> getNode(K key) {
        int hashIndex = hashIndex(key, array.length);
        Node<K, V> node = array[hashIndex];
        while (node != null) {
            if (node.key.equals(key)) {
                return node;
            }
            node = node.next;
        }
        return null;
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
}
