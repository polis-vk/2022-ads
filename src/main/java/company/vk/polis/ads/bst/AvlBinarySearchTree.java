package company.vk.polis.ads.bst;

import org.jetbrains.annotations.NotNull;

/**
 * AVL implementation of binary search tree.
 */
public class AvlBinarySearchTree<Key extends Comparable<Key>, Value> implements BinarySearchTree<Key, Value> {

    private Node head;
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
        return get(head, key);
    }

    private Value get(Node node, Key key) {
        if (node == null) {
            return null;
        }
        if (node.key == key) {
            return node.value;
        }
        if (key.compareTo(node.key) < 0) {
            return get(node.left, key);
        } else {
            return get(node.right, key);
        }
    }

    @Override
    public void put(@NotNull Key key, @NotNull Value value) {
        Value oldValue = get(key);
        if (oldValue == null) {
            size++;
        }
        head = put(head, key, value);
    }

    private Node put(Node node, Key key, Value value) {
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

    @Override
    public Value remove(@NotNull Key key) {
        Value oldValue = get(head, key);
        if (oldValue != null) {
            head = remove(head, key);
            size--;
        }
        return oldValue;
    }

    private Node remove(Node node, Key key) {
        if (node == null) {
            return null;
        }
        if (key.compareTo(node.key) < 0) {
            node.left = remove(node.left, key);
        } else if (key.compareTo(node.key) > 0) {
            node.right = remove(node.right, key);
        } else {
            if (node.left == null && node.right == null) {
                return null;
            }
            if (node.right == null) {
                return node.left;
            }
            if (node.left == null) {
                return node.right;
            }
            Node tempNode = node;
            node = findMin(tempNode.right);
            node.right = removeMin(tempNode.right);
            node.left = tempNode.left;
            fixHeight(node);
            node = balance(node);
            return node;
        }
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

    private Node findMin(Node node) {
        return node.left == null ? node : findMin(node.left);
    }

    @Override
    public Key min() {
        if (head == null) {
            return null;
        }
        Node min = min(head);
        return min.key;
    }

    @Override
    public Value minValue() {
        if (head == null) {
            return null;
        }
        Node min = min(head);
        return min.value;
    }

    @Override
    public Key max() {
        if (head == null) {
            return null;
        }
        Node max = max(head);
        return max.key;
    }

    @Override
    public Value maxValue() {
        if (head == null) {
            return null;
        }
        Node max = max(head);
        return max.value;
    }

    @Override
    public Key floor(@NotNull Key key) {
        return floor(head, key);
    }

    private Key floor(Node node, Key key) {
        if (node == null) {
            return null;
        }
        if (key.compareTo(node.key) >= 0) {
            Key rightFloor = floor(node.right, key);
            if (rightFloor != null) {
                return rightFloor;
            }
            return node.key;
        }
        return floor(node.left, key);
    }

    @Override
    public Key ceil(@NotNull Key key) {
        return ceil(head, key);
    }

    private Key ceil(Node node, Key key) {
        if (node == null) {
            return null;
        }
        if (key.compareTo(node.key) <= 0) {
            Key leftCeil = ceil(node.left, key);
            if (leftCeil != null) {
                return leftCeil;
            }
            return node.key;
        }
        return ceil(node.right, key);
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public int height() {
        return height(head);
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

    private int factor(Node node) {
        return height(node.left) - height(node.right);
    }

    private Node rotateRight(Node yNode) {
        Node xNode = yNode.left;
        yNode.left = xNode.right;
        xNode.right = yNode;
        fixHeight(yNode);
        fixHeight(xNode);
        return xNode;
    }

    private Node rotateLeft(Node xNode) {
        Node yNode = xNode.right;
        xNode.right = yNode.left;
        yNode.left = xNode;
        fixHeight(xNode);
        fixHeight(yNode);
        return yNode;
    }

    private void fixHeight(Node node) {
        node.height = 1 + Math.max(height(node.left), height(node.right));
    }

    private int height(Node node) {
        return node == null ? 0 : node.height;
    }

    private Node min(Node node) {
        return node.left == null ? node : min(node.left);
    }

    private Node max(Node node) {
        return node.right == null ? node : max(node.right);
    }

}
