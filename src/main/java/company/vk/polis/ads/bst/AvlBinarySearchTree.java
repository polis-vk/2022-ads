package company.vk.polis.ads.bst;

import org.jetbrains.annotations.NotNull;

/**
 * AVL implementation of binary search tree.
 */
public class AvlBinarySearchTree<Key extends Comparable<Key>, Value> implements BinarySearchTree<Key, Value> {

    private int size;
    private Node root;

    AvlBinarySearchTree() {
        this.size = 0;
    }

    private class Node {
        Key key;
        Value value;
        Node left;
        Node right;
        int height;

        Node(Key k, Value value, int height) {
            this.key = k;
            this.value = value;
            this.height = height;
        }
    }

    @Override
    public Value get(@NotNull Key key) {
        Node current = root;
        while (current != null) {
            int cmp = key.compareTo(current.key);
            if (cmp == 0) {
                break;
            }
            current = (cmp > 0)? current.right : current.left;
        }
        if (current == null) {
            return null;
        }
        return current.value;
    }

    @Override
    public void put(@NotNull Key key, @NotNull Value value) {
        root = put(root, key, value);
    }

    private Node put(Node n, Key key, Value value) {
        if (n == null) {
            size++;
            return new Node(key, value, 1);
        }
        int cmp = key.compareTo(n.key);
        if (cmp < 0) {
            n.left = put(n.left, key, value);
        } else if (cmp > 0) {
            n.right = put(n.right, key, value);
        } else {
            n.value = value;
        }
        fixHeight(n);
        n = balance(n);
        return n;
    }

    @Override
    public Value remove(@NotNull Key key) {
        Value result = get(key);
        if (result == null) {
            return null;
        }
        root = remove(root, key);
        return result;
    }

    private Node remove(Node n, Key key) {
        if (n == null) {
            return null;
        }
        int cmp = key.compareTo(n.key);
        if (cmp < 0) {
            n.left = remove(n.left, key);
        } else if (cmp > 0) {
            n.right = remove(n.right, key);
        } else {
            size--;
            if (n.left == null || n.right == null) {
                n = (n.left == null) ? n.right : n.left;
            } else {
                Node curr = n;
                n = min(n.right);
                n.right = remove(n.right, n.key);
                n.left = curr.left;
            }
        }
        if (n != null) {
            fixHeight(n);
            n = balance(n);
        }
        return n;
    }

    @Override
    public Key min() {
        Node minNode = min(root);
        if (minNode == null) {
            return null;
        }
        return min(root).key;
    }

    @Override
    public Value minValue() {
        Node minNode = min(root);
        if (minNode == null) {
            return null;
        }
        return min(root).value;
    }

    private Node min(Node n) {
        if (n == null) {
            return null;
        }
        if (n.left == null) {
            return n;
        }
        return min(n.left);
    }

    @Override
    public Key max() {
        Node maxNode = max(root);
        if (maxNode == null) {
            return null;
        }
        return max(root).key;
    }

    @Override
    public Value maxValue() {
        Node maxNode = max(root);
        if (maxNode == null) {
            return null;
        }
        return max(root).value;
    }

    private Node max(Node n) {
        if (n == null) {
            return null;
        }
        if (n.right == null) {
            return n;
        }
        return max(n.right);
    }

    @Override
    public Key floor(@NotNull Key key) {
        Node result = floor(root, key);
        if (result == null) {
            return null;
        }
        return result.key;
    }

    private Node floor(Node n, Key key) {
        if (n == null) {
            return null;
        }
        int cmp = key.compareTo(n.key);
        if (cmp == 0) {
            return n;
        }
        if (cmp < 0) {
            return floor(n.left, key);
        }
        Node x = floor(n.right, key);
        return (x == null || x.key.compareTo(key) > 0) ? n : x;
    }

    @Override
    public Key ceil(@NotNull Key key) {
        Node result = ceil(root, key);
        if (result == null) {
            return null;
        }
        return result.key;
    }

    private Node ceil(Node n, Key key) {
        if (n == null) {
            return null;
        }
        int cmp = key.compareTo(n.key);
        if (cmp == 0) {
            return n;
        }
        if (cmp > 0) {
            return ceil(n.right, key);
        }
        Node x = ceil(n.left, key);
        return (x == null || x.key.compareTo(key) < 0) ? n : x;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public int height() {
        return height(root);
    }

    private int height(Node n) {
        return (n == null)? 0 : n.height;
    }

    private void fixHeight(Node n) {
        n.height = 1 + Math.max(height(n.left), height(n.right));
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

    private int factor(Node n) {
        return (height(n.left) - height(n.right));
    }

    private Node balance(Node n) {
        if (factor(n) == 2) {
            if (factor(n.left) < 0) {
                n.left = rotateLeft(n.left);
            }
            return rotateRight(n);
        }
        if (factor(n) == -2) {
            if (factor(n.right) > 0) {
                n.right = rotateLeft(n.right);
            }
            return rotateLeft(n);
        }
        return n;
    }
}
