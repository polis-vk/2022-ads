package company.vk.polis.ads.bst;

import org.jetbrains.annotations.NotNull;

/**
 * AVL implementation of binary search tree.
 */
public class AvlBinarySearchTree<Key extends Comparable<Key>, Value> implements BinarySearchTree<Key, Value> {
    int size;
    Node root;
    Node lastDeleted;

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
        lastDeleted = null;
        root = remove(root, key);
        return (lastDeleted == null ? null : lastDeleted.value);
    }

    @Override
    public Key min() {
        Node min = min(root);
        return (min == null ? null : min.key);
    }

    @Override
    public Value minValue() {
        Node min = min(root);
        return (min == null ? null : min.value);
    }

    @Override
    public Key max() {
        Node max = max(root);
        return (max == null ? null : max.key);
    }

    @Override
    public Value maxValue() {
        Node max = max(root);
        return (max == null ? null : max.value);
    }

    @Override
    public Key floor(@NotNull Key key) {
        Node floor = floor(root, key);
        return (floor == null ? null : floor.key);
    }

    @Override
    public Key ceil(@NotNull Key key) {
        Node ceil = ceil(root, key);
        return (ceil == null ? null : ceil.key);
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public int height() {
        return height(root);
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
        return balance(x);
    }

    private void fixHeight(Node n) {
        n.height = 1 + Math.max(height(n.left), height(n.right));
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

    private int height(Node x) {
        return (x == null ? 0 : x.height);
    }

    private int factor(Node x) {
        if (x == null) {
            return 0;
        }
        return height(x.left) - height(x.right);
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

    private Value get(Node x, Key key) {
        if (x == null) {
            return null;
        }

        if (key.compareTo(x.key) < 0) {
            return get(x.left, key);
        } else if (key.compareTo(x.key) > 0) {
            return get(x.right, key);
        } else {
            return x.value;
        }
    }

    private Node remove(Node x, Key key) {
        if (x == null) {
            return null;
        }

        if (key.compareTo(x.key) < 0) {
            x.left = remove(x.left, key);
        } else if (key.compareTo(x.key) > 0) {
            x.right = remove(x.right, key);
        } else {
            x = innerDelete(x);
        }

        return balance(x);
    }

    private Node innerDelete(Node x) {
        size--;
        lastDeleted = x;
        if (x.right == null) {
            return x.left;
        }
        if (x.left == null) {
            return x.right;
        }
        Node t = x;
        x = min(t.right);
        x.right = deleteMin(t.right);
        x.left = t.left;
        return x;
    }

    private Node min(Node x) {
        if (x == null) {
            return null;
        }
        if (x.left == null) {
            return x;
        }
        return min(x.left);
    }

    private Node ceil(Node x, Key key) {
        if (x == null) {
            return null;
        }
        if (key.compareTo(x.key) < 0) {
            Node ceilLeft = ceil(x.left, key);
            return (ceilLeft == null ? x : ceilLeft);
        } else if (key.compareTo(x.key) > 0) {
            return ceil(x.right, key);
        }
        return x;
    }

    private Node floor(Node x, Key key) {
        if (x == null) {
            return null;
        }
        if (key.compareTo(x.key) < 0) {
            return floor(x.left, key);
        } else if (key.compareTo(x.key) > 0) {
            Node ceilRight = floor(x.right, key);
            return (ceilRight == null ? x : ceilRight);
        }
        return x;
    }

    private Node max(Node x) {
        if (x == null) {
            return null;
        }
        if (x.right == null) {
            return x;
        }
        return max(x.right);
    }


    private Node deleteMin(Node x) {
        if (x == null) {
            return null;
        }
        if (x.left == null) {
            return x.right;
        }
        x.left = deleteMin(x.left);
        return balance(x);
    }
}
