package company.vk.polis.ads.bst;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * Binary search tree with ordered operations support.
 */
public interface BinarySearchTree<Key extends Comparable<Key>, Value> {
    @Nullable Value get(@NotNull Key key);

    default boolean containsKey(@NotNull Key key) {
        return get(key) != null;
    }

    void put(@NotNull Key key, @NotNull Value value);

    @Nullable Value remove(@NotNull Key key);

    @Nullable Key min();

    @Nullable Value minValue();

    @Nullable Key max();

    @Nullable Value maxValue();

    @Nullable Key floor(@NotNull Key key);

    @Nullable Key ceil(@NotNull Key key);

    int size();

    int height();

    default boolean isEmpty() {
        return size() == 0;
    }
}
