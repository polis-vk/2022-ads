package company.vk.polis.ads.hash;

import java.util.HashMap;
import java.util.Objects;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;

import org.jetbrains.annotations.Nullable;

final class HashMapMapImpl<K, V> implements Map<K, V> {
    private final java.util.Map<K, V> hashMap;

    HashMapMapImpl(BiFunction<Integer, Float, java.util.Map<K, V>> m, int capacity, float loadFactor) {
        hashMap = m.apply(capacity, loadFactor);
    }

    HashMapMapImpl(int capacity, float loadFactor) {
        this(HashMap::new, capacity, loadFactor);
    }

    @Override
    public int size() {
        return hashMap.size();
    }

    @Override
    public boolean containsKey(K key) {
        return hashMap.containsKey(Objects.requireNonNull(key));
    }

    @Nullable
    @Override
    public V get(K key) {
        return hashMap.get(Objects.requireNonNull(key));
    }

    @Nullable
    @Override
    public V put(K key, V value) {
        return hashMap.put(Objects.requireNonNull(key), Objects.requireNonNull(value));
    }

    @Nullable
    @Override
    public V remove(K key) {
        return hashMap.remove(Objects.requireNonNull(key));
    }

    @Override
    public void forEach(BiConsumer<K, V> consumer) {
        hashMap.forEach(consumer);
    }
}
