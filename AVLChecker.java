class TreeNode {
    int value;
    TreeNode left;
    TreeNode right;

    TreeNode(int value) {
        this.value = value;
        this.left = null;
        this.right = null;
    }
}

public class AVLChecker {

    // Function to calculate the height of a tree
    private static int height(TreeNode node) {
        if (node == null)
            return 0;
        return 1 + Math.max(height(node.left), height(node.right));
    }

    // Function to check if the tree is AVL
    public static boolean isAVL(TreeNode root) {
        if (root == null)
            return true;

        // Check if the height difference of the left and right subtrees is not more than 1
        if (Math.abs(height(root.left) - height(root.right)) <= 1) {
            // Recursively check if both left and right subtrees are AVL
            return isAVL(root.left) && isAVL(root.right);
        }

        return false;
    }

    // Function to perform left rotation
    private static TreeNode leftRotate(TreeNode root) {
        TreeNode newRoot = root.right;
        root.right = newRoot.left;
        newRoot.left = root;
        return newRoot;
    }

    // Function to perform right rotation
    private static TreeNode rightRotate(TreeNode root) {
        TreeNode newRoot = root.left;
        root.left = newRoot.right;
        newRoot.right = root;
        return newRoot;
    }

    // Function to balance the tree if it's not AVL
    public static TreeNode balanceTree(TreeNode root) {
        if (root == null)
            return null;

        int balanceFactor = height(root.left) - height(root.right);

        // Left heavy
        if (balanceFactor > 1) {
            // Left-Right case
            if (height(root.left.right) > height(root.left.left)) {
                root.left = leftRotate(root.left);
            }
            // Left-Left case
            root = rightRotate(root);
        }
        // Right heavy
        else if (balanceFactor < -1) {
            // Right-Left case
            if (height(root.right.left) > height(root.right.right)) {
                root.right = rightRotate(root.right);
            }
            // Right-Right case
            root = leftRotate(root);
        }
        return root;
    }

    public static void main(String[] args) {
        // Example input 1
        TreeNode root1 = new TreeNode(1);
        root1.left = new TreeNode(2);
        root1.right = new TreeNode(3);

        if (isAVL(root1)) {
            System.out.println("Output 1: 1 (The binary tree is an AVL tree)");
        } else {
            System.out.println("Output 1: 0 (The binary tree is not an AVL tree)");
            System.out.println("Balancing the tree...");
            root1 = balanceTree(root1);
            System.out.println("Balanced tree is now AVL: " + isAVL(root1));
        }

        // Example input 2
        TreeNode root2 = new TreeNode(1);
        root2.left = new TreeNode(2);
        root2.left.left = new TreeNode(3);

        if (isAVL(root2)) {
            System.out.println("Output 2: 1 (The binary tree is an AVL tree)");
        } else {
            System.out.println("Output 2: 0 (The binary tree is not an AVL tree)");
            System.out.println("Balancing the tree...");
            root2 = balanceTree(root2);
            System.out.println("Balanced tree is now AVL: " + isAVL(root2));
        }
    }
}

