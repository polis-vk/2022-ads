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
    private Value lastRemovedValue;
    private int size;

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
        lastRemovedValue = null;
        root = remove(root, key);
        return lastRemovedValue;
    }

    @Nullable
    @Override
    public Key min() {
        return root == null ? null : getMinNode(root).key;
    }

    @Nullable
    @Override
    public Value minValue() {
        return root == null ? null : getMinNode(root).value;
    }

    @Nullable
    @Override
    public Key max() {
        return root == null ? null : getMaxNode(root).key;
    }

    @Nullable
    @Override
    public Value maxValue() {
        return root == null ? null : getMaxNode(root).value;
    }

    @Nullable
    @Override
    public Key floor(@NotNull Key key) {
        Node result = getFloor(root, key);
        return result == null ? null : result.key;
    }

    @Nullable
    @Override
    public Key ceil(@NotNull Key key) {
        Node result = getCeil(root, key);
        return result == null ? null : result.key;
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
        return getHeight(root);
    }

    private Value get(Node node, Key key) {
        if (node == null) {
            return null;
        }

        int cmpResult = key.compareTo(node.key);
        if (cmpResult < 0) {
            return get(node.left, key);
        }
        if (cmpResult > 0) {
            return get(node.right, key);
        }
        return node.value;
    }

    private Node put(Node node, Key key, Value value) {
        if (node == null) {
            size++;
            return new Node(key, value, RED);
        }

        int cmpResult = key.compareTo(node.key);
        if (cmpResult < 0) {
            node.left = put(node.left, key, value);
        } else if (cmpResult > 0) {
            node.right = put(node.right, key, value);
        } else {
            node.value = value;
        }
        return fixUp(node);
    }

    private Node remove(Node node, Key key) {
        if (node == null) {
            return null;
        }

        int cmpResult = key.compareTo(node.key);
        if (cmpResult < 0) {
            if (node.left != null) {
                if (!isRed(node.left) && !isRed(node.left.left)) {
                    node = moveRedLeft(node);
                }
                node.left = remove(node.left, key);
            }
        } else {
            if (isRed(node.left)) {
                node = rotateRight(node);
                node.right = remove(node.right, key);
            } else if (cmpResult == 0 && node.right == null) {
                size--;
                lastRemovedValue = node.value;
                return null;
            } else {
                if (node.right != null && !isRed(node.right) && !isRed(node.right.left)) {
                    node = moveRedRight(node);
                }

                if (key.compareTo(node.key) == 0) {
                    size--;
                    lastRemovedValue = node.value;
                    Node min = getMinNode(node.right);
                    node.key = min.key;
                    node.value = min.value;
                    node.right = removeMin(node.right);
                } else {
                    node.right = remove(node.right, key);
                }
            }
        }
        return fixUp(node);
    }

    private Node removeMin(Node node) {
        if (node.left == null) {
            return null;
        }
        if (!isRed(node.left) && !isRed(node.left.left)) {
            node = moveRedLeft(node);
        }

        node.left = removeMin(node.left);
        return fixUp(node);
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

    private Node getMinNode(Node node) {
        if (node.left != null) {
            node = getMinNode(node.left);
        }

        return node;
    }

    private Node getMaxNode(Node node) {
        if (node.right != null) {
            node = getMaxNode(node.right);
        }

        return node;
    }

    private Node getFloor(Node node, Key key) {
        if (node == null) {
            return null;
        }

        int cmpResult = key.compareTo(node.key);
        if (cmpResult == 0) {
            return node;
        }
        if (cmpResult < 0) {
            return getFloor(node.left, key);
        }

        Node nextNode = getFloor(node.right, key);
        return nextNode == null ? node : nextNode;
    }

    private Node getCeil(Node node, Key key) {
        if (node == null) {
            return null;
        }

        int cmpResult = key.compareTo(node.key);
        if (cmpResult == 0) {
            return node;
        }
        if (cmpResult > 0) {
            return getCeil(node.right, key);
        }

        Node nextNode = getCeil(node.left, key);
        return nextNode == null ? node : nextNode;
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

    private boolean isRed(Node node) {
        return node != null && node.color == RED;
    }

    private int getHeight(Node root) {
        return root == null ? 0 : 1 + Math.max(getHeight(root.left), getHeight(root.right));
    }
}

