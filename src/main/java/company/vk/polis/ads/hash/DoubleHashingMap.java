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
    private int size;
    private int capacity;
    private boolean[] removed;
    private final float loadFactor;
    private final int expectedMaxSize;
    private boolean firstResizeFlag = true;
    private final int[] primeArray = {17, 31, 61, 127, 257, 521, 1009, 2003, 5003, 1007,
            50021, 100003, 200003, 500009, 1000003, 2000003, 5000011, 10000019, 20000003,
            50000017, 100000007};

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
        this.expectedMaxSize = expectedMaxSize;
        this.capacity = closestPrimeCapacity((int) (expectedMaxSize / loadFactor));
        keys = allocate(capacity);
        values = allocate(capacity);
        removed = new boolean[capacity];
    }

    @Override
    public int size() {
        return size;
    }

    boolean isPrime(int val) {
        for (int i = 2; i * i <= val; i++) {
            if (val % i == 0) {
                return false;
            }
        }
        return true;
    }

    private int closestPrimeCapacity(int val) {
        int i = val;
        while (true) {
            if (isPrime(++i)) {
                return i;
            }
        }
    }

    private int getPrimeCapacity(int val) {
        for (Integer elem : primeArray) {
            if (elem > val) {
                return elem;
            }
        }
        return -1;
    }

    @Override
    public boolean containsKey(K key) {
        int position = hashPos1(key);
        return keys[position] != null;
    }

    private int hashPos1(K key) {
        int hashPos = Objects.hashCode(key) % keys.length;
        if (hashPos < 0) {
            hashPos += keys.length;
        }
        return hashPos;
    }

    private int hashPos2(K key) {
        int hashPos = Objects.hashCode(key) % keys.length;
        if (hashPos < 0) {
            hashPos += keys.length;
        }
        return 31 - hashPos % 31;
    }

    @Nullable
    @Override
    public V get(K key) {
        int position = hashPos1(key);
        int step = hashPos2(key);
        while (keys[position] != null) {
            if (keys[position].equals(key) && !removed[position]) {
                return values[position];
            }
            position = (position + step) % keys.length;
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
        int position = hashPos1(key);
        int step = hashPos2(key);
        V prevValue = null;
        while (true) {
            if (keys[position] == null || removed[position]) {
                keys[position] = key;
                values[position] = value;
                removed[position] = false;
                size++;
                break;
            } else if (keys[position].equals(key)) {
                prevValue = values[position];
                values[position] = value;
                break;
            }
            position = (position + step) % keys.length;
        }
        resizeIfNeed();
        return prevValue;
    }

    private void resizeIfNeed() {
        if ((!firstResizeFlag || expectedMaxSize >= size()) && capacity * loadFactor >= size()) {
            return;
        }
        capacity = getPrimeCapacity(capacity);
        K[] newKeys = allocate(capacity);
        V[] newValues = allocate(capacity);
        for (int i = 0; i < keys.length; i++) {
            if (removed[i]) {
                continue;
            }
            K currKey = keys[i];
            V currValue = values[i];
            int position = hashPos1(currKey);
            int step = hashPos2(currKey);
            while (keys[position] != null) {
                position = (position + step) % keys.length;
            }
            newKeys[position] = currKey;
            newValues[position] = currValue;
        }
        keys = newKeys;
        values = newValues;
        removed = new boolean[capacity];
        firstResizeFlag = false;
    }

    @Nullable
    @Override
    public V remove(K key) {
        int position = hashPos1(key);
        int step = hashPos2(key);
        while (keys[position] != null) {
            if (keys[position].equals(key)) {
                removed[position] = true;
                size--;
                return values[position];
            }
            position = (position + step) % keys.length;
        }
        return null;
    }

    @Override
    public void forEach(BiConsumer<K, V> consumer) {
        Objects.requireNonNull(consumer);
        for (int i = 0; i < keys.length; i++) {
            if (removed[i]) {
                continue;
            }
            K key = keys[i];
            if (Objects.nonNull(key)) {
                consumer.accept(key, values[i]);
            }
        }
    }

    @SuppressWarnings("unchecked")
    private static <T> T[] allocate(int capacity) {
        return (T[]) new Object[capacity];
    }
}
