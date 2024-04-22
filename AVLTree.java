public class AVLTree {
    class Node {
        int key;
        int data;
        Node left;
        Node right;
        int height;

        Node(int key, int data) {
            this.key = key;
            this.data = data;
            this.left = null;
            this.right = null;
            this.height = 1;
        }
    }

    private Node root;

    public AVLTree() {
        root = null;
    }


    private int height(Node node) {
        if (node == null)
            return 0;
        return node != null ? node.height : -1;
        // return node.height;
    }

    public boolean isAVL(Node node) {
        if (node == null)
            return true;
        int left = height(node.left);
        int right = height(node.right);
        if (Math.abs(left - right) > 1)
            return false;
        return isAVL(node.left) && isAVL(node.right);
    }
    public boolean isAVL() {
        return isAVL(root);
    }

    private void updateHeight(Node node) {
        int leftChildHeight = height(node.left);
        int rightChildHeight = height(node.right);
        node.height = Math.max(leftChildHeight, rightChildHeight) + 1;
    }

    private int balanceFactor(Node node) {
        if(node == null) return 0;
        return height(node.left) - height(node.right);
    }

    private Node leftRotate(Node node) {
        Node newRoot = node.left;
        node.left = newRoot.right;
        newRoot.right = node;
        updateHeight(node);
        updateHeight(newRoot);
        return newRoot;
    }

    private Node rightRotate(Node node) {
        Node newRoot = node.right;
        node.right = newRoot.left;
        newRoot.left = node;
        updateHeight(node);
        updateHeight(newRoot);
        return newRoot;
    }

    private Node rebalance(Node node) {
        int balanceFactor = balanceFactor(node);
        if (balanceFactor < -1)
            if (balanceFactor(node.left) <= 0) {
                node = rightRotate(node);
            } else {
                node.left = leftRotate(node.left);
                node = rightRotate(node);
            }
        if (balanceFactor > 1)
            if (balanceFactor(node.right) >= 0) {
                node = leftRotate(node);
            } else {
                node.right = rightRotate(node.right);
                node = leftRotate(node);
            }
        return node;
    }

    public void insert(int key, int value) {
        root = insertNode(root, key, value);
    }

    private Node insertNode(Node node, int key, int value) {
        if (node == null)
            return new Node(key, value);
        if(key < node.key)
            node.left = insertNode(node.left, key, value);
        else if (key > node.key)
            node.right = insertNode(node.right, key, value);
        else
            node.data = value;
        updateHeight(node);
        return rebalance(node);
    }

    private Node minimumNode(Node node) {
        Node minimumCurrentNode = node;
        while (minimumCurrentNode.left != null)
            minimumCurrentNode = minimumCurrentNode.left;
        return minimumCurrentNode;
    }
    public void delete(int key) {
        root = deleteNode(root, key);
    }
    private Node deleteNode(Node node, int key) {
        if(node == null)    return  null;
        if(key < node.key)
            node.left = deleteNode(node.left, key);
        else if(key > node.key)
            node.right = deleteNode(node.right, key);
        else {
            if((node.left == null) || (node.right == null)){
                Node temp = null;
                if(temp == node.left)
                    temp = node.right;
                else
                    temp = node.left;
                if(temp == null){
                    temp = node;
                    node = null;
                }
                else
                    node = temp;
            }
            else {
                Node temp = minimumNode(node.right);
                node.key = temp.key;
                node.data = temp.data;
                node.right = deleteNode(node.right, temp.key);
            }
        }
        return rebalance(node);
    }

    public static void main(String[] args) {
        AVLTree tree = new AVLTree();
        tree.insert(1 , 10);
        tree.insert(2 , 20);
        tree.insert(3 , 30);

        System.out.println("Output 1:" + (tree.isAVL(tree.root) ? "true" : "false"));

        AVLTree tree2 = new AVLTree();
        tree2.insert(1 , 10);
        tree2.insert(2 , 20);
        tree2.insert(3 , 30);

        System.out.println("output 2:" + (tree2.isAVL() ? "true" : "false"));
    }

}
