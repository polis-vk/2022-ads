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
    private Node<K, V>[] array;
    private int expectedMaxSize; // threshold
    private int size;
    private int capacity; // количество элементов array; увеличиваем, когда больше expectedMaxSize

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
        size = 0;
        capacity = (int) (expectedMaxSize / loadFactor);
        array = allocate(capacity);
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
        var node = getNode(key);
        return node == null ? null : node.value;
    }

    /**
     * Если capacity * loadFactor == size() и будет добавлен новый ключ,
     * то нужно выполнить расширение массивов
     */
    @Nullable
    @Override
    public V put(K key, V value) {
        if (size + 1 > expectedMaxSize) {
            resize();
        }
        int index = indexOf(hash(key), capacity);
        if (array[index] == null) {
            array[index] = new Node<>(key, value);
        } else {
            Node<K, V> node = array[index];
            Node<K, V> prevNode = null;
            while (node != null) {
                if (node.key.equals(key)) {
                    V oldValue = node.value;
                    node.value = value;
                    return oldValue;
                }
                prevNode = node;
                node = node.next;
            }
            node = new Node<>(key, value);
            node.prev = prevNode;
            node.prev.next = node;
        }
        size++;
        return null;
    }

    @Nullable
    @Override
    public V remove(K key) {
        int index = indexOf(hash(key), capacity);
        Node<K, V> node = array[index];

        while (node != null) {
            if (node.key.equals(key)) {
                if (node.equals(array[index])) {
                    // deleting the first element
                    node.next.prev = null;
                    array[index] = node.next;
                } else {
                    if (node.next != null) {
                        node.next.prev = node.prev;
                    }
                    node.prev.next = node.next;
                }
                V value = node.value;
                size--;
                return value;
            }
            node = node.next;
        }
        return null;
    }

    @Override
    public void forEach(BiConsumer<K, V> consumer) {
        if (array == null) {
            return;
        }
        for (int i = 0; i < capacity; i++) {
            var node = array[i];
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

        public void add(Node<K, V> node) {
            var curNode = this;
            while (curNode.next != null) {
                curNode = curNode.next;
            }
            curNode.next = node;
            curNode.next.prev = curNode;
        }

        public boolean equals(Node<K, V> o) {
            if (o == this) {
                return true;
            }
            return Objects.equals(key, o.key) && Objects.equals(value, o.value);
        }

    }

    private int hash(K key) {
        int h;
        return (key == null) ? 0 : (h = key.hashCode()) ^ (h >>> 16);
    }

    private void resize() {
        expectedMaxSize *= 2;
        capacity *= 2;
        var oldArray = array;
        array = allocate(capacity);
        for (int i = 0; i < capacity / 2; i++) {
            var node = oldArray[i];
            while (node != null) {
                put(node.key, node.value);
                node = node.next;
            }
        }
    }

    private int indexOf(int h, int capacity) {
        return h & (capacity - 1);
    }

    private Node<K, V> getNode(K key) {
        int index = indexOf(hash(key), capacity);
        Node<K, V> node = array[index];
        while (node != null) {
            if (node.key.equals(key)) {
                return node;
            }
            node = node.next;
        }
        return null;
    }

}
