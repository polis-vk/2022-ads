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
        array = allocate((int) (expectedMaxSize/loadFactor));
        this.loadFactor = loadFactor;
    }


    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean containsKey(K key) {
        int h = hash(key);
        Node<K, V> nowNode = array[h];
        while (nowNode != null) {
            if (Objects.equals(nowNode.key, key)) {
                return true;
            }
            nowNode = nowNode.next;
        }
        return false;
    }

    @Nullable
    @Override
    public V get(K key) {
        int h = hash(key);
        Node<K, V> nowNode = array[h];
        while (nowNode != null) {
            if (Objects.equals(nowNode.key, key)) {
                return nowNode.value;
            }
            nowNode = nowNode.next;
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
        checkAndResize();

        int h = hash(key);
        Node<K, V> nowNode = array[h];
        while (nowNode != null) {
            if (Objects.equals(nowNode.key, key)) {
                var ans = nowNode.value;
                nowNode.value = value;
                return ans;
            }
            nowNode = nowNode.next;
        }
        size++;
        if (array[h] == null) {
            array[h] = new Node<>(key, value);
        } else {
            var newNode = new Node<>(key, value);
            newNode.next = array[h];
            newNode.next.prev = newNode;
            array[h] = newNode;
        }
        return null;
    }

    @Nullable
    @Override
    public V remove(K key) {
        var h = hash(key);
        var nowNode = array[h];
        Node<K, V> ans = null;
        while (nowNode != null){
            if (nowNode.key.equals(key)) {
                ans = nowNode;
                break;
            }
            nowNode = nowNode.next;
        }
        if (ans != null) {
            size--;
            if (ans.prev != null) {
                ans.prev.next = ans.next;
            } else {
                array[h] = ans.next;
            }
            if (ans.next != null) {
                ans.next.prev = ans.prev;
            }
            return ans.value;
        } else {
            return null;
        }
    }

    @Override
    public void forEach(BiConsumer<K, V> consumer) {
        Node<K, V> nowNode;
        for (Node<K, V> kvNode : array) {
            nowNode = kvNode;
            while (nowNode != null) {
                consumer.accept(nowNode.key, nowNode.value);
                nowNode = nowNode.next;
            }
        }
    }

    public void checkAndResize(){
        if (loadFactor > ((float) size) / array.length) return;

        var oldArr = array;
        array = allocate(array.length * 2);

        for (Node<K, V> kvNode: oldArr) {
            var nowNode = kvNode;
            while (nowNode != null) {
                put(nowNode.key, nowNode.value);
                nowNode = nowNode.next;
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

    private int hash(K key) {
        return (key.hashCode() & 0x7fffffff) % array.length;
    }
}
