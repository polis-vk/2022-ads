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
        array = allocate((int) (expectedMaxSize / loadFactor));
        this.loadFactor = loadFactor;
    }

    private int getIndex(K key) {
        return Math.abs(key.hashCode() % array.length);
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean containsKey(K key) {
        int index = getIndex(key);

        Node<K, V> tmp = array[index];
        while (tmp != null) {
            if (tmp.key.equals(key)) {
                return true;
            }
            tmp = tmp.next;
        }

        return false;
    }

    @Nullable
    @Override
    public V get(K key) {
        int index = getIndex(key);

        Node<K, V> tmp = array[index];
        while (tmp != null) {
            if (tmp.key.equals(key)) {
                return tmp.value;
            }
            tmp = tmp.next;
        }

        return null;
    }

    private void resize() {
        Node<K, V>[] tmp = array;
        array = allocate(2 * tmp.length);
        size = 0;
        for (Node<K, V> node : tmp) {
            while (node != null) {
                put(node.key, node.value);
                node = node.next;
            }
        }
    }

    /**
     * Если capacity * loadFactor == size() и будет добавлен новый ключ,
     * то нужно выполнить расширение массивов
     */

    @Nullable
    @Override
    public V put(K key, V value) {
        if (array.length * loadFactor <= size()) {
            resize();
        }
        size++;

        int index = getIndex(key);
        if (array[index] == null) {
            array[index] = new Node<K, V>(key, value);
            return null;
        }

        Node<K, V> node = array[index];
        while (node.next != null && !node.key.equals(key)) {
            node = node.next;
        }

        if (node.next == null && !node.key.equals(key)) {
            node.next = new Node<K, V>(key, value);
            node.next.prev = node;
            return null;
        }

        V tmp = node.value;
        node.value = value;
        size--;
        return tmp;
    }

    @Nullable
    @Override
    public V remove(K key) {
        int index = getIndex(key);
        if (array[index] == null) {
            return null;
        }

        Node<K, V> node = array[index];
        while (node != null && !node.key.equals(key)) {
            node = node.next;
        }

        if (node == null) {
            return null;
        }

        V value = node.value;
        if (node.prev == null) {
            array[index] = node.next;
            if (node.next != null) {
                array[index].prev = null;
            }
        } else {
            Node<K, V> tmp = node.next;
            if (tmp == null) {
                node.prev.next = null;
            } else {
                tmp.prev = node.prev;
                node.prev.next = tmp;
            }
        }
        size--;
        return value;
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
