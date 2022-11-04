package company.vk.polis.ads.bst;

import org.jetbrains.annotations.NotNull;

/**
 * AVL implementation of binary search tree.
 */
public class AvlBinarySearchTree<Key extends Comparable<Key>, Value> implements BinarySearchTree<Key, Value> {

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
            this.left = null;
            this.right = null;
        }
    }

    public AvlBinarySearchTree() {
        root = null;
        size = 0;
    }

    @Override
    public Value get(@NotNull Key key) {
        Node tmp = root;
        while (tmp != null) {
            if (key.compareTo(tmp.key) < 0) {
                tmp = tmp.left;
            } else if (key.compareTo(tmp.key) > 0) {
                tmp = tmp.right;
            } else {
                return tmp.value;
            }
        }
        return null;
    }

    @Override
    public void put(@NotNull Key key, @NotNull Value value) {
        root = putNode(root, key, value);
    }

    @Override
    public Value remove(@NotNull Key key) {
        Value tmp = get(key);
        root = removeNode(root, key);
        return (tmp == null) ? null : tmp;
    }

    @Override
    public Key min() {
        Node min = getMinNode(root);
        return (min == null) ? null : min.key;
    }

    @Override
    public Value minValue() {
        Node min = getMinNode(root);
        return (min == null) ? null : min.value;
    }

    @Override
    public Key max() {
        Node max = getMaxNode(root);
        return (max == null) ? null : max.key;
    }

    @Override
    public Value maxValue() {
        Node max = getMaxNode(root);
        return (max == null) ? null : max.value;
    }

    @Override
    public Key floor(@NotNull Key key) {
        Node tmp = root;
        Node floor = null;
        while (tmp != null) {
            if (key.compareTo(tmp.key) > 0) {
                floor = tmp;
                tmp = tmp.right;
            } else if (key.compareTo(tmp.key) < 0) {
                tmp = tmp.left;
            } else {
                return tmp.key;
            }
        }
        return (floor == null) ? null : floor.key;
    }

    @Override
    public Key ceil(@NotNull Key key) {
        Node tmp = root;
        Node ceil = null;
        while (tmp != null) {
            if (key.compareTo(tmp.key) > 0) {
                tmp = tmp.right;
            } else if (key.compareTo(tmp.key) < 0) {
                ceil = tmp;
                tmp = tmp.left;
            } else {
                return tmp.key;
            }
        }
        return (ceil == null) ? null : ceil.key;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public int height() {
        return getHeightNode(root);
    }

    private Node getMinNode(Node tmp) {
        if (tmp == null) {
            return null;
        }
        while (tmp.left != null) {
            tmp = tmp.left;
        }
        return tmp;
    }

    private Node getMaxNode(Node tmp) {
        if (tmp == null) {
            return null;
        }
        while (tmp.right != null) {
            tmp = tmp.right;
        }
        return tmp;
    }

    private int getHeightNode(Node n) {
        return n == null ? 0 : n.height;
    }

    private void fixHeight(Node n) {
        n.height = Math.max(getHeightNode(n.left), getHeightNode(n.right)) + 1;
    }

    private int factor(Node n) {
        return getHeightNode(n.left) - getHeightNode(n.right);
    }

    private Node rotateLeft(Node x) {
        Node y = x.right;
        x.right = y.left;
        y.left = x;
        fixHeight(x);
        fixHeight(y);
        return y;
    }

    private Node rotateRight(Node x) {
        Node y = x.left;
        x.left = y.right;
        y.right = x;
        fixHeight(x);
        fixHeight(y);
        return y;
    }

    private Node balance(Node x) {
        if (factor(x) == -2) {
            if (factor(x.right) > 0) {
                x.right = rotateRight(x.right);
            }
            return rotateLeft(x);
        }
        if (factor(x) == 2) {
            if (factor(x.left) < 0) {
                x.left = rotateLeft(x.left);
            }
            return rotateRight(x);
        }
        return x;
    }

    private Node putNode(Node x, @NotNull Key key, @NotNull Value value) {
        if (x == null) {
            ++size;
            return new Node(key, value, 1);
        }
        if (key.compareTo(x.key) < 0) {
            x.left = putNode(x.left, key, value);
        } else if (key.compareTo(x.key) > 0) {
            x.right = putNode(x.right, key, value);
        } else {
            x.value = value;
        }

        fixHeight(x);
        x = balance(x);
        return x;
    }

    private Node removeNode(Node x, @NotNull Key key) {
        if (x == null) {
            return null;
        }

        if (key.compareTo(x.key) < 0) {
            x.left = removeNode(x.left, key);
        } else if (key.compareTo(x.key) > 0) {
            x.right = removeNode(x.right, key);
        } else {
            size--;
            x = innerDelete(x);
        }

        if (x != null) {
            fixHeight(x);
            x = balance(x);
        }
        return x;
    }

    private Node innerDelete(Node x) {
        if (x.right == null) {
            return x.left;
        }
        if (x.left == null) {
            return x.right;
        }
        Node tmp = x;
        x = getMinNode(tmp.right);
        x.right = deleteMin(tmp.right);
        x.left = tmp.left;
        return x;
    }

    private Node deleteMin(Node x) {
        if (x.left == null) {
            return x.right;
        }
        x.left = deleteMin(x.left);
        fixHeight(x);
        balance(x);
        return x;
    }

    private Node root;
    private int size;
}
