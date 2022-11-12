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

    Node root;
    private int nodeCount;

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
        Value res = get(root, key);
        if (res == null) {
            return null;
        }
        root = delete(root, key);
        nodeCount--;
        return res;
    }

    @Nullable
    @Override
    public Key min() {
        Node res = minNode(root);
        return res == null ? null : res.key;
    }

    @Nullable
    @Override
    public Value minValue() {
        Node res = minNode(root);
        return res == null ? null : res.value;
    }

    @Nullable
    @Override
    public Key max() {
        Node res = maxNode(root);
        return res == null ? null : res.key;
    }

    @Nullable
    @Override
    public Value maxValue() {
        Node res = maxNode(root);
        return res == null ? null : res.value;
    }

    @Nullable
    @Override
    public Key floor(@NotNull Key key) {
        return floor(root, key);
    }

    @Nullable
    @Override
    public Key ceil(@NotNull Key key) {
        return ceil(root, key);
    }

    @Override
    public int size() {
        return nodeCount;
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
            nodeCount++;
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
            } else if (x.key.equals(key) && x.right == null) {
                return null;
            } else {
                if (x.right != null && !isRed(x.right) && !isRed(x.right.left)) {
                    x = moveRedRight(x);
                }
                if (x.key.equals(key)) {
                    Node min = minNode(x.right);
                    if (min != null) {
                        x.key = min.key;
                        x.value = min.value;
                        x.right = deleteMin(x.right);
                    }
                } else {
                    x.right = delete(x.right, key);
                }
            }
        }
        return fixUp(x);
    }

    Node moveRedLeft(Node x) {
        flipColors(x);
        if (isRed(x.right.left)) {
            x.right = rotateRight(x.right);
            x = rotateLeft(x);
            flipColors(x);
        }
        return x;
    }

    Node moveRedRight(Node x) {
        flipColors(x);
        if (isRed(x.left.left)) {
            x = rotateRight(x);
            flipColors(x);
        }
        return x;
    }

    Node deleteMin(Node x) {
        if (x.left == null) {
            return null;
        }
        if (!isRed(x.left) && !isRed(x.left.left)) {
            x = moveRedRight(x);
        }
        x.left = deleteMin(x.left);
        return fixUp(x);
    }

    private Node minNode(Node x) {
        if (x == null) {
            return null;
        }
        while (x.left != null) {
            x = x.left;
        }
        return x;
    }

    private Node maxNode(Node x) {
        if (x == null) {
            return null;
        }
        while (x.right != null) {
            x = x.right;
        }
        return x;
    }

    private Key floor(Node x, Key key) {
        if (x == null) {
            return null;
        }
        if (key.compareTo(x.key) < 0) {
            return floor(x.left, key);
        } else if (key.compareTo(x.key) > 0) {
            Key res = floor(x.right, key);
            return (res != null && res.compareTo(key) <= 0) ? res : x.key;
        } else {
            return x.key;
        }
    }

    private Key ceil(Node x, Key key) {
        if (x == null) {
            return null;
        }
        if (key.compareTo(x.key) < 0) {
            Key res = ceil(x.left, key);
            return (res != null && res.compareTo(key) >= 0) ? res : x.key;
        } else if (key.compareTo(x.key) > 0) {
            return ceil(x.right, key);
        } else {
            return x.key;
        }
    }

    private int height(Node x) {
        int res = 0;
        while (x != null) {
            x = x.left;
            res++;
        }
        return res;
    }

}
