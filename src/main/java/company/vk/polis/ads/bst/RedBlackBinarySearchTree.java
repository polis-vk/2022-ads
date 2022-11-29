package company.vk.polis.ads.bst;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * LLRB implementation of binary search tree.
 */
public class RedBlackBinarySearchTree<Key extends Comparable<Key>, Value>
        implements BinarySearchTree<Key, Value> {

    private static final boolean BLACK = false;
    private static final boolean RED = true;

    private class Node {
        Key key;
        Value value;
        Node left;
        Node right;
        boolean color;
    }

    @Nullable
    @Override
    public Value get(@NotNull Key key) {
        throw new UnsupportedOperationException("Implement me");
    }

    @Override
    public void put(@NotNull Key key, @NotNull Value value) {
        throw new UnsupportedOperationException("Implement me");
    }

    @Nullable
    @Override
    public Value remove(@NotNull Key key) {
        throw new UnsupportedOperationException("Implement me");
    }

    @Nullable
    @Override
    public Key min() {
        throw new UnsupportedOperationException("Implement me");
    }

    @Nullable
    @Override
    public Value minValue() {
        throw new UnsupportedOperationException("Implement me");
    }

    @Nullable
    @Override
    public Key max() {
        throw new UnsupportedOperationException("Implement me");
    }

    @Nullable
    @Override
    public Value maxValue() {
        throw new UnsupportedOperationException("Implement me");
    }

    @Nullable
    @Override
    public Key floor(@NotNull Key key) {
        throw new UnsupportedOperationException("Implement me");
    }

    @Nullable
    @Override
    public Key ceil(@NotNull Key key) {
        throw new UnsupportedOperationException("Implement me");
    }

    @Override
    public int size() {
        throw new UnsupportedOperationException("Implement me");
    }

    /**
     * Только для тестов
     * Саму высоту хранить не обязательно, достаточно сделать рекурсивное вычисление
     */
    @Override
    public int height() {
        throw new UnsupportedOperationException("Implement me");
    }
}
