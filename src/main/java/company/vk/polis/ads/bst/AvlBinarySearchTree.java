package company.vk.polis.ads.bst;

import org.jetbrains.annotations.NotNull;

/**
 * AVL implementation of binary search tree.
 */
public class AvlBinarySearchTree<Key extends Comparable<Key>, Value> implements BinarySearchTree<Key, Value> {

    private class Node {

        Node(Key key, Value value, int height) {
            this.key = key;
            this.value = value;
            this.height = height;
            this.left = null;
            this.right = null;
        }

        Key key;
        Value value;
        Node left;
        Node right;
        int height;
    }

    private Node root = null;
    private int size = 0;

    private Node get(Node x, Key key) {
        if (x == null) {
            return null;
        }

        if (key.compareTo(x.key) < 0) {
            return get(x.left, key);
        }
        if (key.compareTo(x.key) > 0) {
            return get(x.right, key);
        }
        return x;
    }

    @Override
    public Value get(@NotNull Key key) {
        Node node = get(root, key);
        if (node == null) {
            return null;
        }
        return node.value;
    }

    private int height(Node x) {
        return x == null ? 0 : x.height;
    }

    private void fixHeight(Node x) {
        x.height = 1 + Integer.max(height(x.left), height(x.right));
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

    @Override
    public void put(@NotNull Key key, @NotNull Value value) {
        root = put(root, key, value);
    }

    private Node deleteMin(Node x) {
        if (x.left == null) {
            return x.right;
        }
        x.left = deleteMin(x.left);
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
        Node t = x;
        x = min(t.right);
        x.right = deleteMin(t.right);
        x.left = t.left;
        fixHeight(x);
        x = balance(x);
        return x;
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

    @Override
    public Value remove(@NotNull Key key) {
        Node result = get(root, key);
        if (result == null) {
            return null;
        }
        root = delete(root, key);
        return result.value;
    }

    private Node min(Node x) {
        if (x == null) {
            return null;
        }

        Node tmp = x;
        while (tmp.left != null) {
            tmp = tmp.left;
        }
        return tmp;
    }

    @Override
    public Key min() {
        Node tmp = min(root);
        if(tmp == null){
            return null;
        }

        return tmp.key;
    }

    @Override
    public Value minValue() {
        Node tmp = min(root);
        if(tmp == null){
            return null;
        }

        return tmp.value;
    }

    private Node max(Node x) {
        if (x == null) {
            return null;
        }

        Node tmp = x;
        while (tmp.right != null) {
            tmp = tmp.right;
        }
        return tmp;
    }

    @Override
    public Key max() {
        Node tmp = max(root);
        if(tmp == null){
            return null;
        }

        return tmp.key;
    }

    @Override
    public Value maxValue() {
        Node tmp = max(root);
        if(tmp == null){
            return null;
        }

        return tmp.value;
    }

    private Node getFloor(Node x, Key key) {
        if (x == null) {
            return null;
        }
        if (x.key.compareTo(key) == 0) {
            return x;
        }
        if (x.key.compareTo(key) > 0) {
            return getFloor(x.left, key);
        }
        Node floor = getFloor(x.right, key);
        return (floor != null && floor.key.compareTo(key) <= 0) ? floor : x;
    }

    @Override
    public Key floor(@NotNull Key key) {
        Node floor = getFloor(root, key);
        if (floor == null) {
            return null;
        }
        return floor.key;
    }

    private Node getCeil(Node x, Key key) {
        if (x == null) {
            return null;
        }
        if (x.key.compareTo(key) == 0) {
            return x;
        }
        if (x.key.compareTo(key) < 0) {
            return getCeil(x.right, key);
        }
        Node ceil = getCeil(x.left, key);
        return (ceil != null && ceil.key.compareTo(key) >= 0) ? ceil : x;
    }

    @Override
    public Key ceil(@NotNull Key key) {
        Node ceil = getCeil(root, key);
        if (ceil == null) {
            return null;
        }
        return ceil.key;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public int height() {
        return height(root);
    }
}