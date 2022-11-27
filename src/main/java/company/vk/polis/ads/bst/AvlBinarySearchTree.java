package company.vk.polis.ads.bst;

import org.jetbrains.annotations.NotNull;

/**
 * AVL implementation of binary search tree.
 */
public class AvlBinarySearchTree<Key extends Comparable<Key>, Value> implements BinarySearchTree<Key, Value> {
    private Node root = null;
    private Value lastRemValue = null;
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
            node = new Node(key, value, 1);
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
        root = removeNode(root, key);
        Value value = lastRemValue;
        lastRemValue = null;
        return value;
    }

    private Node removeNode(Node node, Key key) {
        if (node == null) {
            return null;
        }
        int cmp = key.compareTo(node.key);
        if (cmp < 0) {
            node.left = removeNode(node.left, key);
        } else if (cmp > 0) {
            node.right = removeNode(node.right, key);
        } else {
            lastRemValue = node.value;
            node = innerDelete(node);
            size--;
        }
        fixHeight(node);
        node = balance(node);
        return node;
    }

    @Override
    public Key min() {
        return (root == null) ? null : minNode(root).key;
    }

    @Override
    public Value minValue() {
        return (root == null) ? null : minNode(root).value;
    }

    private Node minNode(Node node) {
        if (node.left == null) {
            return node;
        }
        return minNode(node.left);
    }

    @Override
    public Key max() {
        return (root == null) ? null : maxNode(root).key;
    }

    @Override
    public Value maxValue() {
        return (root == null) ? null : maxNode(root).value;
    }

    private Node maxNode(Node node) {
        if (node.right == null) {
            return node;
        }
        return maxNode(node.right);
    }

    @Override
    public Key floor(@NotNull Key key) {
        Node floorNode = floorNode(root, key);
        return floorNode == null ? null : floorNode.key;
    }

    private Node floorNode(Node currNode, Key key) {
        if (currNode == null) {
            return null;
        }
        int cmp = key.compareTo(currNode.key);
        if (cmp < 0) {
            return floorNode(currNode.left, key);
        }
        if (cmp > 0) {
            Node rightNode = floorNode(currNode.right, key);
            return rightNode == null ? currNode : rightNode;
        }
        return currNode;
    }

    @Override
    public Key ceil(@NotNull Key key) {
        Node ceilNode = ceilNode(root, key);
        return ceilNode == null ? null : ceilNode.key;
    }

    private Node ceilNode(Node currNode, Key key) {
        if (currNode == null) {
            return null;
        }
        int cmp = key.compareTo(currNode.key);
        if (cmp > 0) {
            return ceilNode(currNode.right, key);
        }
        if (cmp < 0) {
            Node leftNode = ceilNode(currNode.left, key);
            return leftNode == null ? currNode : leftNode;
        }
        return currNode;
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
        if (node != null) {
            node.height = 1 + Math.max(height(node.left), height(node.right));
        }
    }

    private Node rotateRight(Node parentNode) {
        Node currentNode = parentNode.left;
        parentNode.left = currentNode.right;
        currentNode.right = parentNode;
        fixHeight(parentNode);
        fixHeight(currentNode);
        return currentNode;
    }

    private Node rotateLeft(Node parentNode) {
        Node currentNode = parentNode.right;
        parentNode.right = currentNode.left;
        currentNode.left = parentNode;
        fixHeight(parentNode);
        fixHeight(currentNode);
        return currentNode;
    }

    private int factor(Node node) {
        return node == null ? 0 : height(node.left) - height(node.right);
    }

    private Node balance(Node node) {
        if (factor(node) == 2) {
            if (factor(node.left) < 0) {
                node.left = rotateLeft(node.left);
            }
            return rotateRight(node);
        }
        if (factor(node) == -2) {
            if (factor(node.right) > 0) {
                node.right = rotateRight(node.right);
            }
            return rotateLeft(node);
        }
        return node;
    }

    private Node deleteMin(Node node) {
        if (node.left == null) {
            return node.right;
        }
        node.left = deleteMin(node.left);
        fixHeight(node);
        node = balance(node);
        return node;
    }

    private Node innerDelete(Node node) {
        if (node.right == null) {
            return node.left;
        }
        if (node.left == null) {
            return node.right;
        }
        Node temp = node;
        node = minNode(temp.right);
        node.right = deleteMin(temp.right);
        node.left = temp.left;
        fixHeight(node);
        node = balance(node);
        return node;
    }

}