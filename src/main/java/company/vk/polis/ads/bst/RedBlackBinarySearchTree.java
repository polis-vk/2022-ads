package company.vk.polis.ads.bst;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * LLRB implementation of binary search tree.
 */
public class RedBlackBinarySearchTree<Key extends Comparable<Key>, Value>
        implements BinarySearchTree<Key, Value> {

    private int size = 0;
    private Node root = null;

    private static final boolean BLACK = false;
    private static final boolean RED = true;

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

    private Value get(Node node, Key key) {
        if (node == null) {
            return null;
        }
        if (key.compareTo(node.key) < 0) {
            return get(node.left, key);
        }
        if (key.compareTo(node.key) > 0) {
            return get(node.right, key);
        }
        return node.value;
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
        } else if (key.compareTo(x.key) > 0) {
            x.right = put(x.right, key, value);
        } else {
            x.value = value;
        }
        return fixUp(x);
    }

    @Nullable
    @Override
    public Value remove(@NotNull Key key) {
        Value deletedValue = get(key);
        if (deletedValue == null) {
            return null;
        }
        root = delete(root, key);
        size--;
        return deletedValue;
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
                if (x.right != null && !isRed(x.right) &&
                        !isRed(x.right.left)) {
                    x = moveRedRight(x);
                }
                if (x.key.equals(key)) {
                    Node min = getMin(x.right);
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

    private Node getMin(Node node) {
        if (root == null) {
            return null;
        }
        Node curNode = node;
        while (curNode.left != null) {
            curNode = curNode.left;
        }
        return curNode;
    }

    @Nullable
    @Override
    public Key min() {
        Node minNode = getMin(root);
        return minNode == null ? null : minNode.key;
    }

    @Nullable
    @Override
    public Value minValue() {
        Node minNode = getMin(root);
        return minNode == null ? null : minNode.value;
    }

    private Node getMax(Node node) {
        if (root == null) {
            return null;
        }
        Node curNode = node;
        while (curNode.right != null) {
            curNode = curNode.right;
        }
        return curNode;
    }

    @Nullable
    @Override
    public Key max() {
        Node maxNode = getMax(root);
        return maxNode == null ? null : maxNode.key;
    }

    @Nullable
    @Override
    public Value maxValue() {
        Node maxNode = getMax(root);
        return maxNode == null ? null : maxNode.value;
    }

    @Nullable
    @Override
    public Key floor(@NotNull Key key) {
        if (root == null) {
            return null;
        }
        if (containsKey(key)) {
            return key;
        }
        Node curNode = root;
        while (curNode != null) {
            if (key.compareTo(curNode.key) < 0) {
                curNode = curNode.left;
            } else if (key.compareTo(curNode.key) > 0) {
                if (curNode.right == null || key.compareTo(curNode.right.key) < 0) {
                    break;
                }
                curNode = curNode.right;
            } else {
                break;
            }
        }
        return curNode == null ? null : curNode.key;
    }

    @Nullable
    @Override
    public Key ceil(@NotNull Key key) {
        if (root == null) {
            return null;
        }
        if (containsKey(key)) {
            return key;
        }
        Node curNode = root;
        while (curNode != null) {
            if (key.compareTo(curNode.key) < 0) {
                if (curNode.left == null || key.compareTo(curNode.left.key) > 0) {
                    break;
                }
                curNode = curNode.left;
            } else if (key.compareTo(curNode.key) > 0) {
                curNode = curNode.right;
            } else {
                break;
            }
        }
        return curNode == null ? null : curNode.key;
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
        int res = 0;
        while (node != null) {
            node = node.left;
            res++;
        }
        return res;
    }

    boolean isRed(Node node) {
        return node != null && node.color == RED;
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

    private Node rotateRight(Node y) {
        Node x = y.left;
        y.left = x.right;
        x.right = y;
        x.color = y.color;
        y.color = RED;
        return x;
    }

    private Node rotateLeft(Node x) {
        Node y = x.right;
        x.right = y.left;
        y.left = x;
        y.color = x.color;
        x.color = RED;
        return y;
    }

    private void flipColors(Node x) {
        x.color = !x.color;
        x.left.color = !x.left.color;
        x.right.color = !x.right.color;
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
        if (x.left == null)
            return null;
        if (!isRed(x.left) && !isRed(x.left.left))
            x = moveRedLeft(x);
        x.left = deleteMin(x.left);
        return fixUp(x);
    }

    private void deleteMin() {
        root = deleteMin(root);
        root.color = BLACK;
    }

    private Node moveRedRight(Node x) {
        flipColors(x);
        if (isRed(x.left.left)) {
            x = rotateRight(x);
            flipColors(x);
        }
        return x;
    }

    private Node deleteMax(Node x) {
        if (isRed(x.left)) x = rotateRight(x);
        if (x.right == null) return null;
        if (!isRed(x.right) &&
                !isRed(x.right.left))
            x = moveRedRight(x);
        x.right = deleteMax(x.right);
        return fixUp(x);
    }

    void deleteMax() {
        root = deleteMax(root);
        root.color = BLACK;
    }
}
