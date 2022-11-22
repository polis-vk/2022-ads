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
    private final float loadFactor;
    private int expectedMaxSize;
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
        capacity = getPreviousPrime(capacity);
        keys = allocate(capacity);
        values = allocate(capacity);
        removed = new boolean[capacity];
        this.loadFactor = loadFactor;
        this.expectedMaxSize = expectedMaxSize;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean containsKey(K key) {
        int hash = hash(key);
        int hash2 = hash2(key);
        for (int i = 0; i < keys.length; i++) {
            if (keys[hash] == null) {
                return false;
            }
            if (keys[hash].equals(key) && !removed[hash]) {
                return true;
            }
            hash = (hash + hash2) % keys.length;
        }
        return false;
    }

    @Nullable
    @Override
    public V get(K key) {
        int hash = hash(key);
        int hash2 = hash2(key);
        for (int i = 0; i < keys.length; i++) {
            if (keys[hash] == null) {
                return null;
            }
            if (keys[hash].equals(key) && !removed[hash]) {
                return values[hash];
            }
            hash = (hash + hash2) % keys.length;
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
        if (size >= keys.length * loadFactor) {
            resize();
        }
        int hash = hash(key);
        int hash2 = hash2(key);
        for (int i = 0; i < keys.length; i++) {
            if (removed[hash] || keys[hash] == null) {
                break;
            }
            if (keys[hash].equals(key)) {
                V oldValue = values[hash];
                values[hash] = value;
                return oldValue;
            }
            hash = (hash + hash2) % keys.length;
        }
        keys[hash] = key;
        values[hash] = value;
        removed[hash] = false;
        size++;
        return null;
    }

    @Nullable
    @Override
    public V remove(K key) {
        int hash = hash(key);
        int hash2 = hash2(key);
        for (int i = 0; i < keys.length; i++) {
            if (keys[hash] == null) {
                return null;
            }
            if (keys[hash].equals(key)) {
                removed[hash] = true;
                size--;
                return values[hash];
            }
            hash = (hash + hash2) % keys.length;
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

    private int hash(K key) {
        return Math.abs(key.hashCode() % keys.length);
    }

    private int hash2(K key) {
        return Math.abs(key.hashCode() % (keys.length - 1) + 1);
    }

    private void resize() {
        K[] tempKeys = keys;
        V[] tempValues = values;
        int newCapacity = keys.length * 2;
        newCapacity = getPreviousPrime(newCapacity);
        keys = allocate(newCapacity);
        values = allocate(newCapacity);
        removed = new boolean[newCapacity];
        size = 0;
        for (int i = 0; i < tempKeys.length; i++) {
            if (tempKeys[i] != null) {
                put(tempKeys[i], tempValues[i]);
            }
        }
    }

    private int getPreviousPrime(int num) {
        for (int i = num - 1; i > 1; i--) {
            if (isPrime(i)) {
                return i;
            }
        }
        return 2;
    }

    private boolean isPrime(int num) {
        for (int i = 2, s = (int) (Math.sqrt(num)); i <= s; i++) {
            if (num % i == 0) {
                return false;
            }
        }
        return num > 1;
    }

    @SuppressWarnings("unchecked")
    private static <T> T[] allocate(int capacity) {
        return (T[]) new Object[capacity];
    }
}
