package company.vk.polis.ads.bst;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Objects;

/**
 * LLRB implementation of binary search tree.
 */
public class RedBlackBinarySearchTree<Key extends Comparable<Key>, Value>
        implements BinarySearchTree<Key, Value> {

    private static final boolean BLACK = false;
    private static final boolean RED = true;

    private class Node {
        Key key;
        Value value;
        Node left;
        Node right;
        boolean color;
        int height;

        public Node(Key key, Value value, int height, boolean color) {
            this.key = key;
            this.value = value;
            this.height = height;
            this.color = color;
        }
    }

    private Node rootNode;

    @Nullable
    @Override
    public Value get(@NotNull Key key) {
        return get(rootNode, key);
    }

    private Value get(Node node, Key key){
        if(node == null){
            return null;
        }

        if(key.compareTo(node.key) > 0){
            return get(node.right, key);
        }else if(key.compareTo(node.key) < 0){
            return get(node.left, key);
        }else{
            return node.value;
        }
    }

    @Override
    public void put(@NotNull Key key, @NotNull Value value) {
        rootNode = put(rootNode, key, value);
        rootNode.color = BLACK;
    }

    private Node put(Node node, Key key, Value value){
        if(node == null){
            return new Node(key, value, 1, RED);
        }

        if(key.compareTo(node.key) > 0){
            node.right = put(node.right, key, value);
        }else if(key.compareTo(node.key) < 0){
            node.left = put(node.left, key, value);
        }else{
            node.value = value;
        }
        node = fixUp(node);
        fixHeight(node);
        return node;
    }

    private void fixHeight(Node node){
        if(node.right == null && node.left != null){
            if(isRed(node.left)){
                node.height = node.left.height;
            }else{
                node.height = node.left.height + 1;
            }
        }
        else if(node.right != null && node.left == null){
            node.height = node.right.height + 1;
        }else if(node.right == null){
            node.height = 1;
        }else{
            node.height = Math.max(node.left.height, node.right.height) + 1;
        }
    }

    private boolean isRed(Node node){
        return node != null && node.color == RED;
    }

    private Node fixUp(Node node){
        if(isRed(node.right) && !isRed(node.left)){
            node = rotateLeft(node);
        }
        if(isRed(node.left) && isRed(node.left.left)){
            node = rotateRight(node);
        }
        if(isRed(node.right) && isRed(node.left)){
            flipColors(node);
        }
        return node;
    }

    private void flipColors(Node node){
        node.color = !node.color;
        if(node.right != null){
            node.right.color = !node.right.color;
        }
        if(node.left != null){
            node.left.color = !node.left.color;
        }
    }

    private Node rotateLeft(Node node){
        Node right = node.right;
        node.right = right.left;
        right.left = node;
        right.color = node.color;
        node.color = RED;
        return right;
    }

    private Node rotateRight(Node node){
        Node left = node.left;
        node.left = left.right;
        left.right = node;
        left.color = node.color;
        node.color = RED;
        return left;
    }

    @Nullable
    @Override
    public Value remove(@NotNull Key key) {
        if(rootNode == null){
            return null;
        }

        Value removedNode = get(key);
        rootNode = remove(rootNode, key);
        return removedNode;
    }

    private Node remove(Node node, Key key){
        var compareKey = key.compareTo(node.key);
        if(compareKey < 0){
            if(node.left != null){
                if(!isRed(node.left) && !isRed(node.left.left)){
                    node = moveRedLeft(node);
                }
                node.left = remove(node.left, key);
            }
        }else{
            if(isRed(node.left)){
                node = rotateRight(node);
            }

            if(node.right == null && compareKey == 0){
                return null;
            }

            if(node.right != null && !isRed(node.right) && !isRed(node.right.left)){
                node = moveRedRight(node);
            }

            if(key.compareTo(node.key) == 0){
                assert node.right != null;
                Node min = min(node.right);
                node.key = min.key;
                node.value = min.value;
                node.right = deleteMin(node.right);
            }else if(node.right != null){
                node.right = remove(node.right, key);
            }
        }
        return fixUp(node);
    }

    private Node moveRedLeft(Node node){
        flipColors(node);

        if(isRed(node.right.left)){
            node.right = rotateRight(node.right);
            node = rotateLeft(node);
            flipColors(node);
        }

        return node;
    }

    private Node moveRedRight(Node node){
        flipColors(node);

        if(isRed(node.left.left)){
            node = rotateRight(node);
            flipColors(node);
        }

        return node;
    }

    private Node deleteMin(Node node){
        if(node == null || node.left == null){
            return null;
        }

        if(!isRed(node.left) && !isRed(node.left.left)){
            node = moveRedLeft(node);
        }

        node.left = deleteMin(node.left);
        return fixUp(node);
    }

    @Nullable
    @Override
    public Key min() {
        if(rootNode == null){
            return null;
        }

        return min(rootNode).key;
    }

    private Node min(Node node){
        if(node.left == null){
            return node;
        }

        return min(node.left);
    }

    @Nullable
    @Override
    public Value minValue() {
        if(rootNode == null){
            return null;
        }

        return min(rootNode).value;
    }

    @Nullable
    @Override
    public Key max() {
        if(rootNode == null){
            return null;
        }

        return max(rootNode).key;
    }

    private Node max(Node node){
        if(node.right == null){
            return node;
        }

        return max(node.right);
    }

    @Nullable
    @Override
    public Value maxValue() {
        if(rootNode == null){
            return null;
        }

        return max(rootNode).value;
    }

    @Nullable
    @Override
    public Key floor(@NotNull Key key) {
        if(size() == 0){
            return null;
        }

        Node nodeFloor = floor(rootNode, key);
        if(nodeFloor == null){
            return null;
        }else{
            return nodeFloor.key;
        }
    }

    private Node floor(Node node, Key key){
        if(node == null){
            return null;
        }

        if(key.compareTo(node.key) < 0){
            return floor(node.left, key);
        }else if(key.compareTo(node.key) == 0){
            return node;
        }

        Node nodeFloor = floor(node.right, key);
        return Objects.requireNonNullElse(nodeFloor, node);
    }

    @Nullable
    @Override
    public Key ceil(@NotNull Key key) {
        if(size() == 0){
            return null;
        }

        Node nodeCeil = ceil(rootNode, key);
        if(nodeCeil == null){
            return null;
        }else{
            return nodeCeil.key;
        }
    }

    private Node ceil(Node node, Key key){
        if(node == null){
            return null;
        }

        if(key.compareTo(node.key) > 0){
            return ceil(node.right, key);
        }else if(key.compareTo(node.key) == 0){
            return node;
        }

        Node nodeCeil = ceil(node.left, key);
        if(nodeCeil != null){
            return nodeCeil;
        }else{
            return node;
        }
    }

    @Override
    public int size() {
        if(rootNode == null){
            return 0;
        }
        return size(rootNode);
    }

    private int size(Node node){
        if(node == null){
            return 0;
        }

        return size(node.left) + size(node.right) + 1;
    }

    /**
     * Только для тестов
     * Саму высоту хранить не обязательно, достаточно сделать рекурсивное вычисление
     */
    @Override
    public int height() {
        return height(rootNode);
    }

    private int height(Node node) {
        if(node == null){
            return 0;
        }

        return node.height;
    }
}
