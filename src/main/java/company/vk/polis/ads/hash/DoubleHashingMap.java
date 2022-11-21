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

    private int expectedMaxSize; // threshold
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
        this.expectedMaxSize = expectedMaxSize;
        size = 0;
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
        int j = 0;
        int i = indexOf(key, j++);
        while (keys[i] != null) {
            if (keys[i].equals(key)) {
                if (removed[i]) {
                    return null;
                }
                return values[i];
            }
            i = indexOf(key, j++);
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
        if (size + 1 > expectedMaxSize) {
            resize();
        }

        int j = 0;
        int i = indexOf(key, j++);
        while (!removed[i] && keys[i] != null) {
            if (keys[i].equals(key)) {
                var oldValue = values[i];
                values[i] = value;
                return oldValue;
            }
            i = indexOf(key, j++);
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
        int j = 0;
        int i = indexOf(key, j++);
        while (keys[i] != null) {
            if (keys[i].equals(key)) {
                if (removed[i]) {
                    return null;
                }
                removed[i] = true;
                size--;
                return values[i];
            }
            i = indexOf(key, j++);
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

    private int hash1(K key) {
        int h;
        return (key == null) ? 0 : (h = key.hashCode()) ^ (h >>> 16);
    }

    private int hash2(K key) {
        long sum = 1;
        long mult = 1;
        int i = key.toString().length();
        mult = (i % 4 == 0) ? 1 : mult * 256;
        sum += key.hashCode() * mult;

        return (int) sum;
    }

    private void resize() {
        int oldCapacity = capacity;
        var oldKeys = keys;
        var oldValues = values;

        capacity = generatePrime(capacity * 2);
        expectedMaxSize = capacity;
        keys = allocate(capacity);
        values = allocate(capacity);
        removed = new boolean[capacity];
        size = 0;

        for (int i = 0; i < oldCapacity; i++) {
            if (oldKeys[i] != null) {
                put(oldKeys[i], oldValues[i]);
            }
        }
    }

    private int indexOf(K key, int i) {
        return Math.abs((hash1(key) + i * hash2(key)) % capacity);
    }

    //generates nearest greater prime number
    private int generatePrime(int n) {
        int pr;
        for (pr = n; pr < Integer.MAX_VALUE; pr++) {
            if (isPrime(pr)) {
                break;
            }
        }
        return pr;
    }

    private boolean isPrime(int n) {
        for (int i = 2; i * i <= n; i++) {
            if (n % i == 0) {
                return false;
            }
        }
        return true;
    }
}
