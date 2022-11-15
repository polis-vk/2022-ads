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

    private Node root;
    private int size;

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

    @Nullable
    @Override
    public Value remove(@NotNull Key key) {
        Value result = get(key);
        if (result == null) {
            return null;
        }

        root = delete(root, key);
        size--;

        return result;
    }

    @Nullable
    @Override
    public Key min() {
        Node answer = getMin(root);
        return (answer == null) ? null : answer.key;
    }

    @Nullable
    @Override
    public Value minValue() {
        Node answer = getMin(root);
        return (answer == null) ? null : answer.value;
    }

    @Nullable
    @Override
    public Key max() {
        Node answer = getMax(root);
        return (answer == null) ? null : answer.key;
    }

    @Nullable
    @Override
    public Value maxValue() {
        Node answer = getMax(root);
        return (answer == null) ? null : answer.value;
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

        Node currentNode = root;

        while (currentNode != null) {
            if (key.compareTo(currentNode.key) < 0) {
                currentNode = currentNode.left;
            } else if (key.compareTo(currentNode.key) > 0) {
                if (currentNode.right == null || key.compareTo(currentNode.right.key) < 0) {
                    break;
                }
                currentNode = currentNode.right;
            } else {
                return currentNode.key;
            }
        }

        return (currentNode == null) ? null : currentNode.key;
    }

    @Nullable
    @Override
    public Key ceil(@NotNull Key key) {
        if (key == null) {
            return null;
        }

        if (containsKey(key)) {
            return key;
        }

        Node currentNode = root;

        while (currentNode != null) {
            if (key.compareTo(currentNode.key) < 0) {
                if (currentNode.left == null || key.compareTo(currentNode.left.key) > 0) {
                    break;
                }

                currentNode = currentNode.left;
            } else if (key.compareTo(currentNode.key) > 0) {
                currentNode = currentNode.right;
            } else {
                return currentNode.key;
            }
        }

        return (currentNode == null) ? null : currentNode.key;
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
        Node currentNode = root;

        while (currentNode != null) {
            if (currentNode.color == BLACK) {
                height++;
            }

            currentNode = currentNode.left;
        }

        return height;
    }

    private Node getMin(Node node) {
        if (root == null) {
            return null;
        }

        if (node.left != null) {
            return getMin(node.left);
        }

        return node;
    }

    private Node getMax(Node node) {
        if (root == null) {
            return null;
        }

        if (node.right != null) {
            return getMax(node.right);
        }

        return node;
    }

    private boolean isRed(Node node) {
        return (node != null && node.color == RED);
    }

    private Node rotateLeft(Node x) {
        Node y = x.right;
        x.right = y.left;
        y.left = x;
        y.color = x.color;
        x.color = RED;

        return y;
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

    private Node rotateRight(Node y) {
        Node x = y.left;
        y.left = x.right;
        x.right = y;
        x.color = y.color;
        y.color = RED;

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

    private void deleteMin() {
        root = deleteMin(root);
        root.color = BLACK;
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

    private void deleteMax() {
        root = deleteMax(root);
        root.color = BLACK;
    }

    private Node deleteMax(Node x) {
        if (isRed(x.left)) {
            x = rotateRight(x);
        }

        if (x.right == null) {
            return null;
        }

        if (!isRed(x.right) && !isRed(x.right.left)) {
            x = moveRedRight(x);
        }

        x.right = deleteMax(x.right);
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
                    Node min = getMin(x.right);
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
}
