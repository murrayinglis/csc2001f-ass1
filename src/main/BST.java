package main;

import javax.swing.*;

/**
 * Binary search tree class
 *
 * @author Murray Inglis
 */
public class BST
{
    /**
     * Node class
     * Contains an account object as data
     * Contains pointers to left and right children of the node
     *
     */
    private static class Node
    {
        private Account account;
        private Node left;
        private Node right;

        public Node(Account account) {this.account = account;}
    }

    /**
     * Root node instance variable
     *
     */
    private Node root;

    /**
     * Binary search tree constructor
     * Initializes the tree by setting root to null
     *
     */
    public BST() {this.root = null;}

    // Methods:

    /**
     * Insert an account into the tree
     *
     * @param account the account to insert as a <code>Account</code>
     */
    public void insert(Account account) {root = insert(root, account);}

    /**
     * insert helper method
     * 
     */
    private Node insert(Node node, Account account)
    {
        if (node == null) {
            node = new Node(account);
            return node;
        }

        if (account.compareTo(node.account)<0) {
            node.left = insert(node.left, account);
        } else if (account.compareTo(node.account)>0) {
            node.right = insert(node.right, account);
        }

        return node;
    }

    /**
     * Search the tree for an account
     *
     * @param account the account to search for as an <code>Account</code>
     * @return the account object in the tree as an <code>Account</code>
     */
    public Account search(Account account) {
        return search(root, account);
    }

    /**
     * search helper method
     */
    private Account search(Node node, Account account) {
        if (node == null) {
            return null;
        }

        if (account.compareTo(node.account)==0) {
            return node.account;
        } else if (account.compareTo(node.account)<0) {
            return search(node.left, account);
        } else {
            return search(node.right, account);
        }
    }

    /**
     * Traverses the tree and prints out each account
     * 
     */
    public void inorderTraversal() {
        inorderTraversal(root);
    }

    /**
     * inOrderTraversal helper method
     */
    private void inorderTraversal(Node node) {
        if (node != null) {
            inorderTraversal(node.left);
            System.out.println(node.account);
            inorderTraversal(node.right);
        }
    }

    /**
     * In order traversal for the GUI. Appends each account to the GUI
     *
     * @param textArea the object to append the <code>String</code> representation of each account
     */
    public void inorderTraversal(JTextArea textArea) {
        inorderTraversal(root, textArea);
    }

    /**
     * inOrderTraversal helper method
     */
    private void inorderTraversal(Node node, JTextArea textArea) {
        if (node != null) {
            inorderTraversal(node.left, textArea);
            textArea.append(node.account.toString());
            textArea.append("\n");
            System.out.println(node.account);
            inorderTraversal(node.right, textArea);
        }
    }

    /**
     * Delete an account from the tree
     * 
     * @param account the account to delete as an <code>Account</code>
     */
    public void delete(Account account) {
        root = delete(root, account);
    }

    /**
     * delete helper method
     */
    private Node delete(Node node, Account account) {
        if (node == null) {
            return null;
        }

        if (account.compareTo(node.account) < 0) {
            node.left = delete(node.left, account);
        } else if (account.compareTo(node.account) > 0) {
            node.right = delete(node.right, account);
        } else {
            if (node.left == null && node.right == null) {
                // Case 1: Node has no children, set to null
                node = null;
            } else if (node.left == null) {
                // Case 2: Node has one right child, replace with right child
                node = node.right;
            } else if (node.right == null) {
                // Case 2: Node has one left child, replace with left child
                node = node.left;
            } else {
                // Case 3: Node has two children, replace with minimum right child
                Node successor = findMin(node.right);
                node.account = successor.account;
                node.right = delete(node.right, successor.account);
            }
        }

        return node;
    }

    // Find minimum helper method for delete()
    private Node findMin(Node node) {
        while (node.left != null) {
            node = node.left;
        }

        return node;
    }
}