package company.vk.polis.ads.bst;

import org.jetbrains.annotations.NotNull;

public class AvlBinarySearchTree<Key extends Comparable<Key>, Value> implements BinarySearchTree<Key, Value> {
    private Node root;
    private int size;
    private Node lastRemoved;

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
        return get(key, root);
    }

    private Value get(Key key, Node node) {
        if (node == null) {
            return null;
        }
        if (key.compareTo(node.key) < 0) {
            return get(key, node.left);
        }
        if (key.compareTo(node.key) > 0) {
            return get(key, node.right);
        }
        return node.value;
    }

    @Override
    public void put(@NotNull Key key, @NotNull Value value) {
        root = put(key, value, root);
    }

    private Node put(Key key, Value value, Node node) {
        if (node == null) {
            size++;
            return new Node(key, value, 1);
        }
        if (key.compareTo(node.key) < 0) {
            node.left = put(key, value, node.left);
        } else if (key.compareTo(node.key) > 0) {
            node.right = put(key, value, node.right);
        } else {
            node.value = value;
        }
        updateHeight(node);
        return rebalance(node);
    }

    /*    @Override
        public Value remove(@NotNull Key key) {
            root = remove(root, key);
            if (lastRemoved != null) {
                Value value = lastRemoved.value;
                lastRemoved = null;
                return value;
            }
            return null;
        }

        private Node remove(Node node, Key key) {
            if (node == null) {
                return null;
            }

            int cmpResult = key.compareTo(node.key);
            if (cmpResult < 0) {
                node.left = remove(node.left, key);
            } else if (cmpResult > 0) {
                node.right = remove(node.right, key);
            } else {
                lastRemoved = node;
                node = innerRemove(node);
                size--;
            }
            fixHeight(node);
            node = rebalance(node);
            return node;
        }

        private Node innerRemove(Node node) {
            if (node.left == null) {
                return node.right;
            }
            if (node.right == null) {
                return node.left;
            }

            Node temp = node;
            node = min(temp.right);
            node.right = removeMin(temp.right);
            node.left = temp.left;
            fixHeight(node);
            node = rebalance(node);
            return node;
        }

        private Node removeMin(Node node) {
            if (node.left == null) {
                return node.right;
            }
            node.left = removeMin(node.left);
            fixHeight(node);
            node = rebalance(node);
            return node;
        }*/
    @Override
    public Value remove(@NotNull Key key) {
        Value result = get(key, root);
        root = remove(key, root);
        return result;
    }

    private Node remove(Key key, Node node) {
        if (node == null) {
            return null;
        }
        if (key.compareTo(node.key) < 0) {
            node.left = remove(key, node.left);
        } else if (key.compareTo(node.key) > 0) {
            node.right = remove(key, node.right);
        } else {
            size--;
            if (node.right == null) {
                return node.left;
            }
            if (node.left == null) {
                return node.right;
            }
            Node temp = node;
            node = min(temp.right);
            node.right = deleteMin(temp.right);
            node.left = temp.left;
        }
        updateHeight(node);
        node = rebalance(node);
        return node;
    }

    private Node deleteMin(Node node) {
        if (node.left == null) {
            return node.right;
        }
        node.left = deleteMin(node.left);
        updateHeight(node);
        node = rebalance(node);
        return node;
    }

    @Override
    public Key min() {
        return root == null ? null : min(root).key;
    }

    @Override
    public Value minValue() {
        return root == null ? null : min(root).value;
    }

    private Node min(Node node) {
        while (node.left != null) {
            node = node.left;
        }
        return node;
    }

    @Override
    public Key max() {
        return root == null ? null : max(root).key;
    }

    @Override
    public Value maxValue() {
        return root == null ? null : max(root).value;
    }

    private Node max(Node node) {
        while (node.right != null) {
            node = node.right;
        }
        return node;
    }

    @Override
    public Key floor(@NotNull Key key) {
        Node result = floor(key, root);
        return result == null ? null : result.key;
    }

    private Node floor(Key key, Node node) {
        if (node == null) {
            return null;
        }
        if (key.compareTo(node.key) < 0) {
            return floor(key, node.left);
        }
        if (key.compareTo(node.key) > 0) {
            Node nextNode = floor(key, node.right);
            return nextNode == null ? node : nextNode;
        }
        return node;
    }

    @Override
    public Key ceil(@NotNull Key key) {
        Node result = ceil(key, root);
        return result == null ? null : result.key;
    }

    private Node ceil(Key key, Node node) {
        if (node == null) {
            return null;
        }
        if (key.compareTo(node.key) < 0) {
            Node nextNode = ceil(key, node.left);
            return nextNode == null ? node : nextNode;
        }
        if (key.compareTo(node.key) > 0) {
            return ceil(key, node.right);
        }
        return node;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public int height() {
        return height(root);
    }

    private int height(Node root) {
        return root == null ? 0 : root.height;
    }

    private Node rebalance(Node node) {
        if (balanceFactor(node) < -1) {
            if (balanceFactor(node.right) > 0) {
                node.right = rotateRight(node.right);
            }
            return rotateLeft(node);
        }
        if (balanceFactor(node) > 1) {
            if (balanceFactor(node.left) < 0) {
                node.left = rotateLeft(node.left);
            }
            return rotateRight(node);
        }
        return node;
    }

    private int balanceFactor(Node node) {
        return height(node.left) - height(node.right);
    }

    private Node rotateLeft(Node node) {
        Node rightChild = node.right;
        node.right = rightChild.left;
        rightChild.left = node;
        updateHeight(node);
        updateHeight(rightChild);
        return rightChild;
    }

    private Node rotateRight(Node node) {
        Node leftChild = node.left;
        node.left = leftChild.right;
        leftChild.right = node;
        updateHeight(node);
        updateHeight(leftChild);
        return leftChild;
    }

    private void updateHeight(Node node) {
        if (node == null) {
            return;
        }
        node.height = 1 + Math.max(height(node.left), height(node.right));
    }

}