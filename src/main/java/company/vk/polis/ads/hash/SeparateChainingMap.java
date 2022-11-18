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
    private static final int GROW_FACTOR = 2;

    private Node<K, V>[] array;
    private final double loadFactor;
    private int capacity;
    private int size;

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
        capacity = (int) (expectedMaxSize / loadFactor);
        array = allocate(capacity);
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
        int hash = hash(key);
        int index = getIndex(hash);
        Node<K, V> node = array[index];
        while (node != null) {
            if (key.equals(node.key)) {
                return node.value;
            }
            node = node.next;
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
        if ((float) size / capacity >= loadFactor) {
            resize();
        }
        int hash = hash(key);
        int index = getIndex(hash);
        Node<K, V> node = array[index];
        Node<K, V> prevNode = node;
        if (node == null) {
            array[index] = new Node<>(key, value);
            size++;
            return null;
        }
        while (node != null) {
            if (key.equals(node.key)) {
                V prevValue = node.value;
                node.value = value;
                return prevValue;
            }
            prevNode = node;
            node = node.next;
        }
        prevNode.next = new Node<>(key, value);
        size++;
        return null;
    }

    @Nullable
    @Override
    public V remove(K key) {
        int hash = hash(key);
        int index = getIndex(hash);
        Node<K, V> node = array[index];
        Node<K, V> prevNode = null;
        while (node != null) {
            if (key.equals(node.key)) {
                if (prevNode == null) {
                    array[index] = node.next;
                } else {
                    prevNode.next = node.next;
                }
                size--;
                return node.value;
            }
            prevNode = node;
            node = node.next;
        }
        return null;
    }

    @Override
    public void forEach(BiConsumer<K, V> consumer) {
        for (Node<K, V> node : array) {
            while (node != null) {
                consumer.accept(node.key, node.value);
                node = node.next;
            }
        }
    }

    private int hash(K key) {
        int hash;
        return (hash = key.hashCode()) ^ (hash >>> 16);
    }

    private int getIndex(int hash) {
        return hash & (capacity - 1);
    }

    private void resize() {
        capacity = capacity * GROW_FACTOR;
        Node<K, V>[] newArray = array;
        array = allocate(capacity);
        size = 0;

        for (Node<K, V> node : newArray) {
            Node<K, V> head = node;
            while (head != null) {
                put(head.key, head.value);
                head = head.next;
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
}
