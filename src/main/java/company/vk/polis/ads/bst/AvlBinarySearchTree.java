package company.vk.polis.ads.bst;

import org.jetbrains.annotations.NotNull;

/**
 * AVL implementation of binary search tree.
 */
public class AvlBinarySearchTree<Key extends Comparable<Key>, Value> implements BinarySearchTree<Key, Value> {
    private Node root;
    private int size = 0;

    private static final int AVL_FACTOR = 2;

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
        root = put(root, value, key);
    }

    @Override
    public Value remove(@NotNull Key key) {
        Value element = get(root, key);
        if (element == null) {
            return null;
        }
        root = delete(root, key);
        return element;
    }

    @Override
    public Key min() {
        Node minNode = min(root);
        if (minNode == null) {
            return null;
        }
        return minNode.key;
    }

    @Override
    public Value minValue() {
        Node minNode = min(root);
        if (minNode == null) {
            return null;
        }
        return minNode.value;
    }

    @Override
    public Key max() {
        Node maxNode = max(root);
        if (maxNode == null) {
            return null;
        }
        return maxNode.key;
    }

    @Override
    public Value maxValue() {
        Node maxNode = max(root);
        if (maxNode == null) {
            return null;
        }
        return maxNode.value;
    }

    @Override
    public Key floor(@NotNull Key key) {
        return floor(root, key) == null ? null : floor(root, key).key;
    }

    @Override
    public Key ceil(@NotNull Key key) {
        return ceil(root, key) == null ? null : ceil(root, key).key;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public int height() {
        return height(root);
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

    private int height(Node x) {
        return x == null ? 0 : x.height;
    }

    private Node put(Node x, Value value, Key key) {
        if (x == null) {
            size++;
            return new Node(key, value, 1);
        }
        if (key.compareTo(x.key) < 0) {
            x.left = put(x.left, value, key);
        } else if (key.compareTo(x.key) > 0) {
            x.right = put(x.right, value, key);
        } else {
            x.value = value;
        }
        fixHeight(x);
        x = balance(x);
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

    private Node delete(Node x, Key key) {
        if (x == null) {
            return null;
        }
        if (key.compareTo(x.key) < 0) {
            x.left = delete(x.left, key);
        }
        if (key.compareTo(x.key) > 0) {
            x.right = delete(x.right, key);
        }
        if (key.compareTo(x.key) == 0) {
            size--;
            x = innerDelete(x);
        }
        fixHeight(x);
        x = balance(x);
        return x;
    }

    private Node deleteMin(Node x) {
        if (x.left == null) {
            return x.right;
        }
        x.left = deleteMin(x.left);
        return x;
    }

    private Node innerDelete(Node x) {
        if (x.right == null) {
            return x.left;
        }
        if (x.left == null) {
            return x.right;
        }
        Node temp = x;
        x = min(temp.right);
        x.right = deleteMin(temp.right);
        x.left = temp.left;
        fixHeight(x);
        x = balance(x);
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
        if (x.key == key) {
            return x;
        }
        if (x.key.compareTo(key) < 0) {
            return ceil(x.right, key);
        }
        Node result = ceil(x.left, key);
        return (result == null || result.key.compareTo(key) < 0) ? x : result;
    }

    private Node floor(Node x, Key key) {
        if (x == null) {
            return null;
        }
        if (x.key == key) {
            return x;
        }
        if (x.key.compareTo(key) > 0) {
            return floor(x.left, key);
        }
        Node result = floor(x.right, key);
        return (result == null || result.key.compareTo(key) > 0) ? x : result;
    }

    private void fixHeight(Node x) {
        if (x == null) {
            return;
        }
        x.height = 1 + Math.max(height(x.left), height(x.right));
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
        return height(x.left) - height(x.right);
    }

    private Node balance(Node x) {
        if (x == null) {
            return null;
        }
        if (factor(x) == AVL_FACTOR) {
            if (factor(x.left) < 0) {
                x.left = rotateLeft(x.left);
                return rotateRight(x);
            }
        }
        if (factor(x) == -AVL_FACTOR) {
            if (factor(x.right) > 0) {
                x.right = rotateRight(x.right);
            }
            return rotateLeft(x);
        }
        return x;
    }
}
