package company.vk.polis.ads.hash;

import java.util.function.BiConsumer;
import java.util.stream.IntStream;

import org.jetbrains.annotations.Nullable;

/**
 * Map implementation with double hashing collision resolution approach
 *
 * @param <K> key
 * @param <V> value
 */
public final class DoubleHashingMap<K, V> implements Map<K, V> {

    private static final int INCREASE_FACTOR = 2;

    private final float loadFactor;
    private int size;
    // Do not edit these 3 instance fields!!!
    private K[] keys;
    private V[] values;
    private boolean[] removed;

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
        final int capacity = (int) (expectedMaxSize / loadFactor);
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
        int hash1 = hash1(key);
        int hash2 = hash2(key);
        int i;
        int j = 0;
        for (i = hashToIndex(hash1); keys[i] != null; i = hashToIndex(hash1 + (++j) * hash2)) { // dont work
            if (!removed[i] && keys[i].equals(key)) {
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
        resizeIfNeed();

        int hash1 = hash1(key);
        int hash2 = hash2(key);
        int i;
        int j = 0;
        // removed condition works?
        for (i = hashToIndex(hash1); keys[i] != null; i = hashToIndex(hash1 + (++j) * hash2)) {
            if (!removed[i] && keys[i].equals(key)) {
                V oldVal = values[i];
                values[i] = value;
                return oldVal;
            }
        }
        size++;
        keys[i] = key;
        values[i] = value;
        return null;
    }

    @Nullable
    @Override
    public V remove(K key) {
        int hash1 = hash1(key);
        int hash2 = hash2(key);
        int i;
        int j = 0;
        for (i = hashToIndex(hash1); keys[i] != null; i = hashToIndex(hash1 + (++j) * hash2)) {
            if (!removed[i] && keys[i].equals(key)) {
                removed[i] = true;
                size--;
                return values[i];
            }
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

    private void resizeIfNeed() {
        if (size >= loadFactor * keys.length) {
            K[] oldKeys = keys;
            V[] oldValues = values;
            boolean[] oldRemoved = removed;
            int length = keys.length;
            keys = allocate(length * INCREASE_FACTOR);
            values = allocate(length * INCREASE_FACTOR);
            removed = new boolean[length * INCREASE_FACTOR];
            System.arraycopy(oldKeys, 0, keys, 0, length);
            System.arraycopy(oldValues, 0, values, 0, length);
            System.arraycopy(oldRemoved, 0, removed, 0, length);
        }
    }

    private int hash1(K key) {
        int hash;
        return (hash = key.hashCode()) ^ (hash >>> 16); // javadoc
    }

    private int hash2(K key) {
        int len = keys.length;
        int hash = len - key.hashCode();
        return hash % 2 == 0 ? ++hash : hash;
    }

    private int hashToIndex(int hash) {
        return (hash & 0x7ffffff) % keys.length;
    }
}
