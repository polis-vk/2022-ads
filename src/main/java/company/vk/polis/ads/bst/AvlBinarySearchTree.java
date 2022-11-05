package company.vk.polis.ads.bst;

import org.jetbrains.annotations.NotNull;

/**
 * AVL implementation of binary search tree.
 */
public class AvlBinarySearchTree<Key extends Comparable<Key>, Value> implements BinarySearchTree<Key, Value> {

    Node root;
    private int nodeCount;

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
        Value res = get(root, key);
        if (res == null) {
            return null;
        }
        root = remove(root, key);
        return res;
    }

    @Override
    public Key min() {
        Node res = minNode(root);
        return res == null ? null : res.key;
    }

    @Override
    public Value minValue() {
        Node res = minNode(root);
        return res == null ? null : res.value;
    }

    @Override
    public Key max() {
        Node res = maxNode(root);
        return res == null ? null : res.key;
    }

    @Override
    public Value maxValue() {
        Node res = maxNode(root);
        return res == null ? null : res.value;
    }

    @Override
    public Key floor(@NotNull Key key) {
        return floor(root, key);
    }

    @Override
    public Key ceil(@NotNull Key key) {
        return ceil(root, key);
    }

    @Override
    public int size() {
        return nodeCount;
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
        } else if (key.compareTo(x.key) > 0) {
            return get(x.right, key);
        } else {
            return x.value;
        }
    }

    private Node put(Node x, Key key, Value value) {
        if (x == null) {
            nodeCount++;
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
        } else if (key.compareTo(x.key) > 0) {
            x.right = remove(x.right, key);
        } else {
            this.nodeCount--;
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
        Node tmp = x;
        x = minNode(tmp.right);
        x.right = deleteMin(tmp.right);
        x.left = tmp.left;
        return x;
    }

    private Node minNode(Node x) {
        if (x == null) {
            return null;
        }
        while (x.left != null) {
            x = x.left;
        }
        return x;
    }

    private Node maxNode(Node x) {
        if (x == null) {
            return null;
        }
        while (x.right != null) {
            x = x.right;
        }
        return x;
    }

    private Node deleteMin(Node x) {
        if (x.left == null) {
            return x.right;
        }
        x.left = deleteMin(x.left);
        return x;
    }

    private Key floor(Node x, Key key) {
        if (x == null) {
            return null;
        }
        if (key.compareTo(x.key) < 0) {
            return floor(x.left, key);
        } else if (key.compareTo(x.key) > 0) {
            Key res = floor(x.right, key);
            return (res != null && res.compareTo(key) <= 0) ? res : x.key;
        } else {
            return x.key;
        }
    }

    private Key ceil(Node x, Key key) {
        if (x == null) {
            return null;
        }
        if (key.compareTo(x.key) < 0) {
            Key res = ceil(x.left, key);
            return (res != null && res.compareTo(key) >= 0) ? res : x.key;
        } else if (key.compareTo(x.key) > 0) {
            return ceil(x.right, key);
        } else {
            return x.key;
        }
    }

    private int height(Node x) {
        return x == null ? 0 : x.height;
    }

    private void fixHeight(Node x) {
        if (x != null) {
            x.height = 1 + Math.max(height(x.left), height(x.right));
        }
    }

    private int factor(Node x) {
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

    private Node balance(Node x) {
        if (x == null) {
            return null;
        }
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
}
