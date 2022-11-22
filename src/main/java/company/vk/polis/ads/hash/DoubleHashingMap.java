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
    private float loadFactor;
    private int expectedMaxSize;
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
        int maxSize = getPrime(Math.round(expectedMaxSize / loadFactor));
        keys = allocate(maxSize);
        values = allocate(maxSize);
        removed = new boolean[maxSize];
        this.loadFactor = loadFactor;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean containsKey(K key) {
        int i = hash(key);
        int h2 = hash2(key);
        while (true) {
            if (key.equals(keys[i]) && !removed[i]) {
                return true;
            }
            if (keys[i] == null && !removed[i]) {
                return false;
            }
            i = (i + h2) % keys.length;
        }
    }


    @Nullable
    @Override
    public V get(K key) {
        int i = hash(key);
        int h2 = hash2(key);
        while (true) {
            if (key.equals(keys[i]) && !removed[i]) {
                return values[i];
            }
            if (keys[i] == null && !removed[i]) {
                return null;
            }
            i = (i + h2) % keys.length;
        }
    }

    /**
     * Если capacity * loadFactor == size() и будет добавлен новый ключ,
     * то нужно выполнить расширение массивов
     */
    @Nullable
    @Override
    public V put(K key, V value) {
        int i = hash(key);
        int h2 = hash2(key);
        while (true) {
            if (key.equals(keys[i]) && !removed[i]) {
                V ans = values[i];
                values[i] = value;
                return ans;
            }
            if (keys[i] == null && !removed[i]) {
                this.size++;
                keys[i] = key;
                values[i] = value;
                checkAndResize();
                return null;
            }
            i = (i + h2) % keys.length;
        }
    }

    @Nullable
    @Override
    public V remove(K key) {
        int i = hash(key);
        int h2 = hash2(key);
        while (true) {
            if (key.equals(keys[i]) && !removed[i]) {
                removed[i] = true;
                size--;
                return values[i];
            }
            if (keys[i] == null) {
                return null;
            }
            i = (i + h2) % keys.length;
        }
    }


    @Override
    public void forEach(BiConsumer<K, V> consumer) {
        for (int i = 0; i < keys.length; i++){
            if (!removed[i] && keys[i] != null) {
                consumer.accept(keys[i], values[i]);
            }
        }
    }

    private int hash(K key) {
        return (key.hashCode() & 0x7fffffff) % keys.length;
    }

    private int hash2(K key) {
        int ans = ((key.hashCode() & 0x7fffffff) % 8191) % keys.length;
        return ans == 0 ? 1 : ans;
    }

    private int getPrime(int inp) {
        while (!isPrime(++inp)) ;
        return inp;
    }

    private boolean isPrime(int i) {
        if (i == 1) {
            return false;
        }
        if (i == 2) {
            return true;
        }
        if (i % 2 == 0) {
            return false;
        }
        for (int k = 3; i < Math.sqrt(i); i += 2) {
            if (i % k == 0) {
                return false;
            }
        }
        return true;
    }

    public void checkAndResize() {
        if (size <= expectedMaxSize) return;
        expectedMaxSize *= 2;
        boolean[] oldRemoved = removed;
        K[] oldKeys = keys;
        V[] oldValues = values;
        expectedMaxSize *= 2;
        int newSize = getPrime(expectedMaxSize);
        keys = allocate(newSize);
        values = allocate(newSize);
        removed = new boolean[newSize];
        for (int i = 0; i < oldRemoved.length; i++) {
            if (oldKeys[i] != null && !oldRemoved[i]) {
                put(oldKeys[i], oldValues[i]);
            }
        }
    }


    @SuppressWarnings("unchecked")
    private static <T> T[] allocate(int capacity) {
        return (T[]) new Object[capacity];
    }
}
