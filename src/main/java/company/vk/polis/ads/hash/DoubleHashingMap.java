package company.vk.polis.ads.hash;

import org.jetbrains.annotations.Nullable;

import java.util.Objects;
import java.util.function.BiConsumer;

/**
 * Map implementation with double hashing collision resolution approach
 *
 * @param <K> key
 * @param <V> value
 */
public final class DoubleHashingMap<K, V> implements Map<K, V> {
    private final static int STEP_INIT_VALUE = 0;
    private final static int GROW_FACTOR = 2;

    // Do not edit these 3 instance fields!!!
    private K[] keys;
    private V[] values;
    private boolean[] removed;
    private final float loadFactor;
    private int size;

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
        int capacity = (int) (expectedMaxSize / loadFactor);
        keys = allocate(capacity);
        values = allocate(capacity);
        removed = new boolean[capacity];
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
        int step = STEP_INIT_VALUE;
        int currIndex = getIndex(key, step++);
        while (keys[currIndex] != null) {
            if (keys[currIndex].equals(key) && !removed[currIndex]) {
                return values[currIndex];
            }
            currIndex = getIndex(key, step++);
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
        if ((float) size / keys.length >= loadFactor) {
            resize();
        }

        int step = STEP_INIT_VALUE;
        int currIndex = getIndex(key, step++);
        while (keys[currIndex] != null) {
            if (removed[currIndex]) {
                keys[currIndex] = key;
                values[currIndex] = value;
                removed[currIndex] = false;
                size++;
                return null;
            }

            if (keys[currIndex].equals(key)) {
                final V oldValue = values[currIndex];
                values[currIndex] = value;
                removed[currIndex] = false;
                return oldValue;
            }
            currIndex = getIndex(key, step++);
        }
        keys[currIndex] = key;
        values[currIndex] = value;
        removed[currIndex] = false;
        size++;
        return null;
    }

    @Nullable
    @Override
    public V remove(K key) {
        int step = STEP_INIT_VALUE;
        int currIndex = getIndex(key, step++);
        while (keys[currIndex] != null) {
            if (keys[currIndex].equals(key)) {
                size--;
                removed[currIndex] = true;
                return values[currIndex];
            }
            currIndex = getIndex(key, step++);
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

    private int getIndex(K key, int step) {
        return ((getHash(key) + step * (Objects.hashCode(key)) + 1) & 0x7fffffff) % keys.length;
    }

    private int getHash(K key) {
        return Objects.hashCode(key) % (keys.length - 1) + 1;
    }

    private void resize() {
        size = 0;
        K[] temporaryKeys = keys;
        V[] temporaryValues = values;
        boolean[] temporaryFlags = removed;
        keys = allocate(keys.length * GROW_FACTOR);
        values = allocate(values.length * GROW_FACTOR);
        removed = new boolean[removed.length * GROW_FACTOR];

        K currKey;
        V currValue;
        int currIndex;
        int step = STEP_INIT_VALUE;
        for (int i = 0; i < temporaryKeys.length; i++) {
            currKey = temporaryKeys[i];
            currValue = temporaryValues[i];
            if (currKey != null && currValue != null) {
                put(currKey, currValue);
            }

            do {
                currIndex = getIndex(keys[i], step++);
            } while (removed[currIndex]);
            removed[currIndex] = temporaryFlags[i];
        }
    }
}

