package company.vk.polis.ads.bst;

import java.util.Objects;

import org.jetbrains.annotations.NotNull;

/**
 * AVL implementation of binary search tree.
 */
public class AvlBinarySearchTree<Key extends Comparable<Key>, Value> implements BinarySearchTree<Key, Value> {

    private Node root;
    private int size;

    private class Node {
        public Node(Key key, Value value, int height) {
            this.key = key;
            this.value = value;
            this.height = height;
        }

        Key key;
        Value value;
        Node left;
        Node right;
        int height;

        @Override
        public String toString() {
            return key.toString() + " : " + value.toString();
        }
    }

    @Override
    public Value get(@NotNull Key key) {
        return get(root, key);
    }

    private Value get(Node x, Key key) {
        if (x == null) return null;
        if (key.compareTo(x.key) < 0) return get(x.left, key);
        if (key.compareTo(x.key) > 0) return get(x.right, key);
        return x.value;
    }

    @Override
    public void put(@NotNull Key key, @NotNull Value value) {
        root = put(root, key, value);
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

    private Node balance(Node x) {
        if (factor(x) == 2) {
            if (factor(x.left) < 0)
                x.left = rotateLeft(x.left);
            return rotateRight(x);
        }
        if (factor(x) == -2) {
            if (factor(x.right) > 0)
                x.right = rotateRight(x.right);
            return rotateLeft(x);
        }
        return x;
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
        return x != null ? height(x.left) - height(x.right) : 0;
    }

    private int height(Node x) {
        return x == null ? 0 : x.height;
    }

    private void fixHeight(Node x) {
        x.height = 1 + Math.max(height(x.left), height(x.right));
    }

    @Override
    public Value remove(@NotNull Key key) {
        Value value = get(key);
        if (value == null) {
            return null;
        }
        root = remove(root, key);
        size--;
        return value;
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
        if (key == x.key) {
            x = innerDelete(x);
        }
        if (x != null) {
            fixHeight(x);
        }
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
        x = minNode(t.right);
        x.right = deleteMin(t.right);
        x.left = t.left;
        fixHeight(x);
        x = balance(x);
        return x;
    }

    private Node deleteMin(Node current) {
        if (current.left == null) {
            return current.right;
        }
        current.left = deleteMin(current.left);
        fixHeight(current);
        current = balance(current);
        return current;
    }

    @Override
    public Key min() {
        Node minNode = minNode(root);
        return minNode == null ? null : minNode.key;
    }

    private Node minNode(Node node) {
        if (node == null) {
            return null;
        }
        while (node.left != null) {
            node = node.left;
        }
        return node;
    }

    public void printTree() {
        System.out.println();
        printTree(root);
        System.out.println();
    }

    private void printTree(Node root) {
        if (root.left != null) printTree(root.left);
        System.out.println(root);
        if (root.right != null) printTree(root.right);
    }

    @Override
    public Value minValue() {
        Node minNode = minNode(root);
        return minNode == null ? null : minNode.value;
    }

    @Override
    public Key max() {
        Node maxNode = maxNode(root);
        return maxNode == null ? null : maxNode.key;
    }

    private Node maxNode(Node node) {
        if (node == null) {
            return null;
        }
        while (node.right != null) {
            node = node.right;
        }
        return node;
    }

    @Override
    public Value maxValue() {
        Node maxNode = maxNode(root);
        return maxNode == null ? null : maxNode.value;
    }

    @Override
    public Key floor(@NotNull Key key) {
        return searchFloor(key, root);
    }

    private Key searchFloor(Key key, Node current) {
        if (current == null) {
            return null;
        }
        if (current.key.compareTo(key) == 0) {
            return current.key;
        }
        if (current.key.compareTo(key) > 0) {
            return searchFloor(key, current.left);
        } else {
            Key floor = searchFloor(key, current.right);
            return (floor != null && floor.compareTo(key) <= 0) ? floor : current.key;
        }
    }

    @Override
    public Key ceil(@NotNull Key key) {
        return searchCeil(key, root);
    }

    private Key searchCeil(Key key, Node current) {
        if (current == null) {
            return null;
        }
        if (current.key.compareTo(key) == 0) {
            return current.key;
        }
        if (current.key.compareTo(key) < 0) {
            return searchCeil(key, current.right);
        }
        Key ceil = searchCeil(key, current.left);
        return (ceil != null && ceil.compareTo(key) >= 0) ? ceil : current.key;
    }

    private Key searchMax(Key key, Node current) {
        if (current == null) return null;
        if (current.key == key) return current.key;
        while (current.left != null || current.right != null) {
            if (current.key.compareTo(key) > 0) {
                current = current.left;
            }
            if (current.key.compareTo(key) < 0) {
                current = current.right;
            }
        }
        return current.key;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public int height() {
        return root == null ? 0 : root.height;
    }
}
