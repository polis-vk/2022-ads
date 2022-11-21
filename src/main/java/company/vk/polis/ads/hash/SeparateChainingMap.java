package company.vk.polis.ads.hash;

import org.jetbrains.annotations.Nullable;

import java.util.function.BiConsumer;

/**
 * Map implementation with separate chaining collision resolution approach
 *
 * @param <K> key
 * @param <V> value
 */
public final class SeparateChainingMap<K, V> implements Map<K, V> {
    // Do not edit this field!!!
    private Node<K, V>[] array;
    private int size = 0;
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
        this.loadFactor = loadFactor;
        this.capacity = (int) (expectedMaxSize / loadFactor);
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
        Node<K, V> head = array[getBucketIndex(key)];
        while (head != null) {
            if (head.key.equals(key)) {
                return head.value;
            }
            head = head.next;
        }
        return null;
    }

    private int getBucketIndex(K key) {
        return (key.hashCode() & 0x7ffffff) % capacity;
    }

    /**
     * Если capacity * loadFactor == size() и будет добавлен новый ключ,
     * то нужно выполнить расширение массивов
     */
    @Nullable
    @Override
    public V put(K key, V value) {
        if (capacity * loadFactor <= size) {
            Node<K, V>[] temp = array;
            capacity <<= 1;
            size = 0;
            array = allocate(capacity);
            for (Node<K, V> headNode : temp) {
                while (headNode != null) {
                    put(headNode.key, headNode.value);
                    headNode = headNode.next;
                }
            }
        }
        int bucketIndex = getBucketIndex(key);
        Node<K, V> head = array[bucketIndex];
        while (head != null) {
            if (head.key.equals(key)) {
                V out = head.value;
                head.value = value;
                return out;
            }
            head = head.next;
        }
        size++;
        head = array[bucketIndex];
        Node<K, V> newNode = new Node<>(key, value);
        newNode.next = head;
        array[bucketIndex] = newNode;
        return null;
    }

    @Nullable
    @Override
    public V remove(K key) {
        int bucketIndex = getBucketIndex(key);
        Node<K, V> head = array[bucketIndex];
        Node<K, V> prev = null;
        while (head != null) {
            if (head.key.equals(key)) {
                break;
            }
            prev = head;
            head = head.next;
        }
        if (head == null) {
            return null;
        }
        size--;
        if (prev != null) {
            prev.next = head.next;
        } else {
            array[bucketIndex] = head.next;
        }
        return head.value;
    }

    @Override
    public void forEach(BiConsumer<K, V> consumer) {
        for (Node<K, V> headNode : array) {
            while (headNode != null) {
                consumer.accept(headNode.key, headNode.value);
                headNode = headNode.next;
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
