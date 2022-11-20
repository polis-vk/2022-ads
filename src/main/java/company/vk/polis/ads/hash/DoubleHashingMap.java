package company.vk.polis.ads.hash;

import java.util.Objects;
import java.util.Scanner;
import java.util.function.BiConsumer;

import org.jetbrains.annotations.Nullable;

/**
 * Map implementation with double hashing collision resolution approach
 *
 * @param <K> key
 * @param <V> value
 */
public final class DoubleHashingMap<K, V> implements Map<K, V> {
    static final int GROW_FACTOR = 2;
    static final int DEFAULT_INITIAL_CAPACITY = 17;
    static final int DEFAULT_PRIME_NUM = 100003;

    // Do not edit these 3 instance fields!!!
    private K[] keys;
    private V[] values;
    private boolean[] removed;
    private final float loadFactor;
    private int size;
    private int capacity;

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
        int capacity = Math.max((int) (expectedMaxSize / loadFactor), DEFAULT_INITIAL_CAPACITY);
//        capacity = isPrime(capacity) ? capacity : getNextPrime(capacity);

        keys = allocate(capacity);
        values = allocate(capacity);
        removed = new boolean[capacity];
        this.loadFactor = loadFactor;
        this.capacity = capacity;
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
        int step = 0;
        int index = getPotentialIndex(key, step++);

        while (keys[index] != null) {
            if (keys[index].equals(key) && !removed[index]) {
                return values[index];
            }
            index = getPotentialIndex(key, step++);
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
        if ((capacity * loadFactor) <= size) {
            increaseCapacity();
        }

        int step = 0;
        int index = getPotentialIndex(key, step++);
        while (keys[index] != null) {
            if (keys[index].equals(key) && !removed[index]) {
                V oldValue = values[index];
                values[index] = value;
                return oldValue;
            }
            index = getPotentialIndex(key, step++);
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
        int step = 0;
        int index = getPotentialIndex(key, step++);
        while (keys[index] != null) {
            if (keys[index].equals(key) && !removed[index]) {
                size--;
                removed[index] = true;
                return values[index];
            }
            index = getPotentialIndex(key, step++);
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

    private void increaseCapacity() {
        size = 0;
        K[] tempKeys = keys;
        V[] tempValues = values;
        boolean[] tempRemoved = removed;

        int nextPrime = getNextPrime(capacity * GROW_FACTOR);
        capacity = nextPrime;
        keys = allocate(nextPrime);
        values = allocate(nextPrime);
        removed = new boolean[nextPrime];

        for (int i = 0; i < tempKeys.length; i++) {
            if (!tempRemoved[i] && tempKeys[i] != null) {
                put(tempKeys[i], tempValues[i]);
            }
        }
    }

    private int getPotentialIndex(K key, int step) {
        return ((hash1(key) + step * hash2(key)) & 0x7fffffff) % capacity;
    }

    private int hash1(K key) {
        return ((Objects.hashCode(key) & 0x7fffffff) % capacity);
    }

    private int hash2(K key) {
        int hashValue = (Objects.hashCode(key) & 0x7fffffff) % DEFAULT_PRIME_NUM;
        return capacity % (hashValue == 0 ? 1 : hashValue % DEFAULT_PRIME_NUM) + 1;
    }

    private boolean isPrime(int num) {
        for (int i = 2; i * i <= num; i++) {
            if (num % i == 0) {
                return false;
            }
        }
        return true;
    }

    private int getNextPrime(int num) {
        int currentNum = num;
        while (true) {
            if (isPrime(currentNum)) {
                return currentNum;
            }
            currentNum++;
        }
    }

}
