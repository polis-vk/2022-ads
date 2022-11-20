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
    private static final boolean SLOT_TAKEN = true;
    private static final boolean SLOT_AVAILABLE = false;
    private K[] keys;
    private V[] values;
    private boolean[] removed;
    private final float loadFactor;
    private final int expectedMaxSize;
    private int size;
    private static final int[] primes = new int[]{ 2, 5, 11, 17, 37, 67, 131, 257, 521, 1031, 2053, 4099, 8209, 16411,
            32771, 65537, 131101, 262147, 524309, 1048583, 2097169, 4194319, 8388617, 16777259, 33554467, 67108879,
            134217757, 268435459, 536870923, 1073741827 };

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
        keys = allocate(getPrimeBefore(Math.round(expectedMaxSize / loadFactor)));
        values = allocate(keys.length);
        removed = new boolean[keys.length];
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean containsKey(K key) {
        int hash2 = getHash2(key);
        for (int i = getHash1(key); keys[i] != null; i = (i + hash2) % keys.length) {
            if (removed[i] == SLOT_TAKEN && keys[i].equals(key)) {
                return true;
            }
        }
        return false;
    }

    @Nullable
    @Override
    public V get(K key) {
        int hash2 = getHash2(key);
        for (int i = getHash1(key); keys[i] != null; i = (i + hash2) % keys.length) {
            if (removed[i] == SLOT_TAKEN && keys[i].equals(key)) {
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
        if (expectedMaxSize == size()) {
            resize();
        } else if (size() > expectedMaxSize && Math.round(keys.length * loadFactor) == size()) {
            resize();
        }

        int i = getHash1(key);
        int hash2 = getHash2(key);
        for (; removed[i] == SLOT_TAKEN; i = (i + hash2) % keys.length) {
            if (keys[i].equals(key)) {
                V oldValue = values[i];
                values[i] = value;
                return oldValue;
            }
        }

        V oldValue = values[i];
        keys[i] = key;
        values[i] = value;
        removed[i] = SLOT_TAKEN;
        size++;
        return oldValue;
    }

    @Nullable
    @Override
    public V remove(K key) {
        int i = getHash1(key);
        int hash2 = getHash2(key);
        for (; keys[i] != null; i = (i + hash2) % keys.length) {
            if (removed[i] == SLOT_TAKEN && keys[i].equals(key)) {
                removed[i] = SLOT_AVAILABLE;
                size--;
                return values[i];
            }
        }
        return null;
    }

    @Override
    public void forEach(BiConsumer<K, V> consumer) {
        for (int i = 0; i < keys.length; i++) {
            if (removed[i] == SLOT_TAKEN) {
                consumer.accept(keys[i], values[i]);
            }
        }
    }

    @SuppressWarnings("unchecked")
    private static <T> T[] allocate(int capacity) {
        return (T[]) new Object[capacity];
    }

    private int getNextCapacity() {
        int a = 1;
        int pow = 0;
        while (a < keys.length) {
            a <<= 1;
            pow++;
        }
        return primes[pow - 1];
    }

    private static boolean isPrime(int a) {
        for (int i = 2; i <= Math.sqrt(a); i++) {
            if (a % i == 0) {
                return false;
            }
        }
        return true;
    }

    private static int getPrimeBefore(int a) {
        for (int n = a; n >= 2; n--) {
            if (isPrime(n)) {
                return n;
            }
        }
        return 1;
    }

    private int getHash1(K key) {
        return (key.hashCode() & 0x7fffffff) % keys.length;
    }

    private int getHash2(K key) {
        return (key.hashCode() & 0x7fffffff) % (keys.length - 1) + 1;
    }

    private void resize() {
        K[] oldKeys = keys;
        V[] oldValues = values;
        boolean[] oldRemoved = removed;

        keys = allocate(getNextCapacity());
        values = allocate(keys.length);
        removed = new boolean[keys.length];

        size = 0;
        for (int i = 0; i < oldKeys.length; i++) {
            if (oldRemoved[i] == SLOT_TAKEN) {
                put(oldKeys[i], oldValues[i]);
            }
        }
    }
}
