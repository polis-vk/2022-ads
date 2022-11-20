package company.vk.polis.ads.hash;

import java.util.Arrays;
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
    // Do not edit these 3 instance fields!!!
    private K[] keys;
    private V[] values;
    private boolean[] removed;

    private int size;
    private int capacity;
    private float loadFactor;

    private int [] primes;


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
        primes = supportSizeOfEratosthenes(expectedMaxSize);
        this.loadFactor = loadFactor;
        size = 0;
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
        int h1 = hash1(key);
        int h2 = hash2(key);
        for (int i = getInxOnHash(h1), j = 1; keys[i] != null; i = getInxOnHash(h1 + (j++)*h2)) {
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
        if (Math.ceil(capacity * loadFactor) == size) {
            resize();
        }

        int h1 = hash1(key);
        int h2 = hash2(key);
        int i = getInxOnHash(h1);
        for (int j = 1; keys[i] != null; i = getInxOnHash(h1 + (j++) * h2)) {
            if (keys[i].equals(key) && !removed[i]) {
                V val = values[i];
                values[i] = value;
                return val;
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
        int h1 = hash1(key);
        int h2 = hash2(key);
        for (int i = getInxOnHash(h1), j = 1; keys[i] != null; i = getInxOnHash(h1 + (j++)*h2)) {
            if (keys[i].equals(key) && !removed[i]) {
                removed[i] = true;
                size--;
                return values[i];
            }
        }
        return null;
    }

    @Override
    public void forEach(BiConsumer<K, V> consumer) {
        for (int i = 0; i < capacity; ++i) {
            if (keys[i] != null && !removed[i]) {
                consumer.accept(keys[i], values[i]);
            }
        }
    }

    @SuppressWarnings("unchecked")
    private static <T> T[] allocate(int capacity) {
        return (T[]) new Object[capacity];
    }

    private int hash1(Object key) {
        int h;
        return (key == null) ? 0 : (h = key.hashCode()) ^ (h >>> 16);
    }

    private int hash2(Object key) {
        int h = capacity - (key.hashCode() % capacity == 0 ? 1 : key.hashCode() % capacity);
        return h ^ (h >>> 16);
    }

    private int getInxOnHash(int hash) {
        return (hash & 0x7fffffff) % capacity;
    }

    private void resize() {
       int nextCapacity = Math.abs(Arrays.binarySearch(primes, capacity * 2));
       if (nextCapacity == primes.length) {
          primes = supportSizeOfEratosthenes(capacity);
       }
       int newCapacity = primes[nextCapacity];

       DoubleHashingMap<K, V> map = new DoubleHashingMap<K, V>(newCapacity);
       map.loadFactor = loadFactor;
       map.primes = primes;

       for (int i = 0; i < keys.length; ++i) {
           if (keys[i] != null && !removed[i]) {
               map.put(keys[i], values[i]);
           }
       }

       capacity = newCapacity;
       this.keys = map.keys;
       this.values = map.values;
       this.removed = map.removed;
    }

    /***
     * Решето Эратосфена.Строит массив простых чисел за O(sqrt(max))
     * @param max - значение, которое не превышает лююое простое число из полученного массива
     * @return
     * @throws IllegalArgumentException
     */
    private int [] createSieveOfEratosthenes(int max) throws IllegalArgumentException {
        if (max < 2 || max == Integer.MAX_VALUE) {
            throw  new IllegalArgumentException();
        }
        int [] a = IntStream.range(0, max + 1).toArray();
        a[1] = 0;
        int i = 2;
        while (i <= max / 2) {
            if (a[i] != 0) {
                int j = 2 * i;
                while (j <= max) {
                    a[j] = 0;
                    if (j > max - i) {
                        break;
                    }
                    j = j + i;
                }
            }
            i += 1;
        }
        return Arrays.stream(a).filter(v -> v != 0).toArray();
    }

    /***
     *
     * @param capacity - изначальный параметр размера, на основе которого будем строить массив простых чисел
     * Приблизительно строю на 4-5 расширения, чтобы не съедать слишком много памяти, но при этом не вызывалось слишком часто
     * @return
     */
    private int [] supportSizeOfEratosthenes(int capacity) {
        int size =  (capacity < Integer.MAX_VALUE / 10) ? capacity * 10 : Integer.MAX_VALUE;
        return createSieveOfEratosthenes(size);
    }

    private DoubleHashingMap(int capacity) {
        this.capacity = capacity;
        keys = allocate(capacity);
        values = allocate(capacity);
        removed = new boolean[capacity];
        size = 0;
    }


}
