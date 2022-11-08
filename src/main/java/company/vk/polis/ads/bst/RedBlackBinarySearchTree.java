package company.vk.polis.ads.bst;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Objects;

/**
 * LLRB implementation of binary search tree.
 */
public class RedBlackBinarySearchTree<Key extends Comparable<Key>, Value>
        implements BinarySearchTree<Key, Value> {

    private static final boolean BLACK = false;
    private static final boolean RED = true;

    private Value lastRemoved;
    private Node root;
    private int size;

    private class Node {
        Key key;
        Value value;
        Node left;
        Node right;
        boolean color;

        Node(Key key, Value value, boolean color) {
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
        lastRemoved = null;
        root = delete(root, key);
        if (lastRemoved != null) {
            size--;
        }
        return lastRemoved;
    }

    @Nullable
    @Override
    public Key min() {
        if (root == null) {
            return null;
        }
        return min(root).key;
    }

    @Nullable
    @Override
    public Value minValue() {
        if (root == null) {
            return null;
        }
        return min(root).value;
    }

    @Nullable
    @Override
    public Key max() {
        if (root == null) {
            return null;
        }
        return max(root).key;
    }

    @Nullable
    @Override
    public Value maxValue() {
        if (root == null) {
            return null;
        }
        return max(root).value;
    }

    @Nullable
    @Override
    public Key floor(@NotNull Key key) {
        Node floorNode = floor(root, key);
        if (floorNode == null) {
            return null;
        }
        return floorNode.key;
    }

    @Nullable
    @Override
    public Key ceil(@NotNull Key key) {
        Node ceilNode = ceil(root, key);
        if (ceilNode == null) {
            return null;
        }
        return ceilNode.key;
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

    private boolean isRed(Node node) {
        return node != null && node.color == RED;
    }

    private Value get(Node node, Key key) {
        if (node == null) {
            return null;
        }
        int compareResult = key.compareTo(node.key);
        if (compareResult < 0) {
            return get(node.left, key);
        }
        if (compareResult > 0) {
            return get(node.right, key);
        }
        return node.value;
    }

    private Node put(Node node, Key key, Value value) {
        if (node == null) {
            size++;
            return new Node(key, value, RED);
        }
        int compareResult = key.compareTo(node.key);
        if (compareResult < 0) {
            node.left = put(node.left, key, value);
        } else if (compareResult > 0) {
            node.right = put(node.right, key, value);
        } else {
            node.value = value;
        }
        return fixUp(node);
    }

    private Node delete(Node node, Key key) {
        if (node == null) {
            return null;
        }
        int compareResult = key.compareTo(node.key);
        if (compareResult < 0) {
            if (node.left != null) {
                if (!isRed(node.left) && !isRed(node.left.left)) {
                    node = moveRedLeft(node);
                }
                node.left = delete(node.left, key);
            }
        } else {
            if (isRed(node.left)) {
                node = rotateRight(node);
                node.right = delete(node.right, key);
            } else if (compareResult == 0 && node.right == null) {
                lastRemoved = node.value;
                return null;
            } else {
                if (node.right != null && !isRed(node.right) && !isRed(node.right.left)) {
                    node = moveRedRight(node);
                }
                if (key.compareTo(node.key) == 0) {
                    lastRemoved = node.value;
                    Node min = min(Objects.requireNonNull(node.right));
                    node.key = min.key;
                    node.value = min.value;
                    node.right = deleteMin(node.right);
                } else {
                    node.right = delete(node.right, key);
                }
            }
        }
        return fixUp(node);
    }

    private Node deleteMin(Node node) {
        if (node.left == null) {
            return node.right;
        }
        if (!isRed(node.left) && !isRed(node.left.left)) {
            node = moveRedLeft(node);
        }
        node.left = deleteMin(node.left);
        return fixUp(node);
    }

    private Node min(Node node) {
        if (node.left == null) {
            return node;
        }
        return min(node.left);
    }

    private Node max(Node node) {
        if (node.right == null) {
            return node;
        }
        return max(node.right);
    }

    private Node floor(Node node, Key key) {
        if (node == null) {
            return null;
        }
        int compareResult = key.compareTo(node.key);
        if (compareResult < 0) {
            return floor(node.left, key);
        }
        if (compareResult == 0) {
            return node;
        }
        Node nextNode = floor(node.right, key);
        if (nextNode != null) {
            return nextNode;
        }
        return node;
    }

    private Node ceil(Node node, Key key) {
        if (node == null) {
            return null;
        }
        int compareResult = key.compareTo(node.key);
        if (compareResult > 0) {
            return ceil(node.right, key);
        }
        if (compareResult == 0) {
            return node;
        }
        Node nextNode = ceil(node.left, key);
        if (nextNode != null) {
            return nextNode;
        }
        return node;
    }

    private Node rotateLeft(Node node) {
        Node rightNode = node.right;
        node.right = rightNode.left;
        rightNode.left = node;
        rightNode.color = node.color;
        node.color = RED;
        return rightNode;
    }

    private Node rotateRight(Node node) {
        Node leftNode = node.left;
        node.left = leftNode.right;
        leftNode.right = node;
        leftNode.color = node.color;
        node.color = RED;
        return leftNode;
    }

    private void flipColors(Node node) {
        node.color = !node.color;
        node.left.color = !node.left.color;
        node.right.color = !node.right.color;
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
        return node;
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

    private Node moveRedRight(Node node) {
        flipColors(node);
        if (isRed(node.left.left)) {
            node = rotateRight(node);
            flipColors(node);
        }
        return node;
    }

    private int height(Node node) {
        if (node == null) {
            return 0;
        }
        return 1 + Math.max(height(node.left), height(node.right));
    }

}
