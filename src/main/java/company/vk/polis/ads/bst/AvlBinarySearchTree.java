package company.vk.polis.ads.bst;

import org.jetbrains.annotations.NotNull;

/**
 * AVL implementation of binary search tree.
 */
public class AvlBinarySearchTree<K extends Comparable<K>, V> implements BinarySearchTree<K, V> {
    private Node root;
    private int size;

    private class Node {
        K key;
        V value;
        Node left;
        Node right;
        int height;

        public Node(K key, V value, int height) {
            this.key = key;
            this.value = value;
            this.height = height;
        }
    }

    @Override
    public V get(@NotNull K key) {
        return get(root, key);
    }

    private V get(Node node, K key) {
        if (node == null) {
            return null;
        }
        if (key.compareTo(node.key) > 0) {
            return get(node.right, key);
        } else if (key.compareTo(node.key) < 0) {
            return get(node.left, key);
        } else {
            return node.value;
        }
    }

    @Override
    public void put(@NotNull K key, @NotNull V value) {
        root = put(root, key, value);
    }

    private Node put(Node node, K key, V value) {
        if (node == null) {
            size++;
            return new Node(key, value, 1);
        }
        if (key.compareTo(node.key) > 0) {
            node.right = put(node.right, key, value);
        } else if (key.compareTo(node.key) < 0) {
            node.left = put(node.left, key, value);
        } else {
            node.value = value;
        }
        fixHeight(node);
        node = balance(node);
        return node;
    }

    @Override
    public V remove(@NotNull K key) {
        V value = get(root, key);
        if (value != null) {
            root = remove(root, key);
            size--;
        }
        return value;
    }

    private Node remove(Node node, K key) {
        if (node == null) {
            return null;
        }
        if (key.compareTo(node.key) > 0) {
            node.right = remove(node.right, key);
        } else if (key.compareTo(node.key) < 0) {
            node.left = remove(node.left, key);
        } else {
            node = innerRemove(node);
            fixHeight(node);
            node = balance(node);
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
        Node current = node;
        node = min(current.right);
        node.right = removeMin(current.right);
        node.left = current.left;
        return node;
    }

    private Node removeMin(Node node) {
        if (node.left == null) {
            return node.right;
        }
        node.left = removeMin(node.left);
        return node;
    }

    @Override
    public K min() {
        if (root == null) {
            return null;
        }
        Node min = min(root);
        return min.key;
    }

    @Override
    public V minValue() {
        if (root == null) {
            return null;
        }
        Node min = min(root);
        return min.value;
    }

    private Node min(Node node) {
        if (node.left == null) {
            return node;
        }
        return min(node.left);
    }

    @Override
    public K max() {
        if (root == null) {
            return null;
        }
        Node max = max(root);
        return max.key;
    }

    @Override
    public V maxValue() {
        if (root == null) {
            return null;
        }
        Node max = max(root);
        return max.value;
    }

    private Node max(Node node) {
        if (node.right == null) {
            return node;
        }
        return max(node.right);
    }

    @Override
    public K floor(@NotNull K key) {
        return floor(root, key);
    }

    private K floor(Node node, K key) {
        if (node == null) {
            return null;
        }
        if (key.compareTo(node.key) > 0) {
            K atRight = floor(node.right, key);
            if (atRight != null && atRight.compareTo(node.key) > 0) {
                return atRight;
            }
        } else if (key.compareTo(node.key) < 0) {
            return floor(node.left, key);
        }
        return node.key;
    }

    @Override
    public K ceil(@NotNull K key) {
        return ceil(root, key);
    }

    private K ceil(Node node, K key) {
        if (node == null) {
            return null;
        }
        if (key.compareTo(node.key) < 0) {
            K atLeft = ceil(node.left, key);
            if (atLeft != null && atLeft.compareTo(node.key) < 0) {
                return atLeft;
            }
        } else if (key.compareTo(node.key) > 0) {
            return ceil(node.right, key);
        }
        return node.key;
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

    private Node rotateRight(Node node) {
        Node left = node.left;
        node.left = left.right;
        left.right = node;
        fixHeight(node);
        fixHeight(left);
        return left;
    }

    private Node rotateLeft(Node node) {
        Node right = node.right;
        node.right = right.left;
        right.left = node;
        fixHeight(node);
        fixHeight(right);
        return right;
    }

    private int factor(Node node) {
        if (node == null) {
            return 0;
        }
        return height(node.left) - height(node.right);
    }

    private Node balance(Node node) {
        if (node == null) {
            return null;
        }
        if (factor(node) == 2) {
            if (factor(node.left) < 0) {
                node.left = rotateLeft(node.left);
            }
            return rotateRight(node);
        } else if (factor(node) == -2) {
            if (factor(node.right) > 0) {
                node.right = rotateRight(node.right);
            }
            return rotateLeft(node);
        }
        return node;
    }
}
