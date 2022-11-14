package company.vk.polis.ads.bst;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * LLRB implementation of binary search tree.
 */
public class RedBlackBinarySearchTree<Key extends Comparable<Key>, Value>
        implements BinarySearchTree<Key, Value> {

    private static final boolean BLACK = false;
    private static final boolean RED = true;

    private Node root = null;
    private int size;

    private class Node {
        Key key;
        Value value;
        Node left;
        Node right;
        boolean color;

        public Node(Key key, Value value, Boolean color) {
            this.key = key;
            this.value = value;
            this.color = color;
        }
    }

    @Nullable
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
        root.color = BLACK;
    }

    private Node put(Node x, @NotNull Key key, @NotNull Value value) {
        if (x == null)
            return new Node(key, value, RED);
        if (key.compareTo(x.key) < 0)
            x.left = put(x.left, key, value);
        else if (key.compareTo(x.key) > 0)
            x.right = put(x.right, key, value);
        else
            x.value = value;
        return fixUp(x);
    }

    private Node fixUp(Node x) {
        if (isRed(x.right) && !isRed(x.left))
            x = rotateLeft(x);
        if (isRed(x.left) && isRed(x.left.left))
            x = rotateRight(x);
        if (isRed(x.left) && isRed(x.right))
            flipColors(x);
        return x;
    }

    private boolean isRed(Node x) {
        return x != null && x.color == RED;
    }

    private Node rotateLeft(Node x) {
        Node y = x.right;
        x.right = y.left;
        y.left = x;
        y.color = x.color;
        x.color = RED;
        return y;
    }

    private Node rotateRight(Node y) {
        Node x = y.left;
        y.left = x.right;
        x.right = y;
        x.color = y.color;
        y.color = RED;
        return x;
    }

    private Node flipColors(Node x) {
        x.color = !x.color;
        x.left.color = !x.left.color;
        x.right.color = !x.right.color;
        return x;
    }

    @Nullable
    @Override
    public Value remove(@NotNull Key key) {
        Value currValue = get(key);
        if (currValue != null) {
            size--;
            root = remove(root, key);
        }
        return currValue;
    }

    private Node remove(Node x, @NotNull Key key) {
        if (x == null) {
            return null;
        }
        if (key.compareTo(x.key) < 0) {
            if (x.left != null) {
                if (!isRed(x.left) && !isRed(x.left.left)) {
                    x = moveRedLeft(x);
                }
                x.left = remove(x.left, key);
            }
        } else {
            if (isRed(x.left)) {
                x = rotateRight(x);
                x.right = remove(x.right, key);
            } else if (x.key == key && x.right == null) {
                return null;
            } else {
                if (x.right != null && !isRed(x.right) && !isRed(x.right.left)) {
                    x = moveRedRight(x);
                }
                if (x.key == key) {
                    Node min = minNode(x.right);
                    x.key = min.key;
                    x.value = min.value;
                    x.right = deleteMin(x.right);
                } else {
                    x.right = remove(x.right, key);
                }
            }
        }
        return fixUp(x);
    }

    private Node deleteMin(Node x) {
        if (x.left == null) {
            return null;
        }
        if (!isRed(x.left) && !isRed(x.left.left)) {
            x = moveRedLeft(x);
        }
        x.left = deleteMin(x.left);
        return fixUp(x);
    }

    private Node moveRedLeft(Node x) {
        flipColors(x);
        if (isRed(x.right.left)) {
            x.right = rotateRight(x.right);
            x = rotateLeft(x);
            flipColors(x);
        }
        return x;
    }

    private Node moveRedRight(Node x) {
        flipColors(x);
        if (isRed(x.left.left)) {
            x = rotateRight(x);
            flipColors(x);
        }
        return x;
    }

    @Nullable
    @Override
    public Key min() {
        Node currNode = minNode(root);
        return currNode == null ? null : currNode.key;
    }

    @Nullable
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

    @Nullable
    @Override
    public Key max() {
        Node currNode = maxNode(root);
        return currNode == null ? null : currNode.key;
    }

    @Nullable
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

    @Nullable
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

    @Nullable
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

    /**
     * Только для тестов
     * Саму высоту хранить не обязательно, достаточно сделать рекурсивное вычисление
     */
    @Override
    public int height() {
        return height(root) - 1;
    }

    private int height(Node node) {
        if (node == null) {
            return 1;
        }
        return Math.max(height(node.left), height(node.right)) + 1;
    }
}
