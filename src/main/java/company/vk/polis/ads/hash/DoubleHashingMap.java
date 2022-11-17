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
    private boolean[] removed;
    private int size;
    private int primeSize;
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
        int capacity = (int) (expectedMaxSize / loadFactor);
        keys = allocate(capacity);
        values = allocate(capacity);
        removed = new boolean[capacity];
        this.loadFactor = loadFactor;
        this.primeSize = getPrime();
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean containsKey(K key) {
        int step = 0;
        int index = getIndex(key, step++);
        while (keys[index] != null) {
            if (keys[index].equals(key) && !removed[index]) {
                return true;
            }
            index = getIndex(key, step++);
        }
        return false;
    }

    @Nullable
    @Override
    public V get(K key) {
        int step = 0;
        int index = getIndex(key, step++);
        while (keys[index] != null) {
            if (keys[index].equals(key) && !removed[index]) {
                return values[index];
            }
            index = getIndex(key, step++);
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
        if ((float) size / keys.length >= loadFactor) {
            resizeIfNeeded();
        }
        int step = 0;
        int index = getIndex(key, step++);
        while (keys[index] != null) {
            if (removed[index]) {
                break;
            }
            if (keys[index].equals(key)) {
                V oldValue = values[index];
                values[index] = value;
                return oldValue;
            }
            index = getIndex(key, step++);
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
        int step = 0;
        int index = getIndex(key, step++);
        while (keys[index] != null) {
            if (keys[index].equals(key)) {
                removed[index] = true;
                size--;
                return values[index];
            }
            index = getIndex(key, step++);
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

    private int getPrime() {
        int num = keys.length - 1;
        while (num > 2) {
            for (int den = 2; den <= (int) Math.sqrt(num); den++) {
                if (num % den == 0) {
                    break;
                }
                return num;
            }
            num--;
        }
        return num + 1;
    }

    private void resizeIfNeeded() {
        K[] tmpKeys = keys;
        V[] tmpValues = values;
        boolean[] tmpRemoved = removed;
        keys = allocate(2 * keys.length);
        values = allocate(2 * values.length);
        removed = new boolean[2 * removed.length];
        size = 0;
        primeSize = getPrime();
        for (int i = 0; i < tmpKeys.length; i++) {
            if (tmpKeys[i] != null) {
                put(tmpKeys[i], tmpValues[i]);
            }
        }
    }

    private int hashCode1(K key) {
        return Objects.hashCode(key) % keys.length;
    }

    private int hashCode2(K key) {
        int hash = primeSize - (Objects.hashCode(key) % primeSize) + 1; // plus one to avoid zero
        return hash < 0 ? hash * -1 : hash;
    }

    private int getIndex(K key, int i) {
        int index = (hashCode1(key) + i * hashCode2(key)) % keys.length;
        return index < 0 ? index * -1 : index;
    }

    @SuppressWarnings("unchecked")
    private static <T> T[] allocate(int capacity) {
        return (T[]) new Object[capacity];
    }
}
