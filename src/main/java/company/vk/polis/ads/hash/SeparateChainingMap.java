package company.vk.polis.ads.hash;

import org.jetbrains.annotations.Nullable;

import java.util.Objects;
import java.util.function.BiConsumer;

/**
 * Map implementation with separate chaining collision resolution approach
 *
 * @param <K> key
 * @param <V> value
 */
public final class SeparateChainingMap<K, V> implements Map<K, V> {
    private final static int GROW_FACTOR = 2;

    // Do not edit this field!!!
    private Node<K, V>[] array;
    private final float loadFactor;
    private int size;

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
        Node<K, V> head = array[getIndex(key)];
        while (head != null) {
            if (head.key.equals(key)) {
                return head.value;
            }
            head = head.next;
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
            resize();
        }

        final int currIndex = getIndex(key);
        Node<K, V> currNode = array[currIndex];
        while (currNode != null) {
            if (currNode.key.equals(key)) {
                final V oldValue = currNode.value;
                currNode.value = value;
                return oldValue;
            }
            currNode = currNode.next;
        }

        currNode = array[currIndex];
        Node<K, V> newNode = new Node<>(key, value);
        if (currNode != null) {
            currNode.prev = newNode;
        }
        newNode.next = currNode;
        array[currIndex] = newNode;
        size++;
        return null;
    }

    @Nullable
    @Override
    public V remove(K key) {
        final int currIndex = getIndex(key);
        Node<K, V> currNode = array[currIndex];
        Node<K, V> prevNode = null;
        while (currNode != null) {
            if (currNode.key.equals(key)) {
                break;
            }
            prevNode = currNode;
            currNode = currNode.next;
        }

        if (currNode == null) {
            return null;
        }
        if (prevNode != null) {
            prevNode.next = currNode.next;
        } else {
            array[currIndex] = currNode.next;
        }
        size--;
        return currNode.value;
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

    private int getIndex(K key) {
        return Objects.hashCode(key) & (array.length - 1);
    }

    private void resize() {
        size = 0;
        Node<K, V>[] temp = array;
        array = allocate(array.length * GROW_FACTOR);
        for (Node<K, V> node : temp) {
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

