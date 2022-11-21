package company.vk.polis.ads.hash;

import java.util.Arrays;
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
    private static final float GOLD_RATIO = 1.618F;

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
        this.loadFactor = loadFactor;
        this.array = allocate((int) (expectedMaxSize / loadFactor));
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
        Node<K, V> currentNode = array[getArrayIndex(key)];
        while (currentNode != null) {
            if (currentNode.key.equals(key)) {
                return currentNode.value;
            }
            currentNode = currentNode.next;
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
            ensureCapacity();
        }
        int hashIndex = getArrayIndex(key);
        if (array[hashIndex] == null) {
            array[hashIndex] = new Node<>(key, value);
            size++;
            return null;
        }
        Node<K, V> currentNode = array[hashIndex];
        Node<K, V> previousNode = currentNode.prev;
        while (currentNode != null) {
            if (currentNode.key.equals(key)) {
                final V previousValue = currentNode.value;
                currentNode.value = value;
                return previousValue;
            }
            previousNode = currentNode;
            currentNode = currentNode.next;
        }
        currentNode = new Node<>(key, value);
        currentNode.prev = previousNode;
        previousNode.next = currentNode;
        size++;
        return null;
    }

    @Nullable
    @Override
    public V remove(K key) {
        final int index = getArrayIndex(key);
        Node<K, V> currentNode = array[index];
        while (currentNode != null) {
            if (currentNode.key.equals(key)) {
                Node<K, V> nextNode = currentNode.next;
                Node<K, V> previousNode = currentNode.prev;
                if (nextNode != null) {
                    nextNode.prev = previousNode;
                }
                if (previousNode == null) {
                    array[index] = nextNode;
                } else {
                    previousNode.next = currentNode.next;
                }
                size--;
                return currentNode.value;
            }
            currentNode = currentNode.next;
        }
        return null;
    }

    @Override
    public void forEach(BiConsumer<K, V> consumer) {
        for (Node<K, V> currentNode : array) {
            while (currentNode != null) {
                consumer.accept(currentNode.key, currentNode.value);
                currentNode = currentNode.next;
            }
        }
    }

    private int hashCode(K key) {
        return Objects.hashCode(key) >> 8;
    }

    private int getArrayIndex(K key) {
        return (hashCode(key) & 0x7fffffff) % (array.length - 1);
    }

    @SuppressWarnings("unchecked")
    private static <K, V> Node<K, V>[] allocate(int capacity) {
        return (Node<K, V>[]) new Node[capacity];
    }

    private void ensureCapacity() {
        Node<K, V>[] copyOfNodesArray = Arrays.copyOf(array, array.length);
        int newCapacity = (int) Math.ceil(array.length * GOLD_RATIO);
        array = allocate(newCapacity);
        System.arraycopy(copyOfNodesArray, 0, array, 0, size);
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
