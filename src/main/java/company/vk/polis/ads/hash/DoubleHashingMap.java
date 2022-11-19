package company.vk.polis.ads.hash;

import java.util.Arrays;
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

    // Массив CAPACITY - простые числа, каждое следующее примерно в 2 раза больше предыдущего. Последнее число - степень двойки(2^21)
    private final static int[] CAPACITY = {3, 7, 13, 29, 59, 113, 227, 457, 911, 1823, 3643, 7283, 14563, 29123, 58243,
            116_483, 232_963, 465_929, 931_859, 2_097_152};
    private static final int GROW_FACTOR = 2;

    private K[] keys;
    private V[] values;
    private boolean[] removed;
    private final double loadFactor;
    private int indexOfPrimeCapacity;
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
        indexOfPrimeCapacity = Math.abs(Arrays.binarySearch(CAPACITY, capacity));
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
        int hash1 = firstHash(key);
        int hash2 = secondHash(key);
        int iter = 0;
        for (int i = getIndex(hash1); keys[i] != null; i = getIndex(hash1 + (++iter) * hash2)) {
            if (keys[i].equals(key) && !removed[i]) {
                return values[i];
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
        if ((float) size / capacity >= loadFactor) {
            resize();
        }
        int hash1 = firstHash(key);
        int hash2 = secondHash(key);
        int i = getIndex(hash1);
        int iter = 0;
        while (keys[i] != null) {
            if (key.equals(keys[i])) {
                V prevValue = values[i];
                values[i] = value;
                return prevValue;
            }
            if (removed[i]) {
                break;
            }
            i = getIndex(hash1 + ((++iter)) * hash2);
        }
        keys[i] = key;
        values[i] = value;
        removed[i] = false;
        size++;
        return null;
    }

    @Nullable
    @Override
    public V remove(K key) {
        int hash1 = firstHash(key);
        int hash2 = secondHash(key);
        int iter = 0;
        for (int i = getIndex(hash1); keys[i] != null; i = getIndex(hash1 + (++iter) * hash2)) {
            if (keys[i].equals(key)) {
                removed[i] = true;
                size--;
                return values[i];
            }
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

    private int firstHash(K key) {
        int hash = key.hashCode();
        return hash ^ (hash >>> 16);
    }

    /*
     * Поскольку capacity либо простое число, либо степень двойки,
     * хотим всегда получать нечетный второй хеш, чтобы он был взаимно простым с capacity.
     */
    private int secondHash(K key) {
        int hash = capacity - key.hashCode();
        return hash % 2 == 0 ? ++hash : capacity - key.hashCode();
    }

    private int getIndex(int hash) {
        return (hash & 0x7fffffff) % capacity;
    }

    /*
     * Исходим из предположения, что начальное значение capacity попадет в наш диапазон чисел.
     * Если выйдем за диапазон массива - пойдем по схеме capacity * GROW_FACTOR, тогда capacity всегда степеь двойки.
     */
    private void resize() {
        if (indexOfPrimeCapacity > CAPACITY.length) {
            capacity = capacity * GROW_FACTOR;
        } else {
            capacity = CAPACITY[indexOfPrimeCapacity++];
        }
        K[] newKeys = keys;
        V[] newValues = values;
        boolean[] tempRemoved = removed;
        keys = allocate(capacity);
        values = allocate(capacity);
        removed = new boolean[capacity];
        int iter = 0;
        int index;
        size = 0;

        for (int i = 0; i < newKeys.length; i++) {
            /* Нам надо перезаписать флаги уже под новыми индексами(т.к хеши и, соответсвенно, индексы зависят от capacity)
            * т.к индекс флага должен соответсвовать индексу его пары, нам нужно найти индекс по алгоритму put,
            * ведь именно по нему в следующем if мы положим пару
            */
            if (tempRemoved[i]){
                int hash1 = firstHash(newKeys[i]);
                int hash2 = secondHash(newKeys[i]);
                index = getIndex(firstHash(newKeys[i]));
                while (keys[i] != null) {
                    if (newKeys[i].equals(keys[i])) {
                        removed[i] = tempRemoved[i];
                        break;
                    }
                    if (removed[index]) {
                        removed[i] = tempRemoved[i];
                        break;
                    }
                    index = getIndex(hash1 + ((++iter)) * hash2);
                }
            }
            if (newKeys[i] != null) {
                put(newKeys[i], newValues[i]);
            }
        }
    }

    @SuppressWarnings("unchecked")
    private static <T> T[] allocate(int capacity) {
        return (T[]) new Object[capacity];
    }
}
