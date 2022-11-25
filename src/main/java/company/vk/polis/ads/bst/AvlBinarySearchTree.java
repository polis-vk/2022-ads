package company.vk.polis.ads.bst;

import org.jetbrains.annotations.NotNull;

/**
 * AVL implementation of binary search tree.
 */
public class AvlBinarySearchTree<Key extends Comparable<Key>, Value> implements BinarySearchTree<Key, Value> {

    private Node root;

    private class Node {
        Key key;
        Value value;
        Node left;
        Node right;
        int height;

        public int size() {
            int ans = 0;
            if (left != null) {
                ans += left.size();
            }
            if (right != null) {
                ans += right.size();
            }
            return ans + 1;
        }
    }

    @Override
    public Value get(@NotNull Key key) {
        Node nowNode = root;
        while (nowNode != null) {
            int compared = nowNode.key.compareTo(key);
            if (compared == 0) {
                return nowNode.value;
            } else if (compared < 0) {
                nowNode = nowNode.right;
            } else {
                nowNode = nowNode.left;
            }
        }
        return null;
    }

    @Override
    public void put(@NotNull Key key, @NotNull Value value) {
        root = put(root, key, value);
    }

    @Override
    public Value remove(@NotNull Key key) {
        Value ans = get(key);
        root = remove(root, key);
        return ans;
    }

    @Override
    public Key min() {
        if (root == null) {
            return null;
        }
        Node nowNode = root;
        while (nowNode.left != null) {
            nowNode = nowNode.left;
        }
        return nowNode.key;
    }

    @Override
    public Value minValue() {
        if (root == null) {
            return null;
        }
        Node nowNode = root;
        while (nowNode.left != null) {
            nowNode = nowNode.left;
        }
        return nowNode.value;
    }

    @Override
    public Key max() {
        if (root == null) {
            return null;
        }
        Node nowNode = root;
        while (nowNode.right != null) {
            nowNode = nowNode.right;
        }
        return nowNode.key;
    }

    @Override
    public Value maxValue() {
        if (root == null) {
            return null;
        }
        Node nowNode = root;
        while (nowNode.right != null) {
            nowNode = nowNode.right;
        }
        return nowNode.value;
    }


    // максимальный, не превосходящий данного
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
        return root == null ? 0 : root.size();
    }

    @Override
    public int height() {
        return height(root);
    }

    private int height(Node node) {
        return node == null ? 0 : node.height;
    }

    private Node remove(Node x, @NotNull Key key) {
        if (x == null) {
            return null;
        }
        if (key.compareTo(x.key) < 0) {
            x.left = remove(x.left, key);
        } else if (key.compareTo(x.key) > 0) {
            x.right = remove(x.right, key);
        } else {
            x = innerDelete(x);
        }
        x = balance(x);
        if (x != null) {
            fixHeight(x);
        }
        return x;
    }

    private Node min(Node x) {
        if (x == null) {
            return null;
        }
        Node nowNode = x;
        while (nowNode.left != null) {
            nowNode = nowNode.left;
        }
        return nowNode;
    }

    private Node put(Node node, Key key, Value value) {
        if (node == null){
            Node ans = new Node();
            ans.key = key;
            ans.value = value;
            ans.height = 1;
            return ans;
        }
        int compared = node.key.compareTo(key);
        if (compared == 0) {
            node.value = value;
            return node;
        } else if (compared < 0) {
            node.right = put(node.right, key, value);
        } else {
            node.left = put(node.left, key, value);
        }
        fixHeight(node);
        node = balance(node);
        return node;
    }


    private Node deleteMin(Node x) {
        if (x == null) {
            return null;
        }
        if (x.left == null) {
            return x.right;
        }
        Node nowNode = x;
        while (nowNode.left.left != null) {
            nowNode = nowNode.left;
        }
        nowNode.left = null;
        return nowNode;
    }

    private Node innerDelete(Node x) {
        if (x.right == null) {
            return x.left;
        }
        if (x.left == null) {
            return x.right;
        }
        Node t = x;
        x = min(t.right);
        x.right = deleteMin(t.right);
        x.left = t.left;
        return x;
    }

    private int factor(Node x) {
        if (x == null) {
            return 0;
        }
        return height(x.left) -
                height(x.right);
    }

    private Node balance(Node x){
        if (factor(x) >= 2) {
            if (factor(x.left) < 0) {
                x.left = rotateLeft(x.left);
            }
            return rotateRight(x);
        }
        if (factor(x) <= -2) {
            if (factor(x.right) > 0) {
                x.right = rotateRight(x.right);
            }
            return rotateLeft(x);
        }
        return x;
    }

    private Node rotateLeft(@NotNull Node y) {
        Node x = y.right;
        y.right = x.left;
        x.left = y;
        fixHeight(y);
        fixHeight(x);
        return x;
    }

    private Node rotateRight(Node y) {
        Node x = y.left;
        y.left = x.right;
        x.right = y;
        fixHeight(y);
        fixHeight(x);
        return x;
    }

    private void fixHeight(Node node) {
        node.height = 1 +
                Math.max(
                        height(node.left),
                        height(node.right)
                );
    }
}
