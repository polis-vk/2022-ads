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
    // Do not edit this field!!!
    private Node<K, V>[] array;
    private final float loadFactor;
    private int size;
    private int capacity;

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
        int index = hash(key);
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
        if (capacity * loadFactor <= size()) {
            resize();
        }
        int index = hash(key);
        Node<K, V> node = array[index];
        if (node == null) {
            array[index] = new Node<>(key, value);
            size++;
            return null;
        }
        Node<K, V> prevNode = node;
        while (node != null) {
            if (key.equals(node.key)) {
                V oldValue = node.value;
                node.value = value;
                return oldValue;
            }
            prevNode = node;
            node = node.next;
        }
        Node<K, V> newNode = new Node<>(key, value);
        prevNode.next = newNode;
        newNode.prev = prevNode;
        size++;
        return null;
    }

    @Nullable
    @Override
    public V remove(K key) {
        int index = hash(key);
        Node<K, V> node = array[index];
        Node<K, V> prevNode = null;
        Node<K, V> nextNode;
        while (node != null) {
            if (key.equals(node.key)) {
                nextNode = node.next;
                if (prevNode != null) {
                    prevNode.next = nextNode;
                    if (nextNode != null) {
                        nextNode.prev = prevNode;
                    }
                } else {
                    array[index] = node.next;
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

    @SuppressWarnings("unchecked")
    private static <K, V> Node<K, V>[] allocate(int capacity) {
        return (Node<K, V>[]) new Node[capacity];
    }

    private int hash(K key) {
        return (Objects.hashCode(key) & 0x7fffffff) % capacity;
    }

    private void resize() {
        Node<K, V>[] oldArray = array;
        size = 0;
        capacity *= 2;
        array = allocate(capacity);
        for (Node<K, V> node : oldArray) {
            while (node != null) {
                put(node.key, node.value);
                node = node.next;
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
