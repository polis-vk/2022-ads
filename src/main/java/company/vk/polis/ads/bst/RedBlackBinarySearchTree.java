package company.vk.polis.ads.bst;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * LLRB implementation of binary search tree.
 */
public class RedBlackBinarySearchTree<Key extends Comparable<Key>, Value>
        implements BinarySearchTree<Key, Value> {

    private Node root;
    private int size = 0;

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
        return get(root, key);
    }

    @Override
    public void put(@NotNull Key key, @NotNull Value value) {
        Value oldValue = get(key);
        if (oldValue == null) {
            size++;
        }
        root = put(root, key, value);
        root.color = BLACK;
    }

    @Nullable
    @Override
    public Value remove(@NotNull Key key) {
        Value oldValue = get(key);
        if (oldValue == null) {
            return null;
        }
        size--;
        root = remove(root, key);
        return oldValue;
    }

    @Nullable
    @Override
    public Key min() {
        Node minNode = minNode(root);
        if (minNode == null) {
            return null;
        }
        return minNode.key;
    }

    @Nullable
    @Override
    public Value minValue() {
        Node minNode = minNode(root);
        if (minNode == null) {
            return null;
        }
        return minNode.value;
    }

    @Nullable
    @Override
    public Key max() {
        Node maxNode = maxNode(root);
        if (maxNode == null) {
            return null;
        }
        return maxNode.key;
    }

    @Nullable
    @Override
    public Value maxValue() {
        Node maxNode = maxNode(root);
        if (maxNode == null) {
            return null;
        }
        return maxNode.value;
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
        if (x == null) {
            return 0;
        }
        return 1 + Math.max(height(x.left), height(x.right));
    }

    private Key floor(Node x, Key key) {
        if (x == null) {
            return null;
        }
        int comparison = x.key.compareTo(key);
        if (comparison <= 0) {
            Key rightFloorKey = floor(x.right, key);
            if (rightFloorKey == null) {
                return x.key;
            }
            return rightFloorKey;
        }
        return floor(x.left, key);
    }

    private Key ceil(Node x, Key key) {
        if (x == null) {
            return null;
        }
        int comparison = x.key.compareTo(key);
        if (x.key.equals("8")) {
            System.out.println(comparison);
        }
        if (comparison >= 0) {
            Key leftCeilKey = ceil(x.left, key);
            if (leftCeilKey != null) {
                return leftCeilKey;
            }
            return x.key;
        }
        return ceil(x.right, key);
    }

    private Node minNode(Node x) {
        if (x == null) {
            return null;
        }
        if (x.left == null) {
            return x;
        }
        return minNode(x.left);
    }

    private Node maxNode(Node x) {
        if (x == null) {
            return null;
        }
        if (x.right == null) {
            return x;
        }
        return maxNode(x.right);
    }

    private Value get(Node x, Key key) {
        if (x == null) {
            return null;
        }
        int comparison = key.compareTo(x.key);
        if (comparison < 0) {
            return get(x.left, key);
        }
        if (comparison > 0) {
            return get(x.right, key);
        }
        return x.value;
    }

    private boolean isRed(Node x) {
        return x != null &&
                x.color == RED;
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

    private void flipColors(Node x) {
        x.color = !x.color;
        x.left.color = !x.left.color;
        x.right.color = !x.right.color;
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

    private Node remove(Node x, Key key) {
        if (x == null) {
            return null;
        }
        int comparison = key.compareTo(x.key);
        if (comparison < 0) {
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
            } else if (comparison == 0 && x.right == null) {
                return null;
            } else {
                if (x.right != null && !isRed(x.right) && !isRed(x.right.left)) {
                    x = moveRedRight(x);
                }
                if (key.compareTo(x.key) == 0) {
                    Node minNodeRight = minNode(x.right);
                    x.key = minNodeRight.key;
                    x.value = minNodeRight.value;
                    x.right = removeMin(x.right);
                } else {
                    x.right = remove(x.right, key);
                }
            }
        }
        return fixUp(x);
    }

    private Node removeMin(Node x) {
        if (x.left == null) {
            return null;
        }
        if (!isRed(x.left) && !isRed(x.left.left)) {
            x = moveRedLeft(x);
        }
        x.left = removeMin(x.left);
        return fixUp(x);
    }

    private Node put(Node x, Key key, Value value) {
        if (x == null) {
            Node newNode = new Node();
            newNode.key = key;
            newNode.value = value;
            newNode.color = RED;
            return newNode;
        }
        int comparison = key.compareTo(x.key);
        if (comparison < 0) {
            x.left = put(x.left, key, value);
        } else if (comparison > 0) {
            x.right = put(x.right, key, value);
        } else {
            x.value = value;
        }
        return fixUp(x);
    }
}
