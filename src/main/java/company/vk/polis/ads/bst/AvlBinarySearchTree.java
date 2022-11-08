package company.vk.polis.ads.bst;

import org.jetbrains.annotations.NotNull;

/**
 * AVL implementation of binary search tree.
 */
public class AvlBinarySearchTree<Key extends Comparable<Key>, Value> implements BinarySearchTree<Key, Value> {
    Node root;
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
    }

    @Override
    public Value get(@NotNull Key key) {
        return get(root, key);
    }

    private Value get(Node currNode, @NotNull Key key) {
        if (currNode == null) return null;
        if (key.compareTo(currNode.key) < 0) return get(currNode.left, key);
        if (key.compareTo(currNode.key) > 0) return get(currNode.right, key);
        return currNode.value;
    }

    @Override
    public void put(@NotNull Key key, @NotNull Value value) {
        root = put(root, key, value);
    }

    private Node put(Node currNode, Key key, Value value) {
        if (currNode == null) {
            size++;
            return new Node(key, value, 1);
        }
        if (key.compareTo(currNode.key) < 0) {
            currNode.left = put(currNode.left, key, value);
        } else if (key.compareTo(currNode.key) > 0) {
            currNode.right = put(currNode.right, key, value);
        } else {
            currNode.value = value;
        }
        fixHeight(currNode);
        currNode = balance(currNode);
        return currNode;
    }

    private void fixHeight(Node currNode) {
        if (currNode != null) {
            currNode.height = 1 + Math.max(height(currNode.left), height(currNode.right));
        }
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

    private int factor(Node currNode) {
        return currNode == null ? 0 : (height(currNode.left) - height(currNode.right));
    }

    private Node balance(Node currNode) {
        if (factor(currNode) == 2) {
            if (factor(currNode.left) < 0) {
                currNode.left = rotateLeft(currNode.left);
            }
            return rotateRight(currNode);
        }

        if (factor(currNode) == -2) {
            if (factor(currNode.right) > 0) {
                currNode.right = rotateRight(currNode.right);
            }
            return rotateLeft(currNode);
        }
        return currNode;
    }

    @Override
    public Value remove(@NotNull Key key) {
        Value result = get(key);
        root = remove(root, key);
        return result;
    }

    private Node remove(Node x, @NotNull Key key) {
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
            x = innerRemove(x);
            size--;
        }
        fixHeight(x);
        x = balance(x);
        return x;
    }

    private Node innerRemove(Node x) {
        if (x.right == null) {
            return x.left;
        }
        if (x.left == null) {
            return x.right;
        }
        Node temp = x;
        x = min(temp.right);
        x.right = removeMin(temp.right);
        x.left = temp.left;
        fixHeight(x);
        x = balance(x);
        return x;
    }

    private Node removeMin(Node x) {
        if (x.left == null) {
            return x.right;
        }
        x.left = removeMin(x.left);
        return x;
    }


    @Override
    public Key min() {
        return isEmpty() ? null : min(root).key;
    }

    private Node min(Node currNode) {
        if (currNode.left == null) return currNode;
        return min(currNode.left);
    }

    @Override
    public Value minValue() {
        return get(min());
    }

    @Override
    public Key max() {
        return isEmpty() ? null : max(root).key;
    }

    private Node max(Node currNode) {
        if (currNode.right == null) return currNode;
        return max(currNode.right);
    }

    @Override
    public Value maxValue() {
        return get(max());
    }

    @Override
    public Key floor(@NotNull Key key) {
        if (isEmpty() || floor(root, key) == null) {
            return null;
        }
        return floor(root, key).key;

    }

    private Node floor(Node x, Key key) {
        if (x == null) {
            return null;
        }
        int comparator = x.key.compareTo(key);
        if (comparator == 0) {
            return x;
        }
        if (comparator > 0) {
            return floor(x.left, key);
        }
        Node next = floor(x.right, key);
        return (next != null && next.key.compareTo(key) <= 0) ? next : x;
    }

    @Override
    public Key ceil(@NotNull Key key) {
        if (isEmpty() || ceil(root, key) == null) {
            return null;
        }
        return ceil(root, key).key;
    }

    private Node ceil(Node x, Key key) {
        if (x == null) {
            return null;
        }
        int comparator = x.key.compareTo(key);
        if (comparator == 0) {
            return x;
        }
        if (comparator < 0) {
            return ceil(x.right, key);
        }
        Node next = ceil(x.left, key);
        return (next != null && next.key.compareTo(key) >= 0) ? next : x;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public int height() {
        return height(root);
    }

    private int height(Node currNode) {
        return currNode == null ? 0 : currNode.height;
    }
}
