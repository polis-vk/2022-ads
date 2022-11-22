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
    private static final int DEFAULT_SIZE = 100;
    // Do not edit this field!!!
    private Node<K, V>[] array;
    private final int capacity;
    private int size = 0;
    private final float loadFactor;

    /**
     * Создает новый ассоциативный массив в соответствии с expectedMaxSize и loadFactor.
     * Сразу выделяет начальное количество памяти на основе expectedMaxSize и loadFactor.
     *
     * @param expectedMaxSize Ожидаемое максимальное количество элементов в ассоциативном массиве.
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
        int index = indexByHash(key);
        Node<K, V> currentNode = array[index];

        while(currentNode != null) {
            if (currentNode.key.equals(key)) {
                break;
            }
            currentNode = currentNode.next;
        }

        return currentNode == null ? null : currentNode.value;
    }

    /**
     * Если capacity * loadFactor == size() и будет добавлен новый ключ,
     * то нужно выполнить расширение массивов
     */
    @Nullable
    @Override
    public V put(K key, V value) {
        increaseIfNeed(size());

        int indexOfElement = indexByHash(key);
        Node<K, V> element = array[indexOfElement];
        V oldValue;

        if (element == null) {
            size++;
            array[indexOfElement] = new Node<>(key, value);
            return null;
        }

        while(element.next != null) {
            if (element.key.equals(key)) {
                break;
            }
            element = element.next;
        }

        if (element.key.equals(key)) {
            oldValue = element.value;
            element.value = value;
            return oldValue;
        }

        Node<K, V> newNode = new Node<>(key, value);
        element.next = newNode;
        newNode.prev = element;
        size++;
        return null;
    }

    private void increaseIfNeed(int size) {
        if (size >= capacity * loadFactor) {
            Node<K, V>[] newArray = allocate(capacity * 2);
            System.arraycopy(array, 0, newArray, 0, size);
            array = newArray;
        }
    }

    @Nullable
    @Override
    public V remove(K key) {
        Node<K, V> temp = array[indexByHash(key)];
        Node<K, V> prev = null;
        V resultValue = null;

        if (temp != null && temp.key.equals(key)) {
            resultValue = temp.value;
            array[indexByHash(key)] = temp.next;
            size--;
            return resultValue;
        }

        while (temp != null && !temp.key.equals(key)) {
            prev = temp;
            temp = temp.next;
        }

        if (temp == null) {
            return null;
        }

        resultValue = temp.value;
        if (prev != null) {
            size--;
            prev.next = temp.next;
            if (temp.next != null) {
                temp.next.prev = prev;
            }
        }

        return resultValue;
    }

    @Override
    public void forEach(BiConsumer<K, V> consumer) {
        for (Node<K, V> head : array) {
            while (head != null) {
                consumer.accept(head.key, head.value);
                head = head.next;
            }
        }
    }

    private int indexByHash(K key) {
        return (key.hashCode() & 0x7ffffff) % capacity;
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
