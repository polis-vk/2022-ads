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

    private static final int INCREASE_FACTOR = 2;

    private final float loadFactor;
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
        this.loadFactor = loadFactor;
        array = allocate((int) (expectedMaxSize / loadFactor));
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
        Node<K, V> current = array[hashToIndex(key)];
        while (current != null) {
            if (current.key.equals(key)) {
                return current.value;
            }
            current = current.next;
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
        if (size >= loadFactor * array.length) { // >
            resize();
        }

        int index = hashToIndex(key);
        Node<K, V> current = array[index];
        if (current == null) {
            array[index] = new Node<>(key, value);
            size++;
            return null;
        }
        Node<K, V> prev = null;
        while (current != null) {
            if (current.key.equals(key)) {
                V oldVal = current.value;
                current.value = value;
                return oldVal;
            }
            prev = current;
            current = current.next;
        }
        size++;
        prev.next = new Node<>(key, value);
        prev.next.prev = prev;
        return null;
    }

    @Nullable
    @Override
    public V remove(K key) {
        final int index = hashToIndex(key);
        Node<K, V> current = array[index];
        Node<K, V> prev = null;
        while (current != null) {
            if (current.key.equals(key)) {
                break;
            }
            prev = current;
            current = current.next;
        }
        if (current == null) {
            return null;
        }
        V oldVal = current.value;
        if (prev == null) {
            array[index] = current.next;
            if (current.next != null) {
                current.next.prev = null;
            }
        } else if (current.next == null) {
            current.prev.next = null;
        } else {
            current.prev.next = current.next;
            current.next.prev = current.prev;
        }
        size--;
        return oldVal;
    }

    @Override
    public void forEach(BiConsumer<K, V> consumer) {
        for (Node<K, V> kvNode : array) {
            Node<K, V> current = kvNode;
            while (current != null) {
                consumer.accept(current.key, current.value);
                current = current.next;
            }
        }
    }

    @SuppressWarnings("unchecked")
    private static <K, V> Node<K, V>[] allocate(int capacity) {
        return (Node<K, V>[]) new Node[capacity];
    }

    private int hashToIndex(K key) {
        return (key.hashCode() & 0x7ffffff) % array.length;
    }

    private void resize() {
        Node<K, V>[] old = array;
        array = allocate(array.length * INCREASE_FACTOR);
        System.arraycopy(old, 0, array, 0, old.length);
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
