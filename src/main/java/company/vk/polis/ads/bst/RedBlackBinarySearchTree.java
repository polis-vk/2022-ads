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

    private Node root;
    private int size;

    private class Node {
        Key key;
        Value value;
        Node left;
        Node right;
        boolean color;

        public Node(Key key, Value value, Boolean color) {
            this.key = key;
            this.value = value;
            this.color = color;
        }
    }

    @Nullable
    @Override
    public Value get(@NotNull Key key) {
        return get(root, key);
    }

    private Value get(Node x, Key key) {
        if (x == null) {
            return null;
        }
        if (key.compareTo(x.key) < 0) {
            return get(x.left, key);
        }
        if (key.compareTo(x.key) > 0) {
            return get(x.right, key);
        }
        return x.value;
    }

    @Override
    public void put(@NotNull Key key, @NotNull Value value) {
        root = put(root, key, value);
        root.color = BLACK;
    }

    private Node put(Node x, Key key, Value value) {
        if (x == null) {
            size++;
            return new Node(key, value, RED);
        }
        if (key.compareTo(x.key) < 0) {
            x.left = put(x.left, key, value);
        }
        else if (key.compareTo(x.key) > 0) {
            x.right = put(x.right, key, value);
        }
        else {
            x.value = value;
        }
        return fixUp(x);
    }

    @Nullable
    @Override
    public Value remove(@NotNull Key key) {
        Value result = get(key);
        if (result == null) {
            return null;
        }
        root = remove(root, key);
        size--;
        return result;
    }

    private Node remove(Node x, @NotNull Key key) {
        if (x == null) {
            return null;
        }
        if (key.compareTo(x.key) < 0) {
            if (x.left != null) {
                if (!isRed(x.left) && !isRed(x.left.left)) {
                    x = moveRedLeft(x);
                }
                x.left = remove(x.left, key);
            }
        } else {
            if (isRed(x.left)) {
                x = rotateRight(x);
                x.right = remove(x.right, key);
            } else if (x.key == key && x.right == null) {
                return null;
            } else {
                if (x.right != null && !isRed(x.right) && !isRed(x.right.left)) {
                    x = moveRedRight(x);
                }
                if (x.key == key) {
                    Node min = min(x.right);
                    x.key = min.key;
                    x.value = min.value;
                    x.right = deleteMin(x.right);
                } else {
                    x.right = remove(x.right, key);
                }
            }
        }
        return fixUp(x);
    }

    @Nullable
    @Override
    public Key min() {
        Node min = min(root);
        return (min != null)? min.key : null;
    }

    @Nullable
    @Override
    public Value minValue() {
        Node min = min(root);
        return (min != null)? min.value : null;
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

    @Nullable
    @Override
    public Key max() {
        Node max = max(root);
        return (max != null)? max.key : null;
    }

    @Nullable
    @Override
    public Value maxValue() {
        Node max = max(root);
        return (max != null)? max.value : null;
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

    @Nullable
    @Override
    public Key floor(@NotNull Key key) {
        Node floor = floor(root, key);
        return (floor != null)? floor.key : null;
    }

    private Node floor(Node x, Key key) {
        if (x == null) {
            return null;
        }
        if (key.compareTo(x.key) == 0) {
            return x;
        }
        if (key.compareTo(x.key) < 0) {
            return floor(x.left, key);
        }
        Node y = floor(x.right, key);
        if (y == null || y.key.compareTo(key) > 0) {
            return x;
        }
        return y;
    }

    @Nullable
    @Override
    public Key ceil(@NotNull Key key) {
        Node ceil = ceil(root, key);
        return (ceil != null)? ceil.key : null;
    }

    private Node ceil(Node x, Key key) {
        if (x == null) {
            return null;
        }
        if (key.compareTo(x.key) == 0) {
            return x;
        }
        if (key.compareTo(x.key) > 0) {
            return ceil(x.right, key);
        }
        Node y = ceil(x.left, key);
        if (y == null || y.key.compareTo(key) < 0) {
            return x;
        }
        return y;
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

    private int height(Node x) {
        return (x == null) ? 0 : 1 + Math.max(height(x.left), height(x.right));
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

    private Node moveRedLeft(Node x) {
        flipColors(x);
        if (isRed(x.right.left)) {
            x.right = rotateRight(x.right);
            x = rotateLeft(x);
            flipColors(x);
        }
        return x;
    }

    private Node moveRedRight(Node x) {
        flipColors(x);
        if (isRed(x.left.left)) {
            x = rotateRight(x);
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
