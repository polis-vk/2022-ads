package company.vk.polis.ads.bst;

import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public class AvlBinarySearchTree<Key extends Comparable<Key>, Value> implements BinarySearchTree<Key, Value> {
    private Node root = null;
    private int size = 0;

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

    private Value get(Node node, Key key) {
        if (node == null) {
            return null;
        }
        if (key.compareTo(node.key) < 0) {
            return get(node.left, key);
        }
        if (key.compareTo(node.key) > 0) {
            return get(node.right, key);
        }
        return node.value;
    }

    @Override
    public void put(@NotNull Key key, @NotNull Value value) {
        root = put(root, key, value);
    }

    private Node put(Node node, Key key, Value value) {
        if (node == null) {
            size++;
            return new Node(key, value, 1);
        }
        if (key.compareTo(node.key) < 0) {
            node.left = put(node.left, key, value);
        } else if (key.compareTo(node.key) > 0) {
            node.right = put(node.right, key, value);
        } else {
            node.value = value;
        }
        fixHeight(node);
        node = balance(node);
        return node;
    }

    @Override
    public Value remove(@NotNull Key key) {
        Value result = get(root, key);
        if (result == null) {
            return null;
        }
        root = remove(root, key);
        return result;
    }

    private Node remove(Node node, Key key) {
        if (node == null) {
            return null;
        }
        int temp = key.compareTo(node.key);
        if (temp < 0) {
            node.left = remove(node.left, key);
        }
        if (temp > 0) {
            node.right = remove(node.right, key);
        }
        if (key == node.key) {
            size--;
            node = innerDelete(node);
        }
        if (Objects.nonNull(node)) {
            fixHeight(node);
            node = balance(node);
        }
        return node;
    }

    @Override
    public Key min() {
        return root == null ? null : getMin(root).key;
    }

    @Override
    public Value minValue() {
        return root == null ? null : getMin(root).value;
    }

    @Override
    public Key max() {
        return root == null ? null : getMax(root).key;
    }

    @Override
    public Value maxValue() {
        return root == null ? null : getMax(root).value;
    }

    @Override
    public Key floor(@NotNull Key key) {
        return floor(root, key);
    }

    private Key floor(Node root, Key key) {
        if (root == null) {
            return null;
        }
        Node temp = root;
        while (temp != null) {
            if (key.compareTo(temp.key) < 0) {
                temp = temp.left;
            } else if (key.compareTo(temp.key) > 0) {
                if (temp.right == null || key.compareTo(temp.right.key) < 0) {
                    break;
                }
                temp = temp.right;
            } else {
                return temp.key;
            }
        }
        return (temp == null) ? null : temp.key;
    }

    @Override
    public Key ceil(@NotNull Key key) {
        return ceil(root, key);
    }

    private Key ceil(Node node, Key key) {
        if (node == null) {
            return null;
        }
        int temp = key.compareTo(node.key);
        if (temp < 0) {
            return (ceil(node.left, key) != null && ceil(node.left, key).compareTo(key) >= 0) ?
                    ceil(node.left, key) : node.key;
        } else if (temp > 0) {
            return ceil(node.right, key);
        } else {
            return node.key;
        }
    }


    @Override
    public int size() {
        return size;
    }

    @Override
    public int height() {
        return height(root);
    }

    private int height(Node node) {
        return node == null ? 0 : node.height;
    }

    private void fixHeight(Node node) {
        node.height = 1 + Math.max(height(node.left), height(node.right));
    }


    private Node balance(Node node) {
        if (factor(node) == 2) {
            if (factor(node.left) < 0)
                node.left = rotateLeft(node.left);
            return rotateRight(node);
        }
        if (factor(node) == -2) {
            if (factor(node.right) > 0)
                node.right = rotateRight(node.right);
            return rotateLeft(node);
        }
        return node;
    }

    private int factor(Node node) {
        return node == null ? 0 : height(node.left) - height(node.right);
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

    private Node innerDelete(Node node) {
        if (node.right == null) {
            return node.left;
        }
        if (node.left == null) {
            return node.right;
        }
        Node temp = node;
        node = getMin(temp.right);
        node.right = removeMin(temp.right);
        node.left = temp.left;
        return node;
    }

    private Node getMin(Node node) {
        while (node.left != null) {
            node = node.left;
        }
        return node;
    }

    private Node getMax(Node node) {
        while (node.right != null) {
            node = node.right;
        }
        return node;
    }

    private Node removeMin(Node node) {
        if (node.left == null) {
            return node.right;
        }
        node.left = removeMin(node.left);
        return node;
    }

}
