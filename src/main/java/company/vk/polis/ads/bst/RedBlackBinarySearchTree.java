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
    private int size = 0;

    private class Node {
        Node(Key key, Value value, boolean color) {
            this.key = key;
            this.value = value;
            this.color = color;
        }

        Key key;
        Value value;
        Node left;
        Node right;
        boolean color;
    }

    @Nullable
    @Override
    public Value get(@NotNull Key key) {
        Node node = get(root, key);
        if (node == null) {
            return null;
        }
        return node.value;
    }

    @Override
    public void put(@NotNull Key key, @NotNull Value value) {
        root = put(root, key, value);
        root.color = BLACK;
    }

    @Nullable
    @Override
    public Value remove(@NotNull Key key) {
        Value result = get(key);
        if (result == null) {
            return null;
        }
        root = delete(root, key);
        size--;
        return result;
    }

    @Nullable
    @Override
    public Key min() {
        Node tmp = min(root);
        if(tmp == null){
            return null;
        }

        return tmp.key;
    }

    @Nullable
    @Override
    public Value minValue() {
        Node tmp = min(root);
        if(tmp == null){
            return null;
        }

        return tmp.value;
    }

    @Nullable
    @Override
    public Key max() {
        Node tmp = max(root);
        if(tmp == null){
            return null;
        }

        return tmp.key;
    }

    @Nullable
    @Override
    public Value maxValue() {
        Node tmp = max(root);
        if(tmp == null){
            return null;
        }

        return tmp.value;
    }

    @Nullable
    @Override
    public Key floor(@NotNull Key key) {
        Node floor = getFloor(root, key);
        if (floor == null) {
            return null;
        }
        return floor.key;
    }


    @Nullable
    @Override
    public Key ceil(@NotNull Key key) {
        Node ceil = getCeil(root, key);
        if (ceil == null) {
            return null;
        }
        return ceil.key;
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
        return getHeight(root, 0);

    }

    boolean isRed(Node x) {
        return x != null && x.color == RED;
    }

    Node flipColors(Node x) {
        x.color = !x.color;
        x.left.color = !x.left.color;
        x.right.color = !x.right.color;
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

    Node rotateRight(Node y) {
        Node x = y.left;
        y.left = x.right;
        x.right = y;
        x.color = y.color;
        y.color = RED;
        return x;
    }

    Node fixUp(Node x) {
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

    Node put(Node x, Key key, Value value) {
        if (x == null) {
            size++;
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

    private Node moveRedLeft(Node x) {
        flipColors(x);
        if (isRed(x.right.left)) {
            x.right = rotateRight(x.right);
            x = rotateLeft(x);
            flipColors(x);
        }
        return x;
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

    private Node moveRedRight(Node x) {
        flipColors(x);
        if (isRed(x.left.left)) {
            x = rotateRight(x);
            flipColors(x);
        }
        return x;
    }

    private Node delete(Node x, Key key) {
        if (x == null) {
            return null;
        }
        if (key.compareTo(x.key) < 0) {
            if (x.left != null) {
                if (!isRed(x.left) && !isRed(x.left.left)) {
                    x = moveRedLeft(x);
                }
                x.left = delete(x.left, key);
            }
        } else {
            if (isRed(x.left)) {
                x = rotateRight(x);
                x.right = delete(x.right, key);
            } else if (x.key.compareTo(key) == 0 && x.right == null) {
                return null;
            } else {
                if (x.right != null && !isRed(x.right) && !isRed(x.right.left)) {
                    x = moveRedRight(x);
                }
                if (x.key.compareTo(key) == 0) {
                    Node min = min(x.right);
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

    private Node get(Node x, Key key) {
        if (x == null) {
            return null;
        }

        if (key.compareTo(x.key) < 0) {
            return get(x.left, key);
        }
        if (key.compareTo(x.key) > 0) {
            return get(x.right, key);
        }
        return x;
    }

    private Node min(Node x) {
        if (x == null) {
            return null;
        }

        Node tmp = x;
        while (tmp.left != null) {
            tmp = tmp.left;
        }
        return tmp;
    }

    private Node max(Node x) {
        if (x == null) {
            return null;
        }

        Node tmp = x;
        while (tmp.right != null) {
            tmp = tmp.right;
        }
        return tmp;
    }

    private Node getFloor(Node x, Key key) {
        if (x == null) {
            return null;
        }
        if (x.key.compareTo(key) == 0) {
            return x;
        }
        if (x.key.compareTo(key) > 0) {
            return getFloor(x.left, key);
        }
        Node floor = getFloor(x.right, key);
        return (floor != null && floor.key.compareTo(key) <= 0) ? floor : x;
    }

    private Node getCeil(Node x, Key key) {
        if (x == null) {
            return null;
        }
        if (x.key.compareTo(key) == 0) {
            return x;
        }
        if (x.key.compareTo(key) < 0) {
            return getCeil(x.right, key);
        }
        Node ceil = getCeil(x.left, key);
        return (ceil != null && ceil.key.compareTo(key) >= 0) ? ceil : x;
    }

    private int getHeight(Node x, int start){
        int height = start;
        if (x == null) {
            return height;
        }
        height++;
        return getHeight(x.left, height);
    }

}
