package company.vk.polis.ads.hash;

import java.util.function.BiConsumer;

import org.jetbrains.annotations.Nullable;

/**
 * Map aka Dictionary or Associative array
 *
 * @param <K> key
 * @param <V> value
 */
public interface Map<K, V> {
    int size();

    default boolean isEmpty() {
        return size() == 0;
    }

    /**
     * Checks if key is present.
     *
     * @param key key
     * @return true if key is present and false otherwise
     */
    boolean containsKey(K key);

    /**
     * Returns value associated with key
     *
     * @param key key
     * @return value associated with key
     */
    @Nullable
    V get(K key);

    /**
     * Puts key and value associated with it
     *
     * @param key   key
     * @param value value
     * @return old value associated with key if one present or null otherwise
     */
    @Nullable
    V put(K key, V value);

    /**
     * Removes value associated with key
     *
     * @param key key
     * @return value removed from map if one was or null otherwise
     */
    @Nullable
    V remove(K key);

    /**
     * Iterates over map and passes key-value pairs to consumer
     *
     * @param consumer object that consumes key-value pairs
     */
    void forEach(BiConsumer<K, V> consumer);
}
