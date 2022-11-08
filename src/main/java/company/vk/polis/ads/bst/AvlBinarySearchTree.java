package company.vk.polis.ads.bst;

import org.jetbrains.annotations.NotNull;

/**
 * AVL implementation of binary search tree.
 */
public class AvlBinarySearchTree<Key extends Comparable<Key>, Value> implements BinarySearchTree<Key, Value> {
    private Node root = null;
    private int size = 0;

    private class Node {
        Key key;
        Value value;
        Node left;
        Node right;
        int height;
    }

    @Override
    public Value get(@NotNull Key key) {
        return get(root, key);
    }

    @Override
    public void put(@NotNull Key key, @NotNull Value value) {
        Value oldValue = get(key);
        if (oldValue == null) {
            size++;
        }
        root = put(root, key, value);
    }

    @Override
    public Value remove(@NotNull Key key) {
        Value oldValue = get(key);
        if (oldValue == null) {
            return null;
        }
        root = delete(root, key);
        size--;
        return oldValue;
    }

    @Override
    public Key min() {
        Node minNode = minNode(root);
        if (minNode == null) {
            return null;
        }
        return minNode.key;
    }

    @Override
    public Value minValue() {
        Node minNode = minNode(root);
        if (minNode == null) {
            return null;
        }
        return minNode.value;
    }

    @Override
    public Key max() {
        Node maxNode = maxNode(root);
        if (maxNode == null) {
            return null;
        }
        return maxNode.key;
    }

    @Override
    public Value maxValue() {
        Node maxNode = maxNode(root);
        if (maxNode == null) {
            return null;
        }
        return maxNode.value;
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
        return size;
    }

    @Override
    public int height() {
        return height(root);
    }

    private Key floor(Node x, Key key) {
        if (x == null) {
            return null;
        }
        int comparison = x.key.compareTo(key);
        if (comparison <= 0) {
            Key rightFloor = floor(x.right, key);
            if (rightFloor != null) {
                return rightFloor;
            }
            return x.key;
        }
        return floor(x.left, key);
    }

    private Key ceil(Node x, Key key) {
        if (x == null) {
            return null;
        }
        int comparison = x.key.compareTo(key);
        if (comparison >= 0) {
            Key leftCeil = ceil(x.left, key);
            if (leftCeil != null) {
                return leftCeil;
            }
            return x.key;
        }
        return ceil(x.right, key);
    }

    private int factor(Node x) {
        if (x == null) {
            return 0;
        }
        return height(x.left) - height(x.right);
    }

    private void fixHeight(Node x) {
        if (x == null) {
            return;
        }
        x.height = 1 + Math.max(height(x.left), height(x.right));
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
        return x == null ? 0 : x.height;
    }

    private Value get(Node x, Key key) {
        if (x == null) {
            return null;
        }
        int comparison = x.key.compareTo(key);
        if (comparison > 0) {
            return get(x.left, key);
        }
        if (comparison < 0) {
            return get(x.right, key);
        }
        return x.value;
    }

    private Node minNode(Node x) {
        if (x == null) {
            return null;
        }
        if (x.left == null) {
            return x;
        }
        return minNode(x.left);
    }

    private Node maxNode(Node x) {
        if (x == null) {
            return null;
        }
        if (x.right == null) {
            return x;
        }
        return maxNode(x.right);
    }

    private Node put(Node x, Key key, Value value) {
        if (x == null) {
            Node newNode = new Node();
            newNode.key = key;
            newNode.value = value;
            fixHeight(newNode);
            return newNode;
        }
        int comparison = x.key.compareTo(key);
        if (comparison > 0) {
            x.left = put(x.left, key, value);
        } else if (comparison < 0) {
            x.right = put(x.right, key, value);
        } else {
            x.value = value;
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
        fixHeight(x);
        return x;
    }

    private Node innerDelete(Node x) {
        if (x.left == null) {
            return x.right;
        }
        if (x.right == null) {
            return x.left;
        }
        Node t = x;
        x = minNode(t.right);
        x.right = deleteMin(t.right);
        x.left = t.left;
        fixHeight(x);
        return x;
    }

    private Node delete(Node x, Key key) {
        if (x == null) {
            return null;
        }
        int comparison = x.key.compareTo(key);
        if (comparison > 0) {
            x.left = delete(x.left, key);
        } else if (comparison < 0) {
            x.right = delete(x.right, key);
        } else {
            x = innerDelete(x);
        }
        fixHeight(x);
        x = balance(x);
        return x;
    }
}
