package com.company.dsa;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class BinarySearchTreeImpl<E extends Comparable<E>> implements BinarySearchTree<E> {
    private E value;
    private BinarySearchTreeImpl<E> left;
    private BinarySearchTreeImpl<E> right;


    public BinarySearchTreeImpl(E value) {
        this.value = value;
        left = null;
        right = null;
    }

    @Override
    public E getRootValue() {
        return value;
    }

    @Override
    public BinarySearchTree<E> getLeftTree() {
        return left;
    }

    @Override
    public BinarySearchTree<E> getRightTree() {
        return right;
    }

    @Override
    public void insert(E value) {
        if (this.value.compareTo(value) > 0) {
            if (left == null) {
                left = new BinarySearchTreeImpl<>(value);
            } else {
                left.insert(value);
            }
        } else {
            if (right == null) {
                right = new BinarySearchTreeImpl<>(value);
            } else {
                right.insert(value);
            }
        }

    }

    @Override
    public boolean search(E value) {

        if (value.equals(this.value)) {
            return true;
        }

        if (value.compareTo(this.value) < 0 && left != null) {
            return left.search(value);
        } else if (value.compareTo(this.value) > 0 && right != null) {
            return right.search(value);
        }

        return false;
    }

    @Override
    public List<E> inOrder() {
        List<E> inOrderList = new ArrayList<>();

        inOrderDFS(this, inOrderList);

        return inOrderList;
    }

    private void inOrderDFS(BinarySearchTreeImpl<E> tree, List<E> inOrderList) {
        if (tree == null) {
            return;
        }
        inOrderDFS(tree.left, inOrderList);
        inOrderList.add(tree.value);
        inOrderDFS(tree.right, inOrderList);
    }

    @Override
    public List<E> postOrder() {
        List<E> postOrderList = new ArrayList<>();

        postOrderDFS(this, postOrderList);

        return postOrderList;
    }

    private void postOrderDFS(BinarySearchTreeImpl<E> tree, List<E> postOrderList) {
        if (tree == null) {
            return;
        }
        postOrderDFS(tree.left, postOrderList);
        postOrderDFS(tree.right, postOrderList);
        postOrderList.add(tree.value);
    }

    @Override
    public List<E> preOrder() {
        List<E> preOrderList = new ArrayList<>();

        preOrderDFS(this, preOrderList);

        return preOrderList;
    }

    private void preOrderDFS(BinarySearchTreeImpl<E> tree, List<E> preOrderList) {
        if (tree == null) {
            return;
        }

        preOrderList.add(tree.value);
        preOrderDFS(tree.left, preOrderList);
        preOrderDFS(tree.right, preOrderList);
    }

    @Override
    public List<E> bfs() {
        List<E> result = new ArrayList<>();
        Queue<BinarySearchTreeImpl<E>> queue = new LinkedList<>();
        queue.add(this);

        while (!queue.isEmpty()) {
            BinarySearchTreeImpl<E> next = queue.poll();
            result.add(next.value);

            if (next.left != null) {
                queue.add(next.left);
            }

            if (next.right != null) {
                queue.add(next.right);
            }
        }

        return result;
    }

    @Override
    public int height() {
        int leftCounter = 0;
        int rightCounter = 0;

        if (left != null) {
            leftCounter = 1 + left.height();
        }

        if (right != null) {
            rightCounter = 1 + right.height();
        }

        return Math.max(leftCounter, rightCounter);
    }

    // Advanced task: implement remove method. To test, uncomment the commented tests in BinaryTreeImplTests
    @Override
    public boolean remove(E value) {
        return deleteRecursively(this, value) != null;
    }

    private BinarySearchTreeImpl<E> deleteRecursively(BinarySearchTreeImpl<E> root, E value) {

        if (root == null)
            return null;

        if (value.compareTo(root.value) < 0) {
            root.left = deleteRecursively(root.left, value);
        } else if (value.compareTo(root.value) > 0) {
            root.right = deleteRecursively(root.right, value);
        } else {

            if (root.left == null) {
                return root.right;
            } else if (root.right == null)
                return root.left;

            root.value = inOrderSuccessor(root.right);
            root.right = deleteRecursively(root.right, root.value);
        }

        return root;

    }

    public E inOrderSuccessor(BinarySearchTreeImpl<E> root) {
        E minimum = root.value;
        while (root.left != null) {
            minimum = root.left.value;
            root = root.left;
        }
        return minimum;
    }
}
