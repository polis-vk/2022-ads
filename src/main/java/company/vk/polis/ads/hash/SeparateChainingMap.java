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
    private static final int INCREASE_COEFFICIENT = 2;
    private static final int DEFAULT_CAPACITY = 16;
    private final int expectedMaxSize;
    private final float loadFactor;
    // Do not edit this field!!!
    private Node<K, V>[] array;
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
        array = allocate(Math.max(DEFAULT_CAPACITY, expectedMaxSize));
        this.expectedMaxSize = expectedMaxSize;
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
        Node<K, V> head = array[getIndex(key)];
        while (head != null && !head.key.equals(key)) {
            head = head.next;
        }
        return head != null ? head.value : null;
    }

    /**
     * Если capacity * loadFactor == size() и будет добавлен новый ключ,
     * то нужно выполнить расширение массивов
     */
    @Nullable
    @Override
    public V put(K key, V value) {
        int i = getIndex(key);
        Node<K, V> newNode = new Node<>(key, value);
        Node<K, V> head = array[i];
        if (head == null) {
            array[i] = newNode;
            size++;
            resizeIfNeed(size);
            return null;
        }
        while (head.next != null) {
            if (head.key.equals(key)) {
                V lastValue = head.value;
                head.value = value;
                return lastValue;
            }
            head = head.next;
        }
        if (head.key.equals(key)) {
            V lastValue = head.value;
            head.value = value;
            return lastValue;
        }
        head.next = newNode;
        newNode.prev = head;
        size++;
        resizeIfNeed(size);
        return null;
    }


    private int getIndex(K key) {
        return key.hashCode() & (array.length - 1);
    }

    private void resizeIfNeed(int size) {
        if (size >= (int) (array.length * loadFactor) && size > expectedMaxSize) {
            array = allocate(array.length * INCREASE_COEFFICIENT);
            for (Node<K, V> element : array) {
                Node<K, V> head = element;
                while (head != null) {
                    put(head.key, head.value);
                }
            }
        }
    }

    @Nullable
    @Override
    public V remove(K key) {
        int i = getIndex(key);
        Node<K, V> head = array[i];
        while (head != null) {
            if (head.key.equals(key)) {
                if (head.prev != null) {
                    (head.prev).next = head.next;
                }
                if (head.next != null) {
                    (head.next).prev = head.prev;
                }
                if (head.equals(array[i])) {
                    array[i] = head.next;
                }
                size--;
                return head.value;
            }
            head = head.next;
        }
        return null;
    }

    @Override
    public void forEach(BiConsumer<K, V> consumer) {
        for (Node<K, V> head : array) {
            Node<K, V> next = head;
            while (next != null) {
                consumer.accept(next.key, next.value);
                next = next.next;
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
