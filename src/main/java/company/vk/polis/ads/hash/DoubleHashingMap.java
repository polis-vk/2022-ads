package company.vk.polis.ads.hash;

import java.util.Arrays;
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
    private static final float GOLD_RATIO = 1.618F;

    // Do not edit these 3 instance fields!!!
    private K[] keys;
    private V[] values;
    private boolean[] removed;
    private int size;
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
        capacity = (int) (expectedMaxSize / loadFactor);
        keys = allocate(capacity);
        values = allocate(capacity);
        removed = new boolean[capacity];
        this.loadFactor = loadFactor;
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
        int i = 0;
        int currentIndex = getArrayIndex(key, i);
        while(keys[currentIndex] != null) {
            if (keys[currentIndex].equals(key) && !removed[currentIndex]) {
                return values[currentIndex];
            }
            currentIndex = getArrayIndex(key, ++i);
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
        if (capacity * loadFactor <= size) {
            ensureCapacity();
        }
        int step = 0;
        int currentIndex = getArrayIndex(key, step++);
        while(keys[currentIndex] != null) {
            if (keys[currentIndex].equals(key) && !removed[currentIndex]) {
                final V previousValue = values[currentIndex];
                values[currentIndex] = value;
                return previousValue;
            }
            currentIndex = getArrayIndex(key, step++);
        }
        size++;
        keys[currentIndex] = key;
        values[currentIndex] = value;
        return null;
    }

    @Nullable
    @Override
    public V remove(K key) {
        int step = 0;
        int currentIndex = getArrayIndex(key, step++);
        while(keys[currentIndex] != null) {
            if (keys[currentIndex].equals(key) && !removed[currentIndex]) {
                size--;
                removed[currentIndex] = true;
                return values[currentIndex];
            }
            currentIndex = getArrayIndex(key, step++);
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

    private int hashCode1(K key) {
        return (Objects.hashCode(key) >> 4) + 20;
    }

    private int hashCode2(K key) {
        return Objects.hashCode(key) >> 8;
    }

    private int getArrayIndex(K key, int step) {
        int hash1 = hashCode1(key);
        int hash2 = hashCode2(key);
        return ((hash1 * step + hash2) & 0x7fffffff) % (capacity - 1);
    }

    private void ensureCapacity() {
        int newCapacity = getNextCapacity((int) Math.ceil(capacity * GOLD_RATIO));
        K[] copyOfKeys = Arrays.copyOf(keys, keys.length);
        V[] copyOfValues = Arrays.copyOf(values, values.length);
        keys = allocate(newCapacity);
        values = allocate(newCapacity);
        removed = new boolean[newCapacity];
        size = 0;
        for (int i = 0; i < capacity; i++) {
            if (copyOfKeys[i] != null && !removed[i]) {
                put(copyOfKeys[i], copyOfValues[i]);
            }
        }
        capacity = newCapacity;
    }

    private static int getNextCapacity(int minValue) {
        if (isPrime(minValue)) {
            return minValue;
        }
        return getNextCapacity(++minValue);
    }

    private static boolean isPrime(int value) {
        for (int i = 2; i <= Math.sqrt(value); i++){
            if (value % i == 0) {
                return false;
            }
        }
        return true;
    }
}
