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
    private int capacity;
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
        this.loadFactor = loadFactor;
        this.capacity = (int) (expectedMaxSize / loadFactor);
        this.array = allocate(capacity);
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean containsKey(K key) {
        Node<K, V> curNode = array[getIndex(key)];

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
        Node<K, V> curNode = array[getIndex(key)];

        while (curNode != null) {
            if (curNode.key.equals(key)) {
                return curNode.value;
            }
            curNode = curNode.next;
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
        resizeIfNeed();
        int index = getIndex(key);
        Node<K, V> curNode = array[index];

        if (curNode == null) {
            array[index] = new Node<>(key, value);
            size++;
            return null;
        }

        while (curNode.next != null) {
            if (curNode.key.equals(key)) {
                V result = curNode.value;
                curNode.value = value;
                return result;
            }
            curNode = curNode.next;
        }

        if (curNode.key.equals(key)) {
            V result = curNode.value;
            curNode.value = value;
            return result;
        }

        Node<K, V> newNode = new Node<>(key, value);
        newNode.prev = curNode;
        curNode.next = newNode;
        size++;
        return null;
    }

    @Nullable
    @Override
    public V remove(K key) {
        int index = getIndex(key);
        Node<K, V> curNode = array[index];

        while (curNode != null) {
            if (curNode.key.equals(key)) {
                if (curNode.prev == null) {
                    if (curNode.next != null) {
                        curNode.next.prev = null;
                    }
                    array[index] = curNode.next;
                } else {
                    if (curNode.next != null) {
                        curNode.next.prev = curNode.prev;
                    }
                    curNode.prev.next = curNode.next;
                }
                size--;
                return curNode.value;
            }
            curNode = curNode.next;
        }

        return null;
    }

    @Override
    public void forEach(BiConsumer<K, V> consumer) {
        for (Node<K, V> bucket : array) {
            Node<K, V> curNode = bucket;

            while (curNode != null) {
                consumer.accept(curNode.key, curNode.value);
                curNode = curNode.next;
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

    private int getIndex(K key) {
        return Math.abs(key.hashCode()) % capacity;
    }

    private void resizeIfNeed() {
        if (capacity * loadFactor > size) {
            return;
        }

        capacity *= 2;
        size = 0;
        Node<K, V>[] oldArray = array;
        array = allocate(capacity);

        for (Node<K, V> bucket : oldArray) {
            Node<K, V> curNode = bucket;
            while (curNode != null) {
                put(curNode.key, curNode.value);
                curNode = curNode.next;
            }
        }
    }
}
