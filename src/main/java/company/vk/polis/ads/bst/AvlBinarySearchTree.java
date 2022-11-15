package company.vk.polis.ads.bst;

import org.jetbrains.annotations.NotNull;

/**
 * AVL implementation of binary search tree.
 */
public class AvlBinarySearchTree<Key extends Comparable<Key>, Value> implements BinarySearchTree<Key, Value> {

    private Node root;
    private int size;

    private class Node {
        Key key;
        Value value;
        Node left;
        Node right;
        int height;

        Node(Key key, Value value, int height) {
            this.key = key;
            this.value = value;
            this.height = height;
        }
    }

    private Node rotateRight(Node y) {
        Node x = y.left;
        y.left = x.right;
        x.right = y;
        fixHeight(y);
        fixHeight(x);
        return x;
    }

    private Node rotateLeft(Node x) {
        Node y = x.right;
        x.right = y.left;
        y.left = x;
        fixHeight(x);
        fixHeight(y);
        return y;
    }

    private int factor(Node x) {
        return x == null ? 0 : height(x.left) - height(x.right);
    }

    private int max(int height, int height1) {
        return Math.max(height, height1);
    }

    private Node balance(Node x) {
        if (factor(x) == 2) {
            if (factor(x.left) < 0) {
                x.left = rotateLeft(x.left);
            }
            return rotateRight(x);
        }
        if (factor(x) == -2) {
            if (factor(x.right) > 0) {
                x.right = rotateLeft(x.right);
            }
            return rotateLeft(x);
        }
        return x;
    }

    @Override
    public Value get(@NotNull Key key) {
        return get(root, key);
    }

    private Value get(Node x, Key key) {
        if (x == null) {
            return null;
        }
        if (x.key.compareTo(key) > 0) {
            return get(x.left, key);
        }
        if (x.key.compareTo(key) < 0) {
            return get(x.right, key);
        }
        return x.value;
    }

    private Node put(Node x, Key key, Value value) {
        if (x == null) {
            size++;
            return new Node(key, value, 1);
        }
        if (x.key.compareTo(key) > 0) {
            x.left = put(x.left, key, value);
        } else if (x.key.compareTo(key) < 0) {
            x.right = put(x.right, key, value);
        } else {
            x.value = value;
        }
        fixHeight(x);
        x = balance(x);
        return x;
    }

    @Override
    public void put(@NotNull Key key, @NotNull Value value) {
        this.root = put(root, key, value);
    }

    @Override
    public Value remove(@NotNull Key key) {
        Value result = get(key);
        root = delete(root, key);
        return result;
    }

    private Node delete(Node x,@NotNull Key key) {
        if (x == null) {
            return null;
        }
        int cmp = key.compareTo(x.key);
        if (cmp < 0) {
            x.left = delete(x.left, key);
        }
        if (cmp > 0) {
            x.right = delete(x.right, key);
        }
        if (cmp == 0) {
            size--;
            x = innerDelete(x);
        }
        fixHeight(x);
        x = balance(x);
        return x;
    }

    private Node innerDelete(Node x) {
        if (x.right == null) {
            return x.left;
        }
        if (x.left == null) {
            return x.right;
        }
        Node tempNode = x;
        x = min(tempNode.right);
        x.right = deleteMin(tempNode.right);
        x.left = tempNode.left;
        return x;
    }

    private Node deleteMin(Node x) {
        if (x.left == null) {
            return x.right;
        }
        x.left = deleteMin(x.left);
        return x;
    }

    @Override
    public Key min() {
        if (isEmpty()) {
            return null;
        }
        return min(root).key;
    }

    @Override
    public Value minValue() {
        if (isEmpty()) {
            return null;
        }
        return min(root).value;
    }

    private Node min(Node x) {
        if (x.left == null) {
            return x;
        }
        return min(x.left);
    }

    @Override
    public Key max() {
        if (isEmpty()) {
            return null;
        }
        return max(root).key;
    }

    @Override
    public Value maxValue() {
        if (isEmpty()) {
            return null;
        }
        return max(root).value;
    }

    private Node max(Node x) {
        if (x.right == null) {
            return x;
        }
        return max(x.right);
    }

    @Override
    public Key floor(@NotNull Key key) {
        return floor(root, key) == null ? null : floor(root, key).key;
    }

    private Node floor(Node x, Key key) {
        if (x == null) {
            return null;
        }

        int cmp = key.compareTo(x.key);
        if (cmp == 0) {
            return x;
        }
        if (cmp < 0) {
            return floor(x.left, key);
        }

        Node nextNode = floor(x.right, key);
        return nextNode == null ? x : nextNode;
    }

    @Override
    public Key ceil(@NotNull Key key) {
        return ceil(root, key) == null ? null : ceil(root, key).key;
    }

    private Node ceil(Node x, Key key) {
        if (x == null) {
            return null;
        }

        int cmp = key.compareTo(x.key);
        if (cmp == 0) {
            return x;
        }
        if (cmp > 0) {
            return ceil(x.right, key);
        }

        Node nextNode = ceil(x.left, key);
        return nextNode == null ? x : nextNode;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public int height() {
        return height(root);
    }

    private int height(Node x) {
        return x == null ? 0 : x.height;
    }

    private void fixHeight(Node x) {
        if (x != null) {
            x.height = 1 + max(height(x.left), height(x.right));
        }
    }
}
