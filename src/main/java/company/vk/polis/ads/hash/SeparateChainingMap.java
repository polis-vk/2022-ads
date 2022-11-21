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
    private int capacity;
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
        this.capacity = (int) (expectedMaxSize / loadFactor);
        array = allocate(capacity);
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean containsKey(K key) {
        return Objects.nonNull(get(key));
    }

    private int hashCode(K key) {
        return System.identityHashCode(key);
    }

    @Nullable
    @Override
    public V get(K key) {
        int position = hashCode(key) & (array.length - 1);
        Node<K, V> currNode = array[position];
        while (Objects.nonNull(currNode.key)) {
            if (currNode.key == key) {
                return currNode.value;
            }
            currNode = currNode.next;
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
        V prevValue = put(key, value, array);
        if (Objects.isNull(prevValue)) {
            size++;
        }
        resizeIfNeed();
        return prevValue;
    }

    private V put(K key, V value, Node<K, V>[] arr) {
        int currPosition = hashCode(key) & (arr.length - 1);
        Node<K, V> currNode = arr[currPosition];
        if (currNode == null) {
            arr[currPosition] = new Node<>(key, value);
        } else {
            Node<K, V> prevNode = null;
            while (currNode != null) {
                if (currNode.key.equals(key)) {
                    V prevValue = currNode.value;
                    currNode.value = value;
                    return prevValue;
                }
                prevNode = currNode;
                currNode = currNode.next;
            }
            currNode = new Node<>(key, value);
            currNode.prev = prevNode;
            prevNode.next = currNode;
        }
        return null;
    }

    private void resizeIfNeed() {
        if (capacity * loadFactor + 1 > size()) {
            return;
        }
        capacity *= 2;
        Node<K, V>[] newArray = allocate(capacity);
        for (Node<K, V> currNode : array) {
            while (Objects.nonNull(currNode)) {
                put(currNode.key, currNode.value, newArray);
                currNode = currNode.next;
            }
        }
        array = newArray;
    }

    @Nullable
    @Override
    public V remove(K key) {
        int position = hashCode(key) & (array.length - 1);
        Node<K, V> currNode = array[position];
        while (Objects.nonNull(currNode)) {
            if (currNode.key.equals(key)) {
                if (Objects.isNull(currNode.prev)) {
                    currNode.next.prev = null;
                    array[position] = currNode.next;
                } else if (Objects.isNull(currNode.next)) {
                    currNode.prev.next = null;
                } else {
                    currNode.next.prev = currNode.prev;
                    currNode.prev.next = currNode.next;
                }
                size--;
                return currNode.value;
            }
            currNode = currNode.next;
        }
        return null;
    }

    @Override
    public void forEach(BiConsumer<K, V> consumer) {
        Objects.requireNonNull(consumer);
        for (Node<K, V> currNode : array) {
            while (Objects.nonNull(currNode)) {
                consumer.accept(currNode.key, currNode.value);
                currNode = currNode.next;
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
