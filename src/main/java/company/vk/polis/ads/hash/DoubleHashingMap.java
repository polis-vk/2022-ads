package company.vk.polis.ads.hash;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.function.BiConsumer;

import org.jetbrains.annotations.Nullable;

/**
 * Map implementation with double hashing collision resolution approach
 *
 * @param <K> key
 * @param <V> value
 */
public final class DoubleHashingMap<K, V> implements Map<K, V> {
   private static final int EXPANSION_COEFFICIENT = 2;
    private final float loadFactor;
    private final int expectedMaxSize;
    private int size;
    // Do not edit these 3 instance fields!!!
    private K[] keys;
    private V[] values;
    private boolean[] removed;

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
        this.expectedMaxSize = expectedMaxSize;
        this.loadFactor = loadFactor;
        int initialLength = (int) (expectedMaxSize / loadFactor);
        keys = allocate(initialLength);
        values = allocate(initialLength);
        removed = new boolean[initialLength];
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean containsKey(K key) {
        return getIndex(key) != -1;
    }

    @Nullable
    @Override
    public V get(K key) {
        int keyIndex = getIndex(key);
        if (keyIndex == -1) {
            return null;
        }
        return values[keyIndex];
    }

    /**
     * Если capacity * loadFactor == size() и будет добавлен новый ключ,
     * то нужно выполнить расширение массивов
     */
    @Nullable
    @Override
    public V put(K key, V value) {
        int keyIndex = getIndex(key);
        if (keyIndex == -1) {
            if (size >= loadFactor * keys.length && size >= expectedMaxSize) {
                expand();
            }
            put(keys, values, removed, key, value);
            size++;
            return null;
        }
        if (removed[keyIndex]) {
            // TODO: remove
            keys[keyIndex] = key;
            values[keyIndex] = value;
            removed[keyIndex] = false;
            size++;
            return null;
        }
        V oldValue = values[keyIndex];
        values[keyIndex] = value;
        return oldValue;
    }

    @Nullable
    @Override
    public V remove(K key) {
        int keyIndex = getIndex(key);
        if (keyIndex == -1) {
            return null;
        }
        removed[keyIndex] = true;
        size--;
        // TODO: delete value
        return values[keyIndex];
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

    private int hashIndex(int hashCode, int m) {
        return (hashCode & 0x7fffffff) % m;
    }

    private void expand() {
        K[] newKeys = allocate(keys.length * EXPANSION_COEFFICIENT);
        V[] newValues = allocate(values.length * EXPANSION_COEFFICIENT);
        boolean[] newRemoved = new boolean[removed.length * EXPANSION_COEFFICIENT];
        for (int i = 0; i < keys.length; i++) {
            if (keys[i] != null && !removed[i]) {
                put(newKeys, newValues, newRemoved, keys[i], values[i]);
            }
        }
        keys = newKeys;
        values = newValues;
        removed = newRemoved;
    }

    private int getIndex(K key) {
        int tempIndex = hashIndex(key.hashCode(), keys.length);
        while (keys[tempIndex] != null && !keys[tempIndex].equals(key)) {
            // TODO: change step
            tempIndex = hashIndex(tempIndex + hash2(keys[tempIndex]), keys.length);
        }
        if (keys[tempIndex] == null) {
            return -1;
        }
        return tempIndex;
    }

    private void put(K[] keys, V[] values, boolean[] removed, K key, V value) {
        int tempIndex = hashIndex(key.hashCode(), keys.length);
        while (keys[tempIndex] != null && !removed[tempIndex]) {
            tempIndex = hashIndex(tempIndex + hash2(keys[tempIndex]), keys.length);
        }
        keys[tempIndex] = key;
        values[tempIndex] = value;
        removed[tempIndex] = false;
    }

    private Set<Integer> indexes = new HashSet<>();

    private int hash2(K key) {
        return 1;
    }
}
