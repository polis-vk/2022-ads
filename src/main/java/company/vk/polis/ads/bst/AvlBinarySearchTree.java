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

        public Node(Key key, Value value, int height) {
            this.key = key;
            this.value = value;
            this.height = height;
        }
    }

    private Node root;
    private int size;

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
        Value valueToRemove = get(root, key);
        if (valueToRemove != null) {
            root = remove(root, key);
        }
        return valueToRemove;
    }

    @Override
    public Key min() {
        Node minNode = min(root);
        return minNode == null ? null : minNode.key;
    }

    @Override
    public Value minValue() {
        Node minNode = min(root);
        return minNode == null ? null : minNode.value;
    }

    @Override
    public Key max() {
        return root == null ? null : max(root).key;
    }

    @Override
    public Value maxValue() {
        return root == null ? null : max(root).value;
    }

    @Override
    public Key floor(@NotNull Key key) {
        Node neededNode = floor(root, key);
        return neededNode == null ? null : neededNode.key;
    }

    @Override
    public Key ceil(@NotNull Key key) {
        Node neededNode = ceil(root, key);
        return neededNode == null ? null : neededNode.key;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public int height() {
        return height(root);
    }

    private Node remove(Node node, @NotNull Key key) {
        if (node == null) {
            return null;
        }
        int difference = key.compareTo(node.key);
        if (difference < 0) {
            node.left = remove(node.left, key);
        } else if (difference > 0) {
            node.right = remove(node.right, key);
        } else {
            size--;
            node = innerDelete(node);
        }
        if (node != null) {
            fixHeight(node);
            node = balance(node);
        }
        return node;
    }

    private Node innerDelete(Node x) {
        if (x.right == null) {
            return x.left;
        }
        if (x.left == null) {
            return x.right;
        }
        Node tmp = x;
        x = min(tmp.right);
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

    private Node min(Node node) {
        if (node == null) {
            return null;
        }
        return node.left == null ? node : min(node.left);
    }

    private Node max(Node node) {
        if (node == null) {
            return null;
        }
        return node.right == null ? node : max(node.right);
    }

    private int height(Node node) {
        return node == null ? 0 : node.height;
    }

    private Value get(Node node, Key key) {
        if (node == null) {
            return null;
        }
        int difference = node.key.compareTo(key);
        if (difference < 0) {
            return get(node.right, key);
        }
        if (difference > 0) {
            return get(node.left, key);
        }
        return node.value;
    }

    private Node put(Node node, Key key, Value value) {
        if (node == null) {
            size++;
            return new Node(key, value, 1);
        }
        int difference = node.key.compareTo(key);
        if (difference < 0) {
            node.right = put(node.right, key, value);
        } else if (difference > 0) {
            node.left = put(node.left, key, value);
        } else {
            node.value = value;
        }
        fixHeight(node);
        node = balance(node);
        return node;
    }

    private void fixHeight(Node node) {
        node.height = 1 + Math.max(height(node.left), height(node.right));
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

    private int factor(Node node) {
        return height(node.left) - height(node.right);
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

    private Node floor(Node node, Key key) {
        if (node == null) {
            return null;
        }
        int difference = node.key.compareTo(key);
        if (difference == 0) {
            return node;
        }
        if (difference > 0) {
            return floor(node.left, key);
        }
        Node next = floor(node.right, key);
        return (next != null && next.key.compareTo(key) <= 0) ? next : node;
    }

    private Node ceil(Node node, Key key) {
        if (node == null) {
            return null;
        }
        int difference = node.key.compareTo(key);
        if (difference == 0) {
            return node;
        }
        if (difference < 0) {
            return ceil(node.right, key);
        }
        Node next = ceil(node.left, key);
        return (next != null && next.key.compareTo(key) >= 0) ? next : node;
    }
}
