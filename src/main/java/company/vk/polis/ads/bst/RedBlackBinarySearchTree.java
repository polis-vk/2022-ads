package company.vk.polis.ads.bst;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * LLRB implementation of binary search tree.
 */
public class RedBlackBinarySearchTree<Key extends Comparable<Key>, Value> implements BinarySearchTree<Key, Value> {

    private static final boolean BLACK = false;
    private static final boolean RED = true;
    private Node root;

    private class Node {
        Key key;
        Value value;
        Node left;
        Node right;
        boolean color;
        int size;

        public Node(Key key, Value value, boolean color, int size) {
            this.key = key;
            this.value = value;
            this.color = color;
            this.size = size;
        }
    }

    @Nullable
    @Override
    public Value get(@NotNull Key key) {
        return get(key, root);
    }

    private Value get(Key key, Node node) {
        while (node != null) {
            if (key.compareTo(node.key) < 0) {
                node = node.left;
            } else if (key.compareTo(node.key) > 0) {
                node = node.right;
            } else {
                return node.value;
            }
        }
        return null;
    }

    @Override
    public void put(@NotNull Key key, @NotNull Value value) {
        root = put(key, value, root);
        root.color = BLACK;
    }

    private Node put(Key key, Value val, Node node) {
        if (node == null) {
            return new Node(key, val, RED, 1);
        }
        if (key.compareTo(node.key) < 0) {
            node.left = put(key, val, node.left);
        } else if (key.compareTo(node.key) > 0) {
            node.right = put(key, val, node.right);
        } else {
            node.value = val;
        }
        return fixUp(node);
    }

    @Nullable
    @Override
    public Value remove(@NotNull Key key) {
        Value result = get(key);
        if (result == null) {
            return null;
        }
        root = remove(key, root);
        return result;
    }

    private Node remove(Key key, Node node) {
        if (node == null) {
            return null;
        }
        if (key.compareTo(node.key) < 0) {
            if (node.left != null) {
                if (!isRed(node.left) && !isRed(node.left.left)) {
                    node = moveRedLeft(node);
                }
                node.left = remove(key, node.left);
            }
        } else {
            if (isRed(node.left)) {
                node = rotateRight(node);
                node.right = remove(key, node.right);
            } else if (node.key.compareTo(key) == 0 && node.right == null) {
                return null;
            } else {
                if (node.right != null && !isRed(node.right) && !isRed(node.right.left)) {
                    node = moveRedRight(node);
                }
                if (node.key.compareTo(key) == 0) {
                    Node min = min(node.right);
                    node.key = min.key;
                    node.value = min.value;
                    node.right = deleteMin(node.right);
                } else {
                    node.right = remove(key, node.right);
                }
            }
        }
        return fixUp(node);
    }

    private Node deleteMin(Node node) {
        if (node.left == null) {
            return null;
        }
        if (!isRed(node.left) && !isRed(node.left.left)) {
            node = moveRedLeft(node);
        }
        node.left = deleteMin(node.left);
        return fixUp(node);
    }

    @Nullable
    @Override
    public Key min() {
        return isEmpty() ? null : min(root).key;
    }

    @Nullable
    @Override
    public Value minValue() {
        return isEmpty() ? null : min(root).value;
    }

    private Node min(Node node) {
        return node.left == null ? node : min(node.left);
    }

    @Nullable
    @Override
    public Key max() {
        return isEmpty() ? null : max(root).key;
    }

    @Nullable
    @Override
    public Value maxValue() {
        return isEmpty() ? null : max(root).value;
    }

    private Node max(Node node) {
        return node.right == null ? node : max(node.right);
    }

    @Nullable
    @Override
    public Key floor(@NotNull Key key) {
        Node node = floor(key, root);
        return node == null ? null : node.key;
    }

    private Node floor(Key key, Node node) {
        if (node == null) {
            return null;
        }
        if (key.compareTo(node.key) < 0) {
            return floor(key, node.left);
        }
        if (key.compareTo(node.key) > 0) {
            Node floored = floor(key, node.right);
            return floored != null ? floored : node;
        }
        return node;
    }

    @Nullable
    @Override
    public Key ceil(@NotNull Key key) {
        Node node = ceil(key, root);
        return node == null ? null : node.key;
    }

    private Node ceil(Key key, Node node) {
        if (node == null) {
            return null;
        }
        if (key.compareTo(node.key) < 0) {
            Node t = ceil(key, node.left);
            return t != null ? t : node;
        }
        if (key.compareTo(node.key) > 0) {
            return ceil(key, node.right);
        }
        return node;
    }

    @Override
    public int size() {
        return size(root);
    }

    private int size(Node node) {
        return node == null ? 0 : node.size;
    }

    /**
     * Только для тестов
     * Саму высоту хранить не обязательно, достаточно сделать рекурсивное вычисление
     */
    @Override
    public int height() {
        return height(root);
    }

    private int height(Node node) {
        return node == null ? 0 : Math.max(height(node.left), height(node.right)) + 1;
    }

    private boolean isRed(Node node) {
        return node != null && node.color == RED;
    }

    private Node rotateRight(Node node) {
        Node left = node.left;
        node.left = left.right;
        left.right = node;
        left.color = node.color;
        node.color = RED;
        left.size = node.size;
        node.size = size(node.left) + size(node.right) + 1;
        return left;
    }

    private Node rotateLeft(Node node) {
        Node right = node.right;
        node.right = right.left;
        right.left = node;
        right.color = node.color;
        node.color = RED;
        right.size = node.size;
        node.size = size(node.left) + size(node.right) + 1;
        return right;
    }

    private void flipColors(Node node) {
        node.color = !node.color;
        node.left.color = !node.left.color;
        node.right.color = !node.right.color;
    }

    private Node moveRedLeft(Node node) {
        flipColors(node);
        if (isRed(node.right.left)) {
            node.right = rotateRight(node.right);
            node = rotateLeft(node);
            flipColors(node);
        }
        return node;
    }

    private Node moveRedRight(Node x) {
        flipColors(x);
        if (isRed(x.left.left)) {
            x = rotateRight(x);
            flipColors(x);
        }
        return x;
    }

    private Node fixUp(Node node) {
        if (isRed(node.right) && !isRed(node.left)) {
            node = rotateLeft(node);
        }
        if (isRed(node.left) && isRed(node.left.left)) {
            node = rotateRight(node);
        }
        if (isRed(node.left) && isRed(node.right)) {
            flipColors(node);
        }
        node.size = size(node.left) + size(node.right) + 1;
        return node;
    }

}
