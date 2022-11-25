package company.vk.polis.ads.bst;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * LLRB implementation of binary search tree.
 */
public class RedBlackBinarySearchTree<K extends Comparable<K>, V>
        implements BinarySearchTree<K, V> {

    private static final boolean BLACK = false;
    private static final boolean RED = true;

    private Node root;
    private int size;

    private class Node {
        K key;
        V value;
        Node left;
        Node right;
        boolean color;

        public Node(K key, V value, boolean color) {
            this.key = key;
            this.value = value;
            this.color = color;
        }
    }

    @Nullable
    @Override
    public V get(@NotNull K key) {
        return get(root, key);
    }

    private V get(Node node, K key) {
        if (node == null) {
            return null;
        }
        if (key.compareTo(node.key) < 0) {
            return get(node.left, key);
        } else if (key.compareTo(node.key) > 0) {
            return get(node.right, key);
        }
        return node.value;
    }

    @Override
    public void put(@NotNull K key, @NotNull V value) {
        root = put(root, key, value);
        root.color = BLACK;
    }

    private Node put(Node node, K key, V value) {
        if (node == null) {
            size++;
            return new Node(key, value, RED);
        }
        if (key.compareTo(node.key) < 0) {
            node.left = put(node.left, key, value);
        } else if (key.compareTo(node.key) > 0) {
            node.right = put(node.right, key, value);
        } else {
            node.value = value;
        }
        return fixUp(node);
    }

    @Nullable
    @Override
    public V remove(@NotNull K key) {
        V value = get(key);
        if (value == null) {
            return null;
        }
        root = remove(root, key);
        size--;
        if (root != null) {
            root.color = BLACK;
        }
        return value;
    }

    private Node remove(Node node, K key) {
        if (node == null) {
            return null;
        }
        int compareResult = key.compareTo(node.key);
        if (compareResult < 0 && node.left != null) {
            if (!isRed(node.left) && !isRed(node.left.left)) {
                node = moveRedLeft(node);
            }
            node.left = remove(node.left, key);
        } else {
            if (isRed(node.left)) {
                node = rotateRight(node);
                node.right = remove(node.right, key);
            } else if (compareResult == 0 && node.right == null) {
                return null;
            } else {
                if (!isRed(node.right) && !isRed(node.right.left)) {
                    node = moveRedRight(node);
                }
                if (compareResult == 0) {
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
    public K min() {
        Node node = minNode(root);
        if (node == null) {
            return null;
        }
        return node.key;
    }

    @Nullable
    @Override
    public V minValue() {
        Node node = minNode(root);
        if (node == null) {
            return null;
        }
        return node.value;
    }

    private Node minNode(Node node) {
        if (node == null) {
            return null;
        }
        if (node.left == null) {
            return node;
        }
        return minNode(node.left);
    }

    @Nullable
    @Override
    public K max() {
        Node node = maxNode(root);
        if (node == null) {
            return null;
        }
        return node.key;
    }

    @Nullable
    @Override
    public V maxValue() {
        Node node = maxNode(root);
        if (node == null) {
            return null;
        }
        return node.value;
    }

    private Node maxNode(Node node) {
        if (node == null) {
            return null;
        }
        if (node.right == null) {
            return node;
        }
        return maxNode(node.right);
    }

    @Nullable
    @Override
    public K floor(@NotNull K key) {
        return floor(root, key);
    }

    private K floor(Node node, K key) {
        if (node == null) {
            return null;
        }
        if (key.compareTo(node.key) < 0) {
            return floor(node.left, key);
        } else if (key.compareTo(node.key) > 0) {
            K atRight = floor(node.right, key);
            if (atRight != null && atRight.compareTo(node.key) > 0) {
                return atRight;
            }
        }
        return node.key;
    }

    @Nullable
    @Override
    public K ceil(@NotNull K key) {
        return ceil(root, key);
    }

    private K ceil(Node node, K key) {
        if (node == null) {
            return null;
        }
        if (key.compareTo(node.key) > 0) {
            return ceil(node.right, key);
        } else if (key.compareTo(node.key) < 0) {
            K atLeft = ceil(node.left, key);
            if (atLeft != null && atLeft.compareTo(node.key) < 0) {
                return atLeft;
            }
        }
        return node.key;
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
        int childHeight = Math.max(height(node.left), height(node.right));
        if (node.color == BLACK) {
            childHeight += 1;
        }
        return childHeight;
    }

    private boolean isRed(Node node) {
        return node != null && node.color == RED;
    }

    private Node rotateLeft(Node node) {
        Node right = node.right;
        node.right = right.left;
        right.left = node;
        right.color = node.color;
        node.color = RED;
        return right;
    }

    private Node rotateRight(Node node) {
        Node left = node.left;
        node.left = left.right;
        left.right = node;
        left.color = node.color;
        node.color = RED;
        return left;
    }

    private void flipColors(Node node) {
        node.color = !node.color;
        if (node.left != null) {
            node.left.color = !node.left.color;
        }
        if (node.right != null) {
            node.right.color = !node.right.color;
        }
    }

    private Node fixUp(Node node) {
        if (!isRed(node.left) && isRed(node.right)) {
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
        if (node.right != null && isRed(node.right.left)) {
            node.right = rotateRight(node.right);
            node = rotateLeft(node);
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

    private Node moveRedRight(Node node) {
        flipColors(node);
        if (node.left != null && isRed(node.left.right)) {
            node.left = rotateLeft(node.left);
            node = rotateRight(node);
            flipColors(node);
        }
        return node;
    }
}
