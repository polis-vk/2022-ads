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

    private class Node {
        Key key;
        Value value;
        Node left;
        Node right;

        public Node(Key key, Value value, boolean color) {
            this.key = key;
            this.value = value;
            this.color = color;
        }

        boolean color;
    }

    @Nullable
    @Override
    public Value get(@NotNull Key key) {
        Node ansNode = get(root, key);
        return ansNode == null ? null : ansNode.value;
    }

    public Node get(Node node, Key key) {
        if (node == null) {
            return null;
        }
        if (key.compareTo(node.key) < 0) {
            return get(node.left, key);
        } else if (key.compareTo(node.key) > 0) {
            return get(node.right, key);
        } else {
            return node;
        }
    }

    @Override
    public void put(@NotNull Key key, @NotNull Value value) {
        if (root == null) {
            root = new Node(key, value, BLACK);
        } else {
            root = put(root, key, value);
        }
    }

    private Node put(Node x, Key key, Value value) {
        if (x == null) {
            return new Node(key, value, RED);
        }
        if (key.compareTo(x.key) < 0) {
            x.left = put(x.left, key, value);
        } else if (key.compareTo(x.key) > 0) {
            x.right = put(x.right, key, value);
        } else {
            x.value = value;
        }
        return fixUp(x);
    }

    private Node fixUp(Node x) {
        if (isRed(x.right) && !isRed(x.left)) {
            x = rotateLeft(x);
        }
        if (isRed(x.left) && isRed(x.left.left)) {
            x = rotateRight(x);
        }
        if (isRed(x.left) && isRed(x.right)) {
            flipColors(x);
        }
        return x;
    }

    private Node flipColors(Node x) {
        x.color = !x.color;
        x.left.color = !x.left.color;
        x.right.color = !x.right.color;
        return x;
    }

    private Node rotateRight(Node y) {
        Node x = y.left;
        y.left = x.right;
        x.right = y;
        x.color = y.color;
        y.color = RED;
        return x;
    }

    Node rotateLeft(Node x) {
        Node y = x.right;
        x.right = y.left;
        y.left = x;
        y.color = x.color;
        x.color = RED;
        return y;
    }

    boolean isRed(Node x) {
        return x != null &&
                x.color == RED;
    }

    @Nullable
    @Override
    public Value remove(@NotNull Key key) {
        Value ans = get(key);
        root = delete(root, key);
        return ans;
    }

    Node delete(Node x, Key key) {
        if (x == null) return null;
        if (key.compareTo(x.key) < 0) {
            if (x.left != null) {
                if (!isRed(x.left) && !isRed(x.left.left))
                    x = moveRedLeft(x);
                x.left = delete(x.left, key);
            }
        } else {
            if (isRed(x.left)) {
                x = rotateRight(x);
                x.right = delete(x.right, key);
            } else if (x.key == key && x.right == null) {
                return null; // at the bottom
            } else {
                if (x.right != null && !isRed(x.right) && !isRed(x.right.left)) {
                    x = moveRedRight(x); // preserve invariant
                }
                if (x.key == key) {
                    Node min = minNode(x.right);
                    x.key = min.key;
                    x.value = min.value;
                    x.right = deleteMin(x.right);
                } else {
                    x.right = delete(x.right, key);
                }
            }
        }
        return fixUp(x);
    }

    Node moveRedLeft(Node x) {
        flipColors(x);
        if (isRed(x.right.left)) {
            x.right = rotateRight(x.right);
            x = rotateLeft(x);
            flipColors(x);
        }
        return x;
    }

    Node moveRedRight(Node x) {
        flipColors(x);
        if (isRed(x.left.left)) {
            x = rotateRight(x);
            flipColors(x);
        }
        return x;
    }

    Node deleteMin(Node x) {
        if (x.left == null)
            return null;
        if (!isRed(x.left) && !isRed(x.left.left))
            x = moveRedLeft(x);
        x.left = deleteMin(x.left);
        return fixUp(x);
    }

    @Nullable
    @Override
    public Key min() {
        if (root == null) {
            return null;
        }
        return minNode(root).key;
    }

    private Node minNode(Node start) {
        if (start.left != null) {
            return minNode(start.left);
        }
        return start;
    }

    @Nullable
    @Override
    public Value minValue() {
        if (root == null) {
            return null;
        }
        return minNode(root).value;
    }

    @Nullable
    @Override
    public Key max() {
        if (root == null) {
            return null;
        }
        return maxNode(root).key;
    }

    private Node maxNode(Node start) {
        if (start.right != null) {
            return maxNode(start.right);
        }
        return start;
    }

    @Nullable
    @Override
    public Value maxValue() {
        if (root == null) {
            return null;
        }
        return maxNode(root).value;
    }

    @Nullable
    @Override
    public Key floor(@NotNull Key key) {
        Node ans = null;
        Node nowNode = root;
        while (nowNode != null) {
            int compared = nowNode.key.compareTo(key);
            if (compared == 0) {
                return nowNode.key;
            } else if (compared < 0) {
                if (ans == null || ans.key.compareTo(nowNode.key) < 0) {
                    ans = nowNode;
                }
                nowNode = nowNode.right;
            } else {
                nowNode = nowNode.left;
            }
        }
        return ans == null ? null : ans.key;
    }

    @Nullable
    @Override
    public Key ceil(@NotNull Key key) {
        Node ans = null;
        Node nowNode = root;
        while (nowNode != null) {
            int compared = nowNode.key.compareTo(key);
            if (compared == 0) {
                return nowNode.key;
            } else if (compared < 0) {
                nowNode = nowNode.right;
            } else {
                if (ans == null || ans.key.compareTo(nowNode.key) > 0) {
                    ans = nowNode;
                }
                nowNode = nowNode.left;
            }
        }
        return ans == null ? null : ans.key;
    }

    @Override
    public int size() {
        return size(root);
    }

    private int size(Node node) {
        if (node == null) {
            return 0;
        }
        int ans = 1;
        ans += size(node.left);
        ans += size(node.right);
        return ans;
    }

    /**
     * Только для тестов
     * Саму высоту хранить не обязательно, достаточно сделать рекурсивное вычисление
     */
    @Override
    public int height() {
        return height(root);
    }

    private int height(Node node) {
        if (node == null) {
            return 0;
        }
        return Math.max(height(node.left), height(node.right)) + 1;
    }
}
