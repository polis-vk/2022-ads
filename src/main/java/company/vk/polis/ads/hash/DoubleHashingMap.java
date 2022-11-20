package company.vk.polis.ads.hash;

import org.jetbrains.annotations.Nullable;

import java.util.function.BiConsumer;

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
        this.loadFactor = loadFactor;
        int capacity = (int) (expectedMaxSize / loadFactor);

        this.keys = allocate(capacity);
        this.values = allocate(capacity);
        this.removed = new boolean[capacity];
    }

    private int getIndex(K key, int step) {
        int hash = key.hashCode() % (keys.length - 1) + 1;
        int hash2 = key.hashCode();
        return Math.abs(((hash + step * hash2 + 1)) % keys.length);
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean containsKey(K key) {
        int round = 0;
        int index = getIndex(key, round++);

        while (keys[index] != null) {
            if (key.equals(keys[index]) && !removed[index]) {
                return true;
            }
            index = getIndex(key, round++);
        }
        return false;
    }

    @Nullable
    @Override
    public V get(K key) {
        int round = 0;
        int index = getIndex(key, round++);

        while (keys[index] != null) {
            if (key.equals(keys[index]) && !removed[index]) {
                return values[index];
            }
            index = getIndex(key, round++);
        }
        return null;
    }

    private void resize() {
        K[] tmpkeys = keys;
        V[] tmpvals = values;
        boolean[] tmpremoved = removed;

        keys = allocate(2 * tmpkeys.length);
        values = allocate(2 * tmpvals.length);
        removed = new boolean[2 * tmpremoved.length];
        size = 0;

        for (int i = 0; i < tmpkeys.length; i++) {
            if (tmpkeys[i] != null) {
                put(tmpkeys[i], tmpvals[i]);
            }
        }
    }

    /**
     * Если capacity * loadFactor == size() и будет добавлен новый ключ,
     * то нужно выполнить расширение массивов
     */
    @Nullable
    @Override
    public V put(K key, V value) {
        if (keys.length * loadFactor <= size()) {
            resize();
        }

        int round = 0;
        int index = getIndex(key, round++);

        while (keys[index] != null) {
            if (removed[index]) {
                keys[index] = key;
                values[index] = value;
                removed[index] = false;
                size++;
                return null;
            }

            if (key.equals(keys[index]) && !removed[index]) {
                V tmp = values[index];
                values[index] = value;
                removed[index] = false;
                return tmp;
            }
            index = getIndex(key, round++);
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
        int round = 0;
        int index = getIndex(key, round++);

        while (keys[index] != null) {
            if (key.equals(keys[index]) && !removed[index]) {
                removed[index] = true;
                size--;
                return values[index];
            }
            index = getIndex(key, round++);
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
}
