package company.vk.polis.ads.hash;

import java.util.Objects;
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
    private int size;
    private int capacity;
    private final float loadFactor;
    private final int HASH_MAP_CAPACITY = 16;
    private final int GROW_FACTOR = 2;

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
        capacity = (int) (expectedMaxSize / loadFactor);
        keys = allocate(capacity);
        values = allocate(capacity);
        this.loadFactor = loadFactor;
        removed = new boolean[capacity];
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
        int i = 0;
        int index = getIndex(key, 0);
        while (keys[index] != null) {
            if (keys[index].equals(key)) {
                return values[index];
            }
            i++;
            index = getIndex(key, i);
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
            resizeArray();
        }
        int index;
        int i = 0;
        for (index = getIndex(key, i++); keys[index] != null; index = getIndex(key, i++)) {
            if (keys[index].equals(key) && !removed[index]) {
                V old = values[index];
                values[index] = value;
                return old;
            }
        }
        size++;
        keys[index] = key;
        values[index] = value;
        removed[index] = false;
        return null;
    }

    @Nullable
    @Override
    public V remove(K key) {
        int index;
        int i = 0;
        for (index = getIndex(key, i++); keys[index] != null; index = getIndex(key, i++)) {
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
        for (int i = 0; i < keys.length; i++) {
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
        return key.hashCode() & 0x7fffffff;
    }

    private int secondHash(K key) {
        int hash = key.hashCode();
        return (hash) ^ hash >>> HASH_MAP_CAPACITY;
    }

    private int getIndex(K key, int index) {
        return ((hash(key) + (index * secondHash(key))) & 0x7fffffff) % capacity;
    }

    private boolean isPrime(int n) {
        if (n <= 1) {
            return false;
        }
        int limit = (int) Math.sqrt(n);
        for (int i = 2; i < limit; i++) {
            if (n % i == 0) {
                return false;
            }
        }
        return true;
    }

    private int getPrime(int n) {
        int temp = n;
        while (!isPrime(temp)) {
            temp++;
        }
        return temp;
    }

    private void resizeArray() {
        int oldCapacity = capacity;
        K[] arrayWithOldKeys = keys;
        V[] arrayWithOldValues = values;
        capacity = getPrime(capacity * GROW_FACTOR);
        keys = allocate(capacity);
        values = allocate(capacity);
        removed = new boolean[capacity];
        size = 0;
        for (int i = 0; i < oldCapacity; i++) {
            if (arrayWithOldKeys[i] != null && !removed[i]) {
                put(arrayWithOldKeys[i], arrayWithOldValues[i]);
            }
        }
    }
}
