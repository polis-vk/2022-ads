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

        public Node(Key key, Value value, boolean color) {
            this.key = key;
            this.value = value;
            this.color = color;
        }
    }

    private int size;
    private Node root;
    private Value lastDeleted;

    @Nullable
    @Override
    public Value get(@NotNull Key key) {
        return get(root, key);
    }

    @Override
    public void put(@NotNull Key key, @NotNull Value value) {
        root = put(root, key, value);
        root.color = BLACK;
    }

    @Nullable
    @Override
    public Value remove(@NotNull Key key) {
        lastDeleted = null;
        root = delete(root, key);
        return lastDeleted;
    }

    @Nullable
    @Override
    public Key min() {
        Node min = min(root);
        return (min == null ? null : min.key);
    }

    @Nullable
    @Override
    public Value minValue() {
        Node min = min(root);
        return (min == null ? null : min.value);
    }

    @Nullable
    @Override
    public Key max() {
        Node max = max(root);
        return (max == null ? null : max.key);
    }

    @Nullable
    @Override
    public Value maxValue() {
        Node max = max(root);
        return (max == null ? null : max.value);
    }

    @Nullable
    @Override
    public Key floor(@NotNull Key key) {
        Node floor = floor(root, key);
        return (floor == null ? null : floor.key);
    }

    @Nullable
    @Override
    public Key ceil(@NotNull Key key) {
        Node ceil = ceil(root, key);
        return (ceil == null ? null : ceil.key);
    }

    @Override
    public int size() {
        return size;
    }

    /**
     * Только для тестов
     * Саму высоту хранить не обязательно, достаточно сделать рекурсивное вычисление
     */
    @Override
    public int height() {
        return height(root);
    }

    private Node put(Node x, Key key, Value value) {
        if (x == null) {
            size++;
            return new Node(key, value, RED);
        }
        if (key.compareTo(x.key) < 0) {
            x.left = put(x.left, key, value);
        } else if (key.compareTo(x.key) > 0) {
            x.right = put(x.right, key, value);
        } else {
            x.value = value;
        }
        return fixUp(x);
    }

    private Node fixUp(Node x) {
        if (isRed(x.right) && !isRed(x.left)) {
            x = rotateLeft(x);
        }
        if (isRed(x.left) && isRed(x.left.left)) {
            x = rotateRight(x);
        }
        if (isRed(x.left) && isRed(x.right)) {
            flipColors(x);
        }
        return x;
    }

    private boolean isRed(Node x) {
        return x != null && x.color == RED;
    }

    private Node rotateLeft(Node x) {
        Node y = x.right;
        x.right = y.left;
        y.left = x;
        y.color = x.color;
        x.color = RED;
        return y;
    }

    private Node rotateRight(Node y) {
        Node x = y.left;
        y.left = x.right;
        x.right = y;
        x.color = y.color;
        y.color = RED;
        return x;
    }

    private Node flipColors(Node x) {
        x.color = !x.color;
        x.left.color = !x.left.color;
        x.right.color = !x.right.color;
        return x;
    }

    private Value get(Node x, Key key) {
        if (x == null) {
            return null;
        }
        if (key.compareTo(x.key) < 0) {
            return get(x.left, key);
        } else if (key.compareTo(x.key) > 0) {
            return get(x.right, key);
        } else {
            return x.value;
        }
    }

    private int height(Node x) {
        if (x == null) {
            return 0;
        }
        return 1 + height(x.left);
    }

    private Node max(Node x) {
        if (x == null) {
            return null;
        }
        if (x.right == null) {
            return x;
        }
        return max(x.right);
    }

    private Node min(Node x) {
        if (x == null) {
            return null;
        }
        if (x.left == null) {
            return x;
        }
        return min(x.left);
    }

    private Node ceil(Node x, Key key) {
        if (x == null) {
            return null;
        }
        if (key.compareTo(x.key) < 0) {
            Node ceilLeft = ceil(x.left, key);
            return (ceilLeft == null ? x : ceilLeft);
        } else if (key.compareTo(x.key) > 0) {
            return ceil(x.right, key);
        }
        return x;
    }

    private Node floor(Node x, Key key) {
        if (x == null) {
            return null;
        }
        if (key.compareTo(x.key) < 0) {
            return floor(x.left, key);
        } else if (key.compareTo(x.key) > 0) {
            Node ceilRight = floor(x.right, key);
            return (ceilRight == null ? x : ceilRight);
        }
        return x;
    }

    private Node delete(Node x, Key key) {
        if (x == null) {
            return null;
        }
        if (key.compareTo(x.key) < 0) {
            if (x.left != null) {
                if (!isRed(x.left) && !isRed(x.left.left)) {
                    x = moveRedLeft(x);
                }
                x.left = delete(x.left, key);
            }
        } else {
            if (isRed(x.left)) {
                x = rotateRight(x);
                x.right = delete(x.right, key);
            } else if (x.key.compareTo(key) == 0 && x.right == null) {
                lastDeleted = x.value;
                size--;
                return null;
            } else {
                if (x.right != null && !isRed(x.right) && !isRed(x.right.left)) {
                    x = moveRedRight(x);
                }
                if (x.key.compareTo(key) == 0) {
                    Node min = min(x.right);
                    lastDeleted = x.value;
                    size--;
                    x.key = min.key;
                    x.value = min.value;
                    x.right = deleteMin(x.right);
                } else {
                    x.right = delete(x.right, key);
                }
            }

        }
        return fixUp(x);
    }

    private Node moveRedRight(Node x) {
        flipColors(x);
        if (isRed(x.left.left)) {
            x = rotateRight(x);
            flipColors(x);
        }
        return x;
    }

    private Node moveRedLeft(Node x) {
        flipColors(x);
        if (isRed(x.right.left)) {
            x.right = rotateRight(x.right);
            x = rotateLeft(x);
            flipColors(x);
        }
        return x;
    }

    private Node deleteMin(Node x) {
        if (x.left == null) {
            return null;
        }
        if (!isRed(x.left) && !isRed(x.left.left)) {
            x = moveRedLeft(x);
        }
        x.left = deleteMin(x.left);
        return fixUp(x);
    }
}
