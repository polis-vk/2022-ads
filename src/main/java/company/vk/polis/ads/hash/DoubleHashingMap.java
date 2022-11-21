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
    private int size = 0;
    private int capacity;
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
        int hash1 = getHash(key, true);
        int hash2 = getHash(key, false);
        int i = 0;
        int index = getBucketIndex(hash1);
        while (keys[index] != null) {
            if (!keys[index].equals(key) || removed[index]) {
                index = getBucketIndex(hash1 + (++i) * hash2);
            } else {
                return values[index];
            }
        }
        return null;
    }

    private int getBucketIndex(int hash) {
        return (hash & 0x7ffffff) % capacity;
    }

    private int getHash(K key, boolean first) {
        int hashCode = key.hashCode();
        hashCode %= capacity;
        if (hashCode < 0) {
            hashCode += capacity;
        }
        return first ? hashCode : capacity - hashCode % capacity;
    }

    /**
     * Если capacity * loadFactor == size() и будет добавлен новый ключ,
     * то нужно выполнить расширение массивов
     */
    @Nullable
    @Override
    public V put(K key, V value) {
        if (capacity * loadFactor <= size) {
            K[] tempKeys = keys;
            V[] tempValues = values;
            int tempCapacity = capacity;
            capacity = getNextPrime(capacity << 1);
            size = 0;
            keys = allocate(capacity);
            values = allocate(capacity);
            removed = new boolean[capacity];
            for (int i = 0; i < tempCapacity; i++) {
                if (removed[i] || tempKeys[i] == null) {
                    continue;
                }
                put(tempKeys[i], tempValues[i]);
            }
        }
        int hash1 = getHash(key, true);
        int hash2 = getHash(key, false);
        int i = 0;
        int index = getBucketIndex(hash1);
        while (keys[index] != null) {
            if (key.equals(keys[index])) {
                V out = values[index];
                values[index] = value;
                return out;
            }
            if (removed[index]) {
                break;
            }
            index = getBucketIndex(hash1 + ((++i)) * hash2);
        }
        size++;
        keys[index] = key;
        values[index] = value;
        removed[index] = false;
        return null;
    }

    private int getNextPrime(int n) {
        int prime = n;
        while (true) {
            if (isPrime(prime)) {
                return prime;
            }
            prime++;
        }
    }

    private boolean isPrime(int n) {
        if (n <= 1) {
            return false;
        }
        for (int prime = 2; prime < Math.sqrt(n); prime++) {
            if (prime * prime > n) {
                return true;
            }
            if (n % prime == 0) {
                return false;
            }
        }
        return true;
    }

    @Nullable
    @Override
    public V remove(K key) {
        int hash1 = getHash(key, true);
        int hash2 = getHash(key, false);
        int i = 0;
        int index = getBucketIndex(hash1);
        while (keys[index] != null) {
            if (keys[index].equals(key)) {
                size--;
                removed[index] = true;
                return values[index];
            }
            index = getBucketIndex(hash1 + (++i) * hash2);
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
}
