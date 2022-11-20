package company.vk.polis.ads.hash;

import java.util.Objects;
import java.util.function.BiConsumer;

import org.jetbrains.annotations.Nullable;

/**
 * Map implementation with separate chaining collision resolution approach
 *
 * @param <K> key
 * @param <V> value
 */
public final class SeparateChainingMap<K, V> implements Map<K, V> {
    static final int GROW_FACTOR = 2;
    static final int DEFAULT_INITIAL_CAPACITY = 16;

    // Do not edit this field!!!
    private Node<K, V>[] array;
    private final float loadFactor;
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
        int expectedCapacity = (int) (expectedMaxSize / loadFactor);
        array = allocate(Math.max(expectedCapacity, DEFAULT_INITIAL_CAPACITY));
        this.loadFactor = loadFactor;
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
        Node<K, V> node = array[getIndex(key)];
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
        if (array.length * loadFactor <= size) {
            increaseCapacity();
        }

        int index = getIndex(key);
        Node<K, V> node = array[index];
        while (node != null) {
            if (key.equals(node.key)) {
                V oldValue = node.value;
                node.value = value;
                return oldValue;
            }
            node = node.next;
        }

        size++;
        Node<K, V> newHead = new Node<>(key, value);
        Node<K, V> head = array[index];
        if (head == null) {
            array[index] = newHead;
            return null;
        }
        head.prev = newHead;
        newHead.next = head;
        array[index] = newHead;
        return null;
    }

    @Nullable
    @Override
    public V remove(K key) {
        int index = getIndex(key);
        Node<K, V> node = array[index];
        if (node == null) {
            return null;
        }

        while (node.hasNext()) {
            if (key.equals(node.key)) {
                break;
            }
            node = node.next;
        }

        if (!key.equals(node.key)) {
            return null;
        }

        if (node.hasPrev()) {
            node.prev.next = node.next;
        } else if (node.hasNext()) {
            array[index] = node.next;
            array[index].prev = null;
        } else {
            array[index] = null;
        }
        size--;
        return node.value;
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

    @SuppressWarnings("unchecked")
    private static <K, V> Node<K, V>[] allocate(int capacity) {
        return (Node<K, V>[]) new Node[capacity];
    }

    private void increaseCapacity() {
        Node<K, V>[] tempArray = array;
        array = allocate(size * GROW_FACTOR);
        size = 0;
        for (Node<K, V> node : tempArray) {
            while (node != null) {
                put(node.key, node.value);
                node = node.next;
            }
        }
    }

    private int getIndex(K key) {
        return (Objects.hashCode(key) & 0x7fffffff) % array.length;
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

        private boolean hasNext() {
            return next != null;
        }

        private boolean hasPrev() {
            return prev != null;
        }
    }

}