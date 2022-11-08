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

    private Node root = null;
    private int size;

    @Override
    public Value get(@NotNull Key key) {
        Node currNode = get(root, key);
        return currNode == null ? null : currNode.value;
    }

    private Node get(Node node, @NotNull Key key) {
        if (node == null) {
            return null;
        } else if (node.key.compareTo(key) == 0) {
            return node;
        }
        return get(key.compareTo(node.key) > 0 ? node.right : node.left, key);
    }

    @Override
    public void put(@NotNull Key key, @NotNull Value value) {
        if (!containsKey(key)) {
            size++;
        }
        root = put(root, key, value);
    }

    private Node put(Node node, @NotNull Key key, @NotNull Value value) {
        if (node == null) {
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

    private void fixHeight(Node node) {
        node.height = 1 + Math.max(height(node.left), height(node.right));
    }

    private int height(Node node) {
        return node == null ? 0 : node.height;
    }

    @Override
    public Value remove(@NotNull Key key) {
        Value currValue = get(key);
        if (currValue != null) {
            size--;
        }
        root = remove(root, key);
        return currValue;
    }

    private Node remove(Node node, @NotNull Key key) {
        if (node == null) {
            return null;
        }
        if (key.compareTo(node.key) < 0) {
            node.left = remove(node.left, key);
        }
        if (key.compareTo(node.key) > 0) {
            node.right = remove(node.right, key);
        }
        if (key == node.key) {
            node = innerRemove(node);
        }
        return node;
    }

    private Node innerRemove(Node node) {
        if (node.right == null) {
            return node.left;
        }
        if (node.left == null) {
            return node.right;
        }
        Node temp = node;
        node = minNode(temp.right);
        node.right = removeMin(temp.right);
        node.left = temp.left;
        return node;
    }

    private Node removeMin(Node node) {
        if (node.left == null) {
            return node.right;
        }
        node.left = removeMin(node.left);
        return node;
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

    @Override
    public Key min() {
        Node currNode = minNode(root);
        return currNode == null ? null : currNode.key;
    }

    @Override
    public Value minValue() {
        Node currNode = minNode(root);
        return currNode == null ? null : currNode.value;
    }

    private Node minNode(Node node) {
        if (node == null) {
            return null;
        } else if (node.left == null) {
            return node;
        }
        return minNode(node.left);
    }

    @Override
    public Key max() {
        Node currNode = maxNode(root);
        return currNode == null ? null : currNode.key;
    }

    @Override
    public Value maxValue() {
        Node currNode = maxNode(root);
        return currNode == null ? null : currNode.value;
    }

    private Node maxNode(Node node) {
        if (node == null) {
            return null;
        } else if (node.right == null) {
            return node;
        }
        return maxNode(node.right);
    }

    @Override
    public Key floor(@NotNull Key key) {
        Node currNode = floor(root, null, key);
        return currNode == null ? null : currNode.key;
    }

    private Node floor(Node node, Node prevNode, @NotNull Key key) {
        if (node == null) {
            return null;
        } else if (node.key.compareTo(key) == 0) {
            return node;
        }
        Node nextNode = key.compareTo(node.key) > 0 ? node.right : node.left;
        if (nextNode == null) {
            if (node.key.compareTo(key) < 0) {
                return node;
            } else if (prevNode.key.compareTo(key) < 0) {
                return prevNode;
            }
        }
        return floor(nextNode, node, key);
    }

    @Override
    public Key ceil(@NotNull Key key) {
        Node currNode = ceil(root, key);
        return currNode == null ? null : currNode.key;
    }

    private Node ceil(Node node, @NotNull Key key) {
        if (node == null) {
            return null;
        } else if (node.key == key) {
            return node;
        }

        Node nextNode = key.compareTo(node.key) > 0 ? node.right : node.left;
        if (nextNode == null) {
            if (node.key.compareTo(key) < 0) {
                return null;
            } else {
                return node;
            }
        }
        return ceil(nextNode, key);
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
