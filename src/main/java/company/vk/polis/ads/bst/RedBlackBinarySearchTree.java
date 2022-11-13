package company.vk.polis.ads.bst;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * LLRB implementation of binary search tree.
 */
public class RedBlackBinarySearchTree<Key extends Comparable<Key>, Value> implements BinarySearchTree<Key, Value> {

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
            left = null;
            right = null;
        }
    }

    @Nullable
    @Override
    public Value get(@NotNull Key key) {
        Node tmp = root;
        while (tmp != null) {
            if (key.compareTo(tmp.key) < 0) {
                tmp = tmp.left;
            } else if (key.compareTo(tmp.key) > 0) {
                tmp = tmp.right;
            } else {
                return tmp.value;
            }
        }
        return null;
    }

    @Override
    public void put(@NotNull Key key, @NotNull Value value) {
        root = put(root, key, value);
        root.color = BLACK;
    }

    @Nullable
    @Override
    public Value remove(@NotNull Key key) {
        Value x = get(key);
        if (x == null) {
            return null;
        }
        size--;
        root = delete(root, key);
        return x;
    }

    @Nullable
    @Override
    public Key min() {
        Node min = getMinNode(root);
        return min == null ? null : min.key;
    }

    @Nullable
    @Override
    public Value minValue() {
        Node min = getMinNode(root);
        return min == null ? null : min.value;
    }

    @Nullable
    @Override
    public Key max() {
        Node max = getMaxNode(root);
        return max == null ? null : max.key;
    }

    @Nullable
    @Override
    public Value maxValue() {
        Node max = getMaxNode(root);
        return max == null ? null : max.value;
    }

    @Nullable
    @Override
    public Key floor(@NotNull Key key) {
        Node tmp = root;
        Node floor = null;
        while (tmp != null) {
            if (key.compareTo(tmp.key) > 0) {
                floor = tmp;
                tmp = tmp.right;
            } else if (key.compareTo(tmp.key) < 0) {
                tmp = tmp.left;
            } else {
                return tmp.key;
            }
        }
        return (floor == null) ? null : floor.key;
    }

    @Nullable
    @Override
    public Key ceil(@NotNull Key key) {
        Node tmp = root;
        Node ceil = null;
        while (tmp != null) {
            if (key.compareTo(tmp.key) > 0) {
                tmp = tmp.right;
            } else if (key.compareTo(tmp.key) < 0) {
                ceil = tmp;
                tmp = tmp.left;
            } else {
                return tmp.key;
            }
        }
        return (ceil == null) ? null : ceil.key;
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
        int height = 0;
        Node tmp = root;
        while (tmp != null) {
            if (tmp.color == BLACK) {
                height++;
            }
            tmp = tmp.left;
        }
        return height;
    }

    private Node root = null;
    private int size = 0;

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
            } else if (x.key == key && x.right == null) {
                return null;
            } else {
                if (x.right != null && !isRed(x.right) && !isRed(x.right.left)) {
                    x = moveRedRight(x);
                }
                if (x.key == key) {
                    Node min = getMinNode(x.right);
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

    private Node getMinNode(Node tmp) {
        if (tmp == null) {
            return null;
        }
        while (tmp.left != null) {
            tmp = tmp.left;
        }
        return tmp;
    }
    private Node getMaxNode(Node tmp) {
        if (tmp == null) {
            return null;
        }
        while (tmp.right != null) {
            tmp = tmp.right;
        }
        return tmp;
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
        if (isRed(x.right) && !isRed(x.left)){
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

    Node deleteMax(Node x) {
        if (isRed(x.left)) {
            x = rotateRight(x);
        }
        if (x.right == null) {
            return null;
        }
        if (!isRed(x.right) && !isRed(x.right.left)) {
            x = moveRedRight(x);
        }
        x.right = deleteMax(x);
        return fixUp(x);
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
