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
    private final float loadFactor;
    private int capacity;
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
        this.loadFactor = loadFactor;
        capacity = (int) (expectedMaxSize / loadFactor);
        keys = allocate(capacity);
        values = allocate(capacity);
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
        int index = getIndex(key, 0);
        int i = 0;
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
        if (capacity * loadFactor <= size()) {
            resize();
        }
        int index = getIndex(key, 0);
        int i = 0;
        while (keys[index] != null) {
            if (keys[index].equals(key) && !removed[index]) {
                V oldValue = values[index];
                values[index] = value;
                return oldValue;
            }
            i++;
            index = getIndex(key, i);
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
        int index = getIndex(key, 0);
        int i = 0;
        while (keys[index] != null) {
            if (keys[index].equals(key) && !removed[index]) {
                removed[index] = true;
                size--;
                return values[index];
            }
            i++;
            index = getIndex(key, i);
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

    private int hash1(K key) {
        return Objects.hashCode(key) & 0x7fffffff;
    }

    private int hash2(K key) {
        // HashMap
        int h;
        return (h = key.hashCode()) ^ h >>> 16;
    }

    private int getIndex(K key, int i) {
        return ((hash1(key) + i * hash2(key)) & 0x7fffffff) % capacity;
    }

    private void resize() {
        K[] oldKeys = keys;
        V[] oldValues = values;
        size = 0;
        capacity = getPrime(capacity);
        keys = allocate(capacity);
        values = allocate(capacity);
        removed = new boolean[capacity];
        for (int i = 0; i < oldKeys.length; i++) {
            if (oldKeys[i] != null && !removed[i]) {
                put(oldKeys[i], oldValues[i]);
            }
        }
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
        int num = n;
        while (!isPrime(num)) {
            num++;
        }
        return num;
    }
}
