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
    private Node<K, V>[] array;
    private final float loadFactor;
    private int size;
    private V oldValue;
    private static final int[] primes = new int[]{ 2, 5, 11, 17, 37, 67, 131, 257, 521, 1031, 2053, 4099, 8209, 16411,
            32771, 65537, 131101, 262147, 524309, 1048583, 2097169, 4194319, 8388617, 16777259, 33554467, 67108879,
            134217757, 268435459, 536870923, 1073741827 };
    private int primeIndex;

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
        array = allocate(Math.round(expectedMaxSize / loadFactor));
        updatePrimeIndex();
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean containsKey(K key) {
        Node<K, V> curNode = array[getHash(key)];
        while (curNode != null) {
            if (curNode.key.equals(key)) {
                return true;
            }
            curNode = curNode.next;
        }
        return false;
    }

    @Nullable
    @Override
    public V get(K key) {
        Node<K, V> curNode = array[getHash(key)];
        while (curNode != null && !curNode.key.equals(key)) {
            curNode = curNode.next;
        }
        return (curNode == null ? null : curNode.value);
    }

    /**
     * Если capacity * loadFactor == size() и будет добавлен новый ключ,
     * то нужно выполнить расширение массивов
     */
    @Nullable
    @Override
    public V put(K key, V value) {
        if (Math.round(array.length * loadFactor) == size()) {
            resize();
        }
        oldValue = null;
        int index = getHash(key);
        array[index] = insert(array[index], key, value);
        return oldValue;
    }

    @Nullable
    @Override
    public V remove(K key) {
        oldValue = null;
        int index = getHash(key);
        array[index] = remove(array[index], key);
        return oldValue;
    }

    @Override
    public void forEach(BiConsumer<K, V> consumer) {
        for (Node<K, V> n : array) {
            while (n != null) {
                consumer.accept(n.key, n.value);
                n = n.next;
            }
        }
    }

    @SuppressWarnings("unchecked")
    private static <K, V> Node<K, V>[] allocate(int capacity) {
        return (Node<K, V>[]) new Node[capacity];
    }

    private void resize() {
        Node<K, V>[] oldArray = array;
        array = allocate(array.length << 1);
        updatePrimeIndex();
        size = 0;
        for (Node<K, V> n : oldArray) {
            while (n != null) {
                put(n.key, n.value);
                n = n.next;
            }
        }
    }

    private int getHash(K key) {
        return (key.hashCode() & 0x7fffffff) % primes[primeIndex] % array.length;
    }

    private void updatePrimeIndex() {
        int a = 1;
        int pow = 0;
        while (a < array.length) {
            a <<= 1;
            pow++;
        }
        primeIndex = pow - 1;
    }

    private Node<K, V> insert(Node<K, V> n, K key, V value) {
        if (n == null) {
            size++;
            return new Node<>(key, value);
        }
        if (n.key.equals(key)) {
            oldValue = n.value;
            n.value = value;
        } else {
            n.next = insert(n.next, key, value);
        }
        return n;
    }

    private Node<K, V> remove(Node<K, V> n, K key) {
        if (n == null) {
            return null;
        }
        if (n.key.equals(key)) {
            oldValue = n.value;
            n = n.next;
            size--;
        } else {
            n.next = remove(n.next, key);
        }
        return n;
    }

    private static final class Node<K, V> {
        K key;
        V value;
        Node<K, V> next;

        Node(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }
}
