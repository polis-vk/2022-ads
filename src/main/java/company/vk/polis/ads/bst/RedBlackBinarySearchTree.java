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
    private Value lastRemValue;

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

    private Value get(Node node, Key key) {
        if (node == null) {
            return null;
        }
        int cmp = key.compareTo(node.key);
        if (cmp < 0) {
            return get(node.left, key);
        }
        if (cmp > 0) {
            return get(node.right, key);
        }
        return node.value;
    }

    @Override
    public void put(@NotNull Key key, @NotNull Value value) {
        root = put(root, key, value);
        root.color = BLACK;
    }

    private Node put(Node node, Key key, Value value) {
        if (node == null) {
            size++;
            return new Node(key, value, RED);
        }
        int cmp = key.compareTo(node.key);
        if (cmp < 0) {
            node.left = put(node.left, key, value);
        } else if (cmp > 0) {
            node.right = put(node.right, key, value);
        } else {
            node.value = value;
        }
        return fixUp(node);
    }

    @Nullable
    @Override
    public Value remove(@NotNull Key key) {
        root = remove(root, key);
        Value value = lastRemValue;
        lastRemValue = null;
        return value;
    }

    private Node remove(Node node, Key key) {
        if (node == null) {
            return null;
        }
        if (key.compareTo(node.key) < 0) {
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
            } else if (key.compareTo(node.key) == 0 && node.right == null) {
                size--;
                lastRemValue = node.value;
                return null;
            } else {
                if (node.right != null && !isRed(node.right) && !isRed(node.right.left)) {
                    node = moveRedRight(node);
                }
                if (key.compareTo(node.key) == 0 && node.right != null) {//!!!
                    size--;
                    lastRemValue = node.value;
                    Node min = minNode(node.right);
                    node.key = min.key;
                    node.value = min.value;
                    node.right = deleteMin(node.right);
                } else {
                    node.right = remove(node.right, key);
                }
            }

        }
        return fixUp(node);
    }

    @Nullable
    @Override
    public Key min() {
        return (root == null) ? null : minNode(root).key;
    }

    @Nullable
    @Override
    public Value minValue() {
        return (root == null) ? null : minNode(root).value;
    }

    private Node minNode(Node node) {
        if (node.left == null) {
            return node;
        }
        return minNode(node.left);
    }

    @Nullable
    @Override
    public Key max() {
        return (root == null) ? null : maxNode(root).key;
    }

    @Nullable
    @Override
    public Value maxValue() {
        return (root == null) ? null : maxNode(root).value;
    }

    private Node maxNode(Node node) {
        if (node.right == null) {
            return node;
        }
        return maxNode(node.right);
    }

    @Nullable
    @Override
    public Key floor(@NotNull Key key) {
        Node floorNode = floorNode(root, key);
        return floorNode == null ? null : floorNode.key;
    }

    private Node floorNode(Node currNode, Key key) {
        if (currNode == null) {
            return null;
        }
        int cmp = key.compareTo(currNode.key);
        if (cmp < 0) {
            return floorNode(currNode.left, key);
        }
        if (cmp > 0) {
            Node rightNode = floorNode(currNode.right, key);
            return rightNode == null ? currNode : rightNode;
        }
        return currNode;
    }

    @Nullable
    @Override
    public Key ceil(@NotNull Key key) {
        Node ceilNode = ceilNode(root, key);
        return ceilNode == null ? null : ceilNode.key;
    }

    private Node ceilNode(Node currNode, Key key) {
        if (currNode == null) {
            return null;
        }
        int cmp = key.compareTo(currNode.key);
        if (cmp > 0) {
            return ceilNode(currNode.right, key);
        }
        if (cmp < 0) {
            Node leftNode = ceilNode(currNode.left, key);
            return leftNode == null ? currNode : leftNode;
        }
        return currNode;
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

    private int height(Node node) {
        if (node == null) {
            return 0;
        }
        return 1 + Math.max(height(node.left), height(node.right));
    }

    private boolean isRed(Node node) {
        return node != null && node.color == RED;
    }

    private Node rotateLeft(Node parentNode) {
        Node childNode = parentNode.right;
        parentNode.right = childNode.left;
        childNode.left = parentNode;
        childNode.color = parentNode.color;
        parentNode.color = RED;
        return childNode;
    }

    private Node rotateRight(Node parentNode) {
        Node childNode = parentNode.left;
        parentNode.left = childNode.right;
        childNode.right = parentNode;
        childNode.color = parentNode.color;
        parentNode.color = RED;
        return childNode;
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

}
