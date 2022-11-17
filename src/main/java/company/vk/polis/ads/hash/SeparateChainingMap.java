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
        int capacity = (int) (expectedMaxSize / loadFactor);
        array = (Node<K, V>[]) allocate(capacity);
        this.loadFactor = loadFactor;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean containsKey(K key) {
        int index = getNodeIndex(key);
        Node<K, V> node = array[index];
        while (node != null) {
            if (node.key.equals(key)) {
                return true;
            }
            node = node.next;
        }
        return false;
    }

    @Nullable
    @Override
    public V get(K key) {
        int index = getNodeIndex(key);
        Node<K, V> node = array[index];
        while (node != null) {
            if (node.key.equals(key)) {
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
        if ((float) size / array.length >= loadFactor) {
            resizeIfNeeded();
        }
        int index = getNodeIndex(key);
        Node<K, V> node = array[index];
        Node<K, V> prev = null;
        while (node != null) {
            if (node.key.equals(key)) {
                V oldValue = node.value;
                node.value = value;
                return oldValue;
            }
            prev = node;
            node = node.next;
        }
        if (prev == null) {
            array[index] = new Node<>(key, value);
        } else {
            prev.next = new Node<>(key, value);
            prev.next.prev = prev;
        }
        size++;
        return null;
    }

    @Nullable
    @Override
    public V remove(K key) {
        int index = getNodeIndex(key);
        Node<K, V> node = array[index];
        Node<K, V> prev = null;
        while (node != null) {
            if (node.key.equals(key)) {
                V value = node.value;
                if (prev == null) {
                    array[index] = node.next;
                } else {
                    prev.next = node.next;
                }
                size--;
                return value;
            }
            prev = node;
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

    private void resizeIfNeeded() {
        Node<K, V>[] tmp = array;
        array = allocate(2 * array.length);
        size = 0;
        for (Node<K, V> node : tmp) {
            while (node != null) {
                put(node.key, node.value);
                node = node.next;
            }
        }
    }

    private int getNodeIndex(K key) {
        int index = hashCode(key) % array.length;
        return index < 0 ? index * -1 : index;
    }

    private int hashCode(K key) {
        return Objects.hashCode(key);
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
