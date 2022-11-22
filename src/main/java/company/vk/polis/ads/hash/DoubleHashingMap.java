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
    private static final int INCREASE_COEFFICIENT = 2;
    private int capacity;
    private float loadFactor;
    private int size;

    // Do not edit these 3 instance fields!!!
    private K[] keys;
    private V[] values;
    private boolean[] removed;

    /**
     * Создает новый ассоциативный массив в соответствии с expectedMaxSize и loadFactor.
     * Сразу выделяет начальное количество памяти на основе expectedMaxSize и loadFactor.
     *
     * @param expectedMaxSize Ожидаемое максимальное количество элементов в ассоциативном массиве.
     *                        Это значит, что capacity - размер массивов под капотом -
     *                        не будет увеличиваться до тех пор, пока количество элементов
     *                        не станет больше чем expectedMaxSize
     * @param loadFactor      отношение количества элементов к размеру массивов
     */
    public DoubleHashingMap(int expectedMaxSize, float loadFactor) {
        this.capacity = (int) (expectedMaxSize / loadFactor);
        this.loadFactor = loadFactor;
        keys = allocate(this.capacity);
        values = allocate(this.capacity);
        removed = new boolean[this.capacity];
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean containsKey(K key) {
        int hash1 = hash1(key);
        int hash2 = hash2(key);

        while (keys[hash1] != null) {
            if (keys[hash1].equals(key) && !removed[hash1]) {
                return true;
            }
            hash1 += hash2;
            hash1 %= capacity;
        }

        return false;
    }

    @Nullable
    @Override
    public V get(K key) {
        int hash1 = hash1(key);
        int hash2 = hash2(key);

        while (keys[hash1] != null) {
            if (keys[hash1].equals(key) && !removed[hash1]) {
                return values[hash1];
            }
            hash1 += hash2;
            hash1 %= capacity;
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
        increaseIfNeed();

        int hash1 = hash1(key);
        int hash2 = hash2(key);
        V resultValue;

        while (keys[hash1] != null) {
            if (!removed[hash1] && keys[hash1].equals(key)) {
                resultValue = values[hash1];
                values[hash1] = value;
                return resultValue;
            }
            hash1 += hash2;
            hash1 %= capacity;
        }

        keys[hash1] = key;
        values[hash1] = value;
        removed[hash1] = false;
        size++;

        return null;
    }

    @Nullable
    @Override
    public V remove(K key) {
        int hash1 = hash1(key);
        int hash2 = hash2(key);

        while (keys[hash1] != null) {
            if (!removed[hash1] && keys[hash1].equals(key)) {
                removed[hash1] = true;
                size--;
                return values[hash1];
            }
            hash1 += hash2;
            hash1 %= capacity;
        }

        return null;
    }

    @Override
    public void forEach(BiConsumer<K, V> consumer) {
        for (int i = 0; i < size; i++) {
            if (!removed[i] && keys[i] != null) {
                consumer.accept(keys[i], values[i]);
            }
        }
    }

    private int getIndexByHash (K key) {
        int hash1 = hash1(key);
        int hash2 = hash2(key);

        while (keys[hash1] != null && !keys[hash1].equals(key)) {
            hash1 += hash2;
            hash1 %= capacity;
        }

        return hash1;
    }

    private void increaseIfNeed() {
        if (size() >= capacity * loadFactor) {
            K[] newKeys = allocate(getPrime(capacity * INCREASE_COEFFICIENT));
            V[] newValues = allocate(getPrime(capacity * INCREASE_COEFFICIENT));
            boolean[] newRemoved = new boolean[getPrime(capacity * INCREASE_COEFFICIENT)];

            System.arraycopy(keys, 0, newKeys, 0, size());
            System.arraycopy(values, 0, newValues, 0, size());
            System.arraycopy(removed, 0, newRemoved, 0, size());

            this.keys = newKeys;
            this.values = newValues;
            this.removed = newRemoved;
        }
    }

    private int hash1(K key) {
        return (key.hashCode() & 0x7ffffff) % capacity;
    }

    private int hash2(K key) {
        int primeSize = getPrime(capacity);
        int hashValue = hash1(key);
        return primeSize - hashValue % primeSize;
    }

    private int getPrime(int capacity) {
        int fact;
        for (int i = capacity - 1; i >= 1; i--) {
            fact = 0;
            for (int j = 2; j <= (int) Math.sqrt(i); j++) {
                if (i % j == 0) {
                    fact++;
                }
            }
            if (fact == 0) {
                return i;
            }
        }
        return 3;
    }

    @SuppressWarnings("unchecked")
    private static <T> T[] allocate(int capacity) {
        return (T[]) new Object[capacity];
    }
}
