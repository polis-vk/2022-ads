package company.vk.polis.ads.bst;

import org.jetbrains.annotations.NotNull;

/**
 * AVL implementation of binary search tree.
 */
public class AvlBinarySearchTree<Key extends Comparable<Key>, Value> implements BinarySearchTree<Key, Value> {

    private int size = 0;
    private Node root = null;

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
        Value deletedValue = get(key);
        if (deletedValue == null) {
            return null;
        }
        root = delete(root, key);
        return deletedValue;
    }

    private Node delete(Node node, Key key) {
        if (node == null) {
            return null;
        }
        if (key.compareTo(node.key) < 0) {
            node.left = delete(node.left, key);
        }
        if (key.compareTo(node.key) > 0) {
            node.right = delete(node.right, key);
        }
        if (key == node.key) {
            size--;
            node = innerDelete(node);
        }
        return node;
    }

    private Node innerDelete(Node node) {
        if (node.right == null) {
            return node.left;
        }
        if (node.left == null) {
            return node.right;
        }
        Node curNode = node;
        node = getMin(curNode.right);
        node.right = deleteMin(curNode.right);
        node.left = curNode.left;
        return node;
    }

    Node deleteMin(Node node) {
        if (node.left == null) {
            return node.right;
        }
        node.left = deleteMin(node.left);
        return node;
    }

    private Node getMin(Node node) {
        if (root == null) {
            return null;
        }
        Node curNode = node;
        while (curNode.left != null) {
            curNode = curNode.left;
        }
        return curNode;
    }

    @Override
    public Key min() {
        Node minNode = getMin(root);
        return minNode == null ? null : minNode.key;
    }

    @Override
    public Value minValue() {
        Node minNode = getMin(root);
        return minNode == null ? null : minNode.value;
    }

    private Node getMax(Node node) {
        if (root == null) {
            return null;
        }
        Node curNode = node;
        while (curNode.right != null) {
            curNode = curNode.right;
        }
        return curNode;
    }

    @Override
    public Key max() {
        Node maxNode = getMax(root);
        return maxNode == null ? null : maxNode.key;
    }

    @Override
    public Value maxValue() {
        Node maxNode = getMax(root);
        return maxNode == null ? null : maxNode.value;
    }

    @Override
    public Key floor(@NotNull Key key) {
        if (root == null) {
            return null;
        }
        Node curNode = root;
        while (curNode != null) {
            if (key.compareTo(curNode.key) < 0) {
                curNode = curNode.left;
            } else if (key.compareTo(curNode.key) > 0) {
                if (curNode.right == null || key.compareTo(curNode.right.key) < 0) {
                    break;
                }
                curNode = curNode.right;
            } else {
                break;
            }
        }
        return curNode == null ? null : curNode.key;
    }

    @Override
    public Key ceil(@NotNull Key key) {
        if (root == null) {
            return null;
        }
        Node curNode = root;
        while (curNode != null) {
            if (key.compareTo(curNode.key) < 0) {
                if (curNode.left == null || key.compareTo(curNode.left.key) > 0) {
                    break;
                }
                curNode = curNode.left;
            } else if (key.compareTo(curNode.key) > 0) {
                curNode = curNode.right;
            } else {
                break;
            }
        }
        return curNode == null ? null : curNode.key;
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
        node.height = 1 +
                Math.max(height(node.left),
                        height(node.right));
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

    private int factor(Node node) {
        return height(node.left) - height(node.right);
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
                return rotateLeft(node);
            }
        }
        return node;
    }
}
