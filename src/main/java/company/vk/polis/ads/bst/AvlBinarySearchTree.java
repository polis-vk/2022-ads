package company.vk.polis.ads.bst;

import org.jetbrains.annotations.NotNull;

/**
 * AVL implementation of binary search tree.
 */
public class AvlBinarySearchTree<Key extends Comparable<Key>, Value> implements BinarySearchTree<Key, Value> {
    private Node root;
    private Node lastRemoved;
    private int size;

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
        root = remove(root, key);
        if (lastRemoved != null) {
            Value value = lastRemoved.value;
            lastRemoved = null;
            return value;
        }
        return null;
    }

    @Override
    public Key min() {
        return root == null ? null : getMinNode(root).key;
    }

    @Override
    public Value minValue() {
        return root == null ? null : getMinNode(root).value;
    }

    @Override
    public Key max() {
        return root == null ? null : getMaxNode(root).key;
    }

    @Override
    public Value maxValue() {
        return root == null ? null : getMaxNode(root).value;
    }

    @Override
    public Key floor(@NotNull Key key) {
        Node result = getFloor(root, key);
        return result == null ? null : result.key;
    }

    @Override
    public Key ceil(@NotNull Key key) {
        Node result = getCeil(root, key);
        return result == null ? null : result.key;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public int height() {
        return getHeight(root);
    }

    private Value get(Node node, Key key) {
        if (node == null) {
            return null;
        }

        int cmpResult = key.compareTo(node.key);
        if (cmpResult < 0) {
            return get(node.left, key);
        }
        if (cmpResult > 0) {
            return get(node.right, key);
        }
        return node.value;
    }

    private Node put(Node node, Key key, Value value) {
        if (node == null) {
            size++;
            return new Node(key, value, 1);
        }

        int cmpResult = key.compareTo(node.key);
        if (cmpResult < 0) {
            node.left = put(node.left, key, value);
        } else if (cmpResult > 0) {
            node.right = put(node.right, key, value);
        } else {
            node.value = value;
        }
        fixHeight(node);
        node = balance(node);
        return node;
    }

    private Node remove(Node node, Key key) {
        if (node == null) {
            return null;
        }

        int cmpResult = key.compareTo(node.key);
        if (cmpResult < 0) {
            node.left = remove(node.left, key);
        } else if (cmpResult > 0) {
            node.right = remove(node.right, key);
        } else {
            lastRemoved = node;
            node = innerRemove(node);
            size--;
        }
        fixHeight(node);
        node = balance(node);
        return node;
    }

    private Node innerRemove(Node node) {
        if (node.left == null) {
            return node.right;
        }
        if (node.right == null) {
            return node.left;
        }

        Node temp = node;
        node = getMinNode(temp.right);
        node.right = removeMin(temp.right);
        node.left = temp.left;
        fixHeight(node);
        node = balance(node);
        return node;
    }

    private Node removeMin(Node node) {
        if (node.left == null) {
            return node.right;
        }

        node.left = removeMin(node.left);
        fixHeight(node);
        node = balance(node);
        return node;
    }

    private Node getMinNode(Node node) {
        if (node.left != null) {
            node = getMinNode(node.left);
        }

        return node;
    }

    private Node getMaxNode(Node node) {
        if (node.right != null) {
            node = getMaxNode(node.right);
        }

        return node;
    }

    private Node getFloor(Node node, Key key) {
        if (node == null) {
            return null;
        }

        int cmpResult = key.compareTo(node.key);
        if (cmpResult == 0) {
            return node;
        }
        if (cmpResult < 0) {
            return getFloor(node.left, key);
        }

        Node nextNode = getFloor(node.right, key);
        return nextNode == null ? node : nextNode;
    }

    private Node getCeil(Node node, Key key) {
        if (node == null) {
            return null;
        }

        int cmpResult = key.compareTo(node.key);
        if (cmpResult == 0) {
            return node;
        }
        if (cmpResult > 0) {
            return getCeil(node.right, key);
        }

        Node nextNode = getCeil(node.left, key);
        return nextNode == null ? node : nextNode;
    }

    private Node balance(Node node) {
        if (getFactor(node) == 2) {
            if (getFactor(node.left) < 0) {
                node.left = rotateLeft(node.left);
            }
            return rotateRight(node);
        }

        if (getFactor(node) == -2) {
            if (getFactor(node.right) > 0) {
                node.right = rotateRight(node.right);
            }
            return rotateLeft(node);
        }
        return node;
    }

    private Node rotateLeft(Node node) {
        Node rightNode = node.right;
        node.right = rightNode.left;
        rightNode.left = node;
        fixHeight(node);
        fixHeight(rightNode);
        return rightNode;
    }

    private Node rotateRight(Node node) {
        Node leftNode = node.left;
        node.left = leftNode.right;
        leftNode.right = node;
        fixHeight(node);
        fixHeight(leftNode);
        return leftNode;
    }

    private void fixHeight(Node node) {
        if (node == null) {
            return;
        }

        node.height = 1 + Math.max(getHeight(node.left), getHeight(node.right));
    }

    private int getFactor(Node node) {
        return node == null ? Integer.MAX_VALUE : getHeight(node.left) - getHeight(node.right);
    }

    private int getHeight(Node node) {
        return node == null ? 0 : node.height;
    }
}

