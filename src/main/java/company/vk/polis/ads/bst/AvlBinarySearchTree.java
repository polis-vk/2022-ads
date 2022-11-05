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

    private Node root;
    private int count;

    @Override
    public Value get(@NotNull Key key) {
        return get(root, key);
    }

    private Value get(Node node, Key key) {
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
    public void put(@NotNull Key key, @NotNull Value value) {
        root = put(root, key, value);
    }

    private Node put(Node node, Key key, Value value) {
        if (node == null) {
            count++;
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
        if(node == null){
            return;
        }
        node.height = Math.max(height(node.left), height(node.right)) + 1;
    }

    private int height(Node node) {
        return node == null ? 0 : node.height;
    }

    private int factor(Node node) {
        return height(node.left) - height(node.right);
    }

    private Node balance(Node node) {
        if(node == null){
            return null;
        }
        if(factor(node) == 2){
            if(factor(node.left) < 0){
                node.left = rotateLeft(node.left);
            }
            return rotateRight(node);
        }
        if(factor(node) == -2){
            if(factor(node.right) > 0){
                node.right = rotateRight(node.right);
            }
            return rotateLeft(node);
        }
        return node;
    }

    private Node rotateLeft(Node node) {
        Node right = node.right;
        node.right = right.left;
        right.left = node;
        fixHeight(node);
        fixHeight(right);
        return right;
    }

    private Node rotateRight(Node node) {
        Node left = node.left;
        node.left = left.right;
        left.right = node;
        fixHeight(node);
        fixHeight(left);
        return left;
    }

    @Override
    public Value remove(@NotNull Key key) {
        Value res = get(root, key);
        if(res == null){
            return null;
        }
        root = remove(root, key);
        return res;
    }

    private Node remove(Node node, Key key) {
        if (node == null) {
            return null;
        }
        if(key.compareTo(node.key) < 0){
            node.left = remove(node.left, key);
        }
        if(key.compareTo(node.key) > 0){
            node.right = remove(node.right, key);
        }
        if(key.compareTo(node.key) == 0){
            count--;
            node = innerRemoving(node);
        }
        fixHeight(node);
        node = balance(node);
        return node;
    }

    private Node innerRemoving(Node node){
        if(node.right == null){
            return node.left;
        }
        if(node.left == null){
            return node.right;
        }
        Node tmp = node;
        node = min(tmp.right);
        node.right = deleteMin(tmp.right);
        node.left = tmp.left;
        fixHeight(node);
        node = balance(node);
        return node;
    }

    private Node min(Node node) {
        if (node == null) {
            return null;
        }
        if (node.left == null) {
            return node;
        }
        return min(node.left);
    }

    private Node deleteMin(Node x) {
        if (x.left == null) {
            return x.right;
        }
        x.left = deleteMin(x.left);
        fixHeight(x);
        x = balance(x);
        return x;
    }

    @Override
    public Key min() {
        Node minNode = min(root);
        return minNode == null ? null : minNode.key;
    }

    @Override
    public Value minValue() {
        Node minNode = min(root);
        return minNode == null ? null : minNode.value;
    }

    @Override
    public Key max() {
        Node maxNode = max(root);
        return maxNode == null ? null : maxNode.key;
    }

    private Node max(Node node) {
        if (node == null) {
            return null;
        }
        if (node.right == null) {
            return node;
        }
        return max(node.right);
    }

    @Override
    public Value maxValue() {
        Node maxNode = max(root);
        return maxNode == null ? null : maxNode.value;
    }

    @Override
    public Key floor(@NotNull Key key) {
        if(root == null){
            return null;
        }
        return floor(root, key);
    }

    private Key floor(Node node, Key key) {
        if(node == null){
            return null;
        }
        if(key.compareTo(node.key) == 0){
            return node.key;
        }
        if(key.compareTo(node.key) < 0){
            return floor(node.left, key);
        }
        Key floorRes = floor(node.right, key);
        if(floorRes == null){
            return node.key;
        }else{
            return floorRes;
        }
    }

    @Override
    public Key ceil(@NotNull Key key) {
        if(root == null){
            return null;
        }
        return ceil(root, key);
    }

    private Key ceil(Node node, Key key) {
        if (node == null) {
            return null;
        }
        if(key.compareTo(node.key) == 0){
            return node.key;
        }
        if (key.compareTo(node.key) > 0) {
            return ceil(node.right, key);
        }
        Key ceilRes = ceil(node.left, key);
        if(ceilRes == null){
            return node.key;
        }else{
            return ceilRes;
        }
    }

    @Override
    public int size() {
        return count;
    }

    @Override
    public int height() {
     return height(root);
    }
}
