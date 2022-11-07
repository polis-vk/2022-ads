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

        public Node(Key key, Value value, int height) {
            this.key = key;
            this.value = value;
            this.height = height;
        }
    }

    @Override
    public Value get(@NotNull Key key) {
        return get(root, key);
    }

    @Override
    public void put(@NotNull Key key, @NotNull Value value) {
        root = put(root, key, value);
    }

    @Override
    public Value remove(@NotNull Key key) {
        Value result = get(key);
        root = remove(root, key);
        return result;
    }

    @Override
    public Key min() {
        return isEmpty() ? null : min(root).key;
    }

    @Override
    public Value minValue() {
        return isEmpty() ? null : min(root).value;
    }

    @Override
    public Key max() {
        return isEmpty() ? null : max(root).key;
    }

    @Override
    public Value maxValue() {
        return isEmpty() ? null : max(root).value;
    }

    @Override
    public Key floor(@NotNull Key key) {
        Node x = floor(root, key);
        return x == null ? null : x.key;
    }

    @Override
    public Key ceil(@NotNull Key key) {
        Node x = ceil(root, key);
        return x == null ? null : x.key;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public int height() {
        return height(root);
    }

    public boolean isEmpty() {
        return size == 0;
    }

    private Node min(Node x) {
        if (x.left != null) {
            return min(x.left);
        }
        return x;
    }

    private Node max(Node x) {
        if (x.right != null) {
            return max(x.right);
        }
        return x;
    }

    private Value get(Node x, Key key) {
        if (x == null) {
            return null;
        }
        if (key.compareTo(x.key) < 0) {
            return get(x.left, key);
        }
        if (key.compareTo(x.key) > 0) {
            return get(x.right, key);
        }
        return x.value;
    }

    private Node put(Node x, Key key, Value value) {
        if (x == null) {
            size++;
            return new Node(key, value, 1);
        }
        if (key.compareTo(x.key) < 0) {
            x.left = put(x.left, key, value);
        } else if (key.compareTo(x.key) > 0) {
            x.right = put(x.right, key, value);
        } else {
            x.value = value;
        }
        fixHeight(x);
        x = balance(x);
        return x;
    }

    private Node remove(Node x, Key key) {
        if (x == null) {
            return null;
        }
        if (key.compareTo(x.key) < 0) {
            x.left = remove(x.left, key);
        }
        if (key.compareTo(x.key) > 0) {
            x.right = remove(x.right, key);
        }
        if (key.compareTo(x.key) == 0) {
            x = innerRemove(x);
            size--;
        }
        fixHeight(x);
        x = balance(x);
        return x;
    }

    private Node innerRemove(Node x) {
        if (x.right == null) {
            return x.left;
        }
        if (x.left == null) {
            return x.right;
        }
        Node t = x;
        x = min(t.right);
        x.right = removeMin(t.right);
        x.left = t.left;
        fixHeight(x);
        x = balance(x);
        return x;
    }

    private Node removeMin(Node x) {
        if (x.left == null) {
            return x.right;
        }
        x.left = removeMin(x.left);
        return x;
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
                x.right = rotateRight(x.right);
            }
            return rotateLeft(x);
        }
        return x;
    }

    private int factor(Node x) {
        if (x == null) {
            return 0;
        }
        return height(x.left) - height(x.right);
    }

    private Node rotateLeft(Node x) {
        Node y = x.right;
        x.right = y.left;
        y.left = x;
        fixHeight(x);
        fixHeight(y);
        return y;
    }

    private Node rotateRight(Node y) {
        Node x = y.left;
        y.left = x.right;
        x.right = y;
        fixHeight(y);
        fixHeight(x);
        return x;
    }

    private void fixHeight(Node x) {
        if (x == null) {
            return;
        }
        x.height = 1 + Math.max(height(x.left), height(x.right));
    }

    private int height(Node x) {
        return x == null ? 0 : root.height;
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
        Node t = floor(x.right, key);
        return (t == null || t.key.compareTo(key) > 0) ? x : t;
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
        Node t = ceil(x.left, key);
        return (t == null || t.key.compareTo(key) < 0) ? x : t;
    }
}
