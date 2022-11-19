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
    private int size = 0;
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
        int hash = hash(key);
        int hash2 = hash2(key);
        int i = 0;

        for (int index = getIndex(hash, hash2, i++); keys[index] != null; index = getIndex(hash, hash2, i++)) {
            if (!removed[index] && keys[index].equals(key)) {
                return true;
            }
        }
        return false;
    }

    @Nullable
    @Override
    public V get(K key) {
        int hash = hash(key);
        int hash2 = hash2(key);
        int i = 0;

        for (int index = getIndex(hash, hash2, i++); keys[index] != null; index = getIndex(hash, hash2, i++)) {
            if (!removed[index] && keys[index].equals(key)) {
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
        resizeIfNeed();

        int hash = hash(key);
        int hash2 = hash2(key);
        int i = 0;

        int index;
        for (index = getIndex(hash, hash2, i++); keys[index] != null; index = getIndex(hash, hash2, i++)) {
            if (!removed[index] && keys[index].equals(key)) {
                V result = values[index];
                values[index] = value;
                return result;
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
        int hash = hash(key);
        int hash2 = hash2(key);
        int i = 0;

        for (int index = getIndex(hash, hash2, i++); keys[index] != null; index = getIndex(hash, hash2, i++)) {
            if (!removed[index] && keys[index].equals(key)) {
                size--;
                removed[index] = true;
                return values[index];
            }
        }
        return null;
    }

    @Override
    public void forEach(BiConsumer<K, V> consumer) {
        for (int i = 0; i < capacity; i++) {
            if (!removed[i] && keys[i] != null) {
                consumer.accept(keys[i], values[i]);
            }
        }
    }

    @SuppressWarnings("unchecked")
    private static <T> T[] allocate(int capacity) {
        return (T[]) new Object[capacity];
    }

    private int hash(K key) {
        return (key.hashCode() >> 4) % capacity + 1;
    }

    private int hash2(K key) {
        int hashCode = key.hashCode() & 0x7fffffff ;
        hashCode %= 12317;
        if (hashCode == 0) {
            hashCode++;
        }
        return capacity % (hashCode % 12317) + 1;
    }

    private int getIndex(int hash, int hash2, int i) {
        return ((hash + i * hash2) & 0x7fffffff) % capacity;
    }

    private void resizeIfNeed() {
        if (capacity * loadFactor > size) {
            return;
        }

        int oldCapacity = capacity;
        K[] oldKeys = keys;
        V[] oldValues = values;

        capacity = getNextPrime(capacity * 2);
        size = 0;
        keys = allocate(capacity);
        values = allocate(capacity);
        removed = new boolean[capacity];

        for (int i = 0; i < oldCapacity; i++) {
            if (!removed[i] && oldKeys[i] != null) {
                put(oldKeys[i], oldValues[i]);
            }
        }
    }

    private int getNextPrime(int n) {
        int curNum = n;
        while (true) {
            if (isPrime(curNum)) {
                return curNum;
            }
            curNum++;
        }
    }

    private boolean isPrime(int n) {
        if (n <= 1) {
            return false;
        }

        int limit = (int) Math.sqrt(n);
        for (int i = 2; i < limit; i += 1) {
            if (n % i == 0) {
                return false;
            }
        }

        return true;
    }
}
