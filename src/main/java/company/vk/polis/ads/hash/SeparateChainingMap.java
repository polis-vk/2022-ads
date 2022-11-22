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
        int hash = hash(key);
        Node<K, V> node = array[hash];
        while(node != null){
            if(node.key.equals(key)){
                return true;
            }
            node = node.next;
        }
        return false;
    }

    @Nullable
    @Override
    public V get(K key) {
        int hash = hash(key);
        Node<K, V> node = array[hash];
        while(node != null){
            if(node.key.equals(key)){
                return node.value;
            }
            node = node.next;
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
        if (size >= array.length * loadFactor) {
            resize();
        }
        int hash = hash(key);
        Node<K, V> node = array[hash];
        if(node == null){
            array[hash] = new Node<>(key, value);
            size++;
            return null;
        }
        Node<K, V> prev = null;
        while(node != null) {
            if (node.key.equals(key)) {
                V oldValue = node.value;
                node.value = value;
                return oldValue;
            }
            prev = node;
            node = node.next;
        }
        prev.next = new Node<>(key, value);
        prev.next.prev = prev;
        size++;
        return null;
    }

    @Nullable
    @Override
    public V remove(K key) {
        int hash = hash(key);
        Node<K, V> node = array[hash];
        Node<K, V> prev = null;
        while (node != null){
            if(node.key.equals(key)){
                V value = node.value;
                if(prev == null){
                    array[hash] = node.next;
                }else{
                    prev.next = node.next;
                }
                size--;
                return value;
            }
            prev = node;
            node = node.next;
        }
        return null;
    }

    @Override
    public void forEach(BiConsumer<K, V> consumer) {
        Node<K, V> cur;
        for(Node<K, V> node : array){
            cur = node;
            while(cur != null){
                consumer.accept(cur.key, cur.value);
                cur = cur.next;
            }
        }
    }

    @SuppressWarnings("unchecked")
    private static <K, V> Node<K, V>[] allocate(int capacity) {
        return (Node<K, V>[]) new Node[capacity];
    }

    private void resize() {
        Node<K, V>[] tempArray = array;
        array = allocate(array.length * 2);
        size = 0;
        Node<K, V> cur;
        for (Node<K, V> kvNode : tempArray) {
            cur = kvNode;
            while (cur != null) {
                put(cur.key, cur.value);
                cur = cur.next;
            }
        }
    }

    private int hash(K key) {
        return Math.abs(key.hashCode() % array.length);
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
