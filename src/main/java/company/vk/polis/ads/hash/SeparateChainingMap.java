package company.vk.polis.ads.hash;

import java.util.function.BiConsumer;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * Map implementation with separate chaining collision resolution approach
 *
 * @param <K> key
 * @param <V> value
 */
public final class SeparateChainingMap<K, V> implements Map<K, V> {
    private static final int INITIAL_CAPACITY = 16;

    // Do not edit this field!!!
    private Node<K, V>[] array;

    private int size;
    private final int expectedMaxSize;
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
        this.array = allocate(Math.min(INITIAL_CAPACITY, expectedMaxSize));
        this.expectedMaxSize = expectedMaxSize;
        this.loadFactor = loadFactor;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean containsKey(K key) {
        int hash = key.hashCode();
        int index = hash & (array.length - 1);
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
        int hash = key.hashCode();
        int index = hash & (array.length - 1);
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
        int hash = key.hashCode();
        int index = hash & (array.length - 1);
        Node<K, V> node = array[index];
        if (node == null) {
            array[index] = new Node<>(key, value);
        } else {
            Node<K, V> prev = null;
            while (node != null) {
                if (node.key.equals(key)) {
                    V resultValue = node.value;
                    node.value = value;
                    return resultValue;
                }
                prev = node;
                node = node.next;
            }
            Node<K, V> next = prev.next;
            prev.next = new Node<>(key, value);
            prev.next.next = next;
        }
        size++;
        expandIfNecessary();
        return null;
    }

    @Nullable
    @Override
    public V remove(K key) {
        int hash = key.hashCode();
        int index = hash & (array.length - 1);
        Node<K, V> node = array[index];
        Node<K, V> prev = null;
        while (node != null) {
            if (node.key.equals(key)) {
                V value = node.value;
                if (prev != null) {
                    prev.next = removeNode(node);
                } else {
                    array[index] = removeNode(node);
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
            if (node != null) {
                Node<K, V> current = node;
                while (current != null) {
                    consumer.accept(current.key, current.value);
                    current = current.next;
                }
            }
        }
    }

    private Node<K, V> removeNode(@NotNull Node<K, V> node) {
        Node<K, V> result = null;
        if (node.next != null) {
            result = node.next;
            node.next = null;
            result.prev = node.prev;
        }
        node.prev = null;
        return result;
    }

    @SuppressWarnings("unchecked")
    private static <K, V> Node<K, V>[] allocate(int capacity) {
        return new Node[capacity];
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

    private void expandIfNecessary() {
        if (size > expectedMaxSize && size >= (int) (array.length * loadFactor)) {
            Node<K, V>[] nodes = array;
            array = allocate(Math.min(array.length * 2, expectedMaxSize));
            for (Node<K, V> node : nodes) {
                if (node != null) {
                    int hash = node.key.hashCode();
                    int index = hash & (array.length - 1);
                    array[index] = node;
                }
            }
        }
    }
}
