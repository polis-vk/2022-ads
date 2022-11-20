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

    private int size;
    private int primeNumber;
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
        int initialSize = (int) (expectedMaxSize / loadFactor);
        keys = allocate(initialSize);
        values = allocate(initialSize);
        removed = new boolean[initialSize];
        this.loadFactor = loadFactor;
        setNextPrimeNumberLessThanCapacity();
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean containsKey(K key) {
        int hash = key.hashCode();
        int hash1 = firstHash(hash);
        int hash2 = secondHash(hash);
        int index = getNextIndex(0, hash1, hash2);
        int current = 1;
        while (keys[index] != null) {
            if (keys[index].equals(key)) {
                return !removed[index];
            }
            index = getNextIndex(current++, hash1, hash2);
        }
        return false;
    }

    @Nullable
    @Override
    public V get(K key) {
        int hash = key.hashCode();
        int hash1 = firstHash(hash);
        int hash2 = secondHash(hash);
        int index = getNextIndex(0, hash1, hash2);
        int current = 1;
        while (keys[index] != null) {
            if (keys[index].equals(key)) {
                if (removed[index]) {
                    return null;
                }
                return values[index];
            }
            index = getNextIndex(current++, hash1, hash2);
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
        expandIfNecessary();
        int hash = key.hashCode();
        int hash1 = firstHash(hash);
        int hash2 = secondHash(hash);
        int index = getNextIndex(0, hash1, hash2);
        int current = 1;
        while (keys[index] != null && !removed[index]) {
            if (keys[index].equals(key)) {
                V resultValue = values[index];
                values[index] = value;
                return resultValue;
            }
            index = getNextIndex(current++, hash1, hash2);
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
        int hash = key.hashCode();
        int hash1 = firstHash(hash);
        int hash2 = secondHash(hash);
        int index = getNextIndex(0, hash1, hash2);
        int current = 1;
        while (keys[index] != null) {
            if (keys[index].equals(key)) {
                if (removed[index]) {
                    return null;
                }
                size--;
                removed[index] = true;
                return values[index];
            }
            index = getNextIndex(current++, hash1, hash2);
        }
        return null;
    }

    @Override
    public void forEach(BiConsumer<K, V> consumer) {
        for (int i = 0; i < keys.length; i++) {
            if (!removed[i] && keys[i] != null) {
                consumer.accept(keys[i], values[i]);
            }
        }
    }

    private void expandIfNecessary() {
        if (size >= loadFactor * keys.length) {
            int newSize = keys.length * 2;
            K[] mKeys = keys;
            V[] mValues = values;
            keys = allocate(newSize);
            values = allocate(newSize);
            size = 0;
            for (int i = 0; i < mKeys.length; i++) {
                if (!removed[i] && keys[i] != null) {
                    put(mKeys[i], mValues[i]);
                }
            }
            removed = new boolean[newSize];
            setNextPrimeNumberLessThanCapacity();
        }
    }

    private int firstHash(int objectHash) {
        return objectHash % keys.length;
    }

    // Since the capacity of array is even (we always double capacity when expanding)
    // Our hash needs to be an odd value
    private int secondHash(int objectHash) {
        int hash = Math.abs(primeNumber - (objectHash % primeNumber));
        return hash % 2 == 0 ? hash + 1 : hash;
    }

    private int getNextIndex(int i, int hash1, int hash2) {
        int index = (hash1 + i * hash2) % keys.length;
        return Math.abs(index) % keys.length;
    }

    // This method is only invoked after array was expanded
    // So that prime number can be close to the current array capacity
    private void setNextPrimeNumberLessThanCapacity() {
        for (int n = keys.length - 1; n > 1; n--) {
            if (isPrime(n)) {
                primeNumber = n;
                return;
            }
        }
    }

    private boolean isPrime(int num) {
        int k = 2;
        double sqrt = Math.sqrt(num);
        while (k <= sqrt) {
            if (num % k == 0) {
                return false;
            }
            k++;
        }
        return true;
    }

    @SuppressWarnings("unchecked")
    private static <T> T[] allocate(int capacity) {
        return (T[]) new Object[capacity];
    }
}
