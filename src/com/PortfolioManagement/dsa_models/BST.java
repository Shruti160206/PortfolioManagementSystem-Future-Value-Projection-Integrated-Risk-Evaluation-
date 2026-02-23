// BST: Helps to sort the assets in ascending order based on the corresponding risk factors.

package com.PortfolioManagement.dsa_models;
import com.PortfolioManagement.financialEntities.Asset;

public class BST {

    private Node root;

    private class Node {
        Asset asset;
        Node left, right;

        Node(Asset asset) {
            this.asset = asset;
        }
    }

    public void clear() {
        root = null;
    }

    public void insert(Asset asset) {
        root = insertRec(root, asset);
    }

    private Node insertRec(Node root, Asset asset) {

        if (root == null)
            return new Node(asset);

        if (asset.calculateRisk() <
                root.asset.calculateRisk())
            root.left = insertRec(root.left, asset);
        else
            root.right = insertRec(root.right, asset);

        return root;
    }

    public void inOrder() {
        inOrderRec(root);
    }

    private void inOrderRec(Node root) {

        if (root != null) {

            inOrderRec(root.left);

            System.out.println(root.asset.getName()
                    + " Risk: "
                    + root.asset.calculateRisk());

            inOrderRec(root.right);
        }
    }
}