package company.vk.polis.ads.hash;

import java.util.function.BiConsumer;

import org.jetbrains.annotations.Nullable;

/**
 * Map implementation with double hashing collision resolution approach
 *
 * @param <K> key
 * @param <V> value
 */
public final class DoubleHashingMap<K, V> implements Map<K, V> {
    // Do not edit these 3 instance fields!!!
    private K[] keys;
    private V[] values;
    private boolean[] removed;
    private int capacity;
    private int size;
    private final float loadFactor;

    /**
     * Создает новый ассоциативный массив в соответствии с expectedMaxSize и loadFactor.
     * Сразу выделяет начальное количество памяти на основе expectedMaxSize и loadFactor.
     *
     * @param expectedMaxSize ожидаемое максимальное количество элементов в ассоциативном массие.
     *                        Это значит, что capacity - размер массивов под капотом -
     *                        не будет увеличиваться до тех пор, пока количество элементов
     *                        не станет больше чем expectedMaxSize
     * @param loadFactor      отношение количества элементов к размеру массивов
     */
    public DoubleHashingMap(int expectedMaxSize, float loadFactor) {
        this.loadFactor = loadFactor;
        this.capacity = (int) (expectedMaxSize / loadFactor);
        this.keys = allocate(capacity);
        this.values = allocate(capacity);
        this.removed = new boolean[capacity];
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
        for (int i = 0, index = indexOf(key, i++); keys[index] != null; index = indexOf(key, i++)) {
            if (keys[index].equals(key) && !removed[index]) {
                return values[index];
            }
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
        if (capacity * loadFactor <= size) {
            expandArray();
        }

        int i = 0;
        int index;
        for (index = indexOf(key, i++); keys[index] != null; index = indexOf(key, i++)) {
            if (keys[index].equals(key) && !removed[index]) {
                V oldValue = values[index];
                values[index] = value;
                return oldValue;
            }
        }

        keys[index] = key;
        values[index] = value;
        removed[index] = false;
        size++;
        return null;
    }

    @Nullable
    @Override
    public V remove(K key) {
        for (int i = 0, index = indexOf(key, i++); keys[index] != null; index = indexOf(key, i++)) {
            if (keys[index].equals(key) && !removed[index]) {
                removed[index] = true;
                size--;
                return values[index];
            }
        }
        return null;
    }

    @Override
    public void forEach(BiConsumer<K, V> consumer) {
        for (int i = 0; i < capacity; i++) {
            if (keys[i] != null && !removed[i]) {
                consumer.accept(keys[i], values[i]);
            }
        }
    }

    @SuppressWarnings("unchecked")
    private static <T> T[] allocate(int capacity) {
        return (T[]) new Object[capacity];
    }

    private int hash(K key) {
        int h;
        return (key == null) ? 0 : (h = key.hashCode()) ^ (h >>> 16);
    }

    private int hash2(K key) {
        int h;
        return (key == null) ? 0 : (h = hash(key)) ^ (h >>> 16);
    }

    private int indexOf(K key, int i) {
        return ((hash(key) + i * hash2(key)) & 0x7fffffff) % capacity;
    }

    private void expandArray() {
        int oldCapacity = capacity;
        K[] oldKeys = keys;
        V[] oldValues = values;
        boolean[] oldRemoved = removed;
        capacity = capacity * 2;
        size = 0;
        keys = allocate(capacity);
        values = allocate(capacity);
        removed = new boolean[capacity];

        for (int i = 0; i < oldCapacity; i++) {
            if (oldKeys[i] != null && !oldRemoved[i]) {
                put(oldKeys[i], oldValues[i]);
            }
        }
    }
}
