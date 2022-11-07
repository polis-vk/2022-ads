package company.vk.polis.ads.bst;

import org.jetbrains.annotations.NotNull;

/**
 * AVL implementation of binary search tree.
 */
public class AvlBinarySearchTree<Key extends Comparable<Key>, Value> implements BinarySearchTree<Key, Value> {
    private Node root;
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
        return get(root, key);
    }

    @Override
    public void put(@NotNull Key key, @NotNull Value value) {
        root = put(root, key, value);
    }

    @Override
    public Value remove(@NotNull Key key) {
        Value value = get(root, key);

        if (value != null) {
            root = remove(root, key);
            size--;
        }

        return value;
    }

    @Override
    public Key min() {
        Node answer = getMin(root);
        return (answer == null) ? null : answer.key;
    }

    @Override
    public Value minValue() {
        Node answer = getMin(root);
        return (answer == null) ? null : answer.value;
    }

    @Override
    public Key max() {
        Node answer = getMax(root);
        return (answer == null) ? null : answer.key;
    }

    @Override
    public Value maxValue() {
        Node answer = getMax(root);
        return (answer == null) ? null : answer.value;
    }

    @Override
    public Key floor(@NotNull Key key) {
        if (root == null) {
            return null;
        }

        Node currentNode = root;

        while (currentNode != null) {
            if (key.compareTo(currentNode.key) < 0) {
                currentNode = currentNode.left;
            } else if (key.compareTo(currentNode.key) > 0) {
                if (currentNode.right == null || key.compareTo(currentNode.right.key) < 0) {
                    break;
                }
                currentNode = currentNode.right;
            } else {
                return currentNode.key;
            }
        }

        return (currentNode == null) ? null : currentNode.key;
    }

    // Key ceil(Key key) - вовзращает минимальный ключ, больший либо равный заданному, или null, если такого нет
    @Override
    public Key ceil(@NotNull Key key) {
        if (key == null) {
            return null;
        }

        Node currentNode = root;

        while (currentNode != null) {
            if (key.compareTo(currentNode.key) < 0) {
                if (currentNode.left == null || key.compareTo(currentNode.left.key) > 0) {
                   break;
                }
                currentNode = currentNode.left;
            } else if (key.compareTo(currentNode.key) > 0) {
                currentNode = currentNode.right;
            } else {
                return currentNode.key;
            }
        }

        return (currentNode == null) ? null : currentNode.key;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public int height() {
        return height(root);
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

    private Node getMin(Node node) {
        if (root == null) {
            return null;
        }

        if (node.left != null) {
            return getMin(node.left);
        }

        return node;
    }

    private Node getMax(Node node) {
        if (root == null) {
            return null;
        }

        if (node.right != null) {
            return getMax(node.right);
        }

        return node;
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
            node = innerDelete(node);
            fixHeight(node);
            node = balance(node);
        }

        return node;
    }

    private Node innerDelete(Node node) {
        if (node.left == null) {
            return node.right;
        }

        if (node.right == null) {
            return node.left;
        }

        Node temp = node;
        node = getMin(temp.right);
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

    private int height(Node node) {
        return node == null ? 0 : node.height;
    }

    private void fixHeight(Node node) {
        if (node == null) {
            return;
        }

        node.height = 1 + Math.max(height(node.left), height(node.right));
    }

    private Node rotateRight(Node node) {
        Node temp = node.left;
        node.left = temp.right;
        temp.right = node;
        fixHeight(node);
        fixHeight(temp);
        return temp;
    }

    private Node rotateLeft(Node node) {
        Node temp = node.right;
        node.right = temp.left;
        temp.left = node;
        fixHeight(node);
        fixHeight(temp);
        return temp;
    }

    private int factor(Node node) {
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
        }

        if (factor(node) == -2) {
            if (factor(node.right) > 0) {
                node.right = rotateRight(node.right);
            }
            return rotateLeft(node);
        }

        return node;
    }
}
