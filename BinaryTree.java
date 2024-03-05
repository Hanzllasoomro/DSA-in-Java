import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class BinaryTree {
    static int index = -1;

    public static void main(String[] args) {
        int nodes[] = {5,1,3,4,2,7};
        Node root = null;
        for(int i =0 ; i < nodes.length ; i++)
            root = insert(root, nodes[i]);
        printRoot2Leaf(root, new ArrayList<>());
    }
    static void printRoot2Leaf(Node root , ArrayList<Object> path) {
        if (root == null)
            return;
        path.add(root.data);
        if (root.left == null && root.right == null)
            printPath(path);
        else {
            printRoot2Leaf(root.left, path);
            printRoot2Leaf(root.right, path);
        }
        path.remove(path.size() - 1);
    }
    static void printPath(ArrayList<Object> path){
        for(int i =0 ; i < path.size() ; i++)
            System.out.print(path.get(i)+"-->");
        System.out.println();

    }

    static Node buildTree(Object nodes[]) {
        index++;
        if ((int) nodes[index] == -1)
            return null;

        Node newNode = new Node(nodes[index]);
        newNode.left = buildTree(nodes);
        newNode.right = buildTree(nodes);

        return newNode;
    }
    static Node insert(Node root , int value){
        if(root  == null) {
            root = new Node(value);
            return root;
        }
        if((int)root.data > value)
            root.left = insert(root.left , value);
        else
            root.right = insert(root.right , value);
        return root;
    }
    static Node delete(Node root, int value){
        if((int)root.data > value)
            root.left = delete(root.left , value);
        else if((int)root.data < value)
            root.right = delete(root.right , value);
            else{
                if(root.left == null && root.right == null)
                    return null;
                if(root.left == null)
                    return root.right;
                else if(root.right == null)
                    return root.left;

                Node IS = inorderSuccessor(root.right);
                root.data = IS.data;
                root.right = delete(root.right ,(int) IS.data);
            }
            return root;
    }
    static void preorder(Node root) {
        if (root == null)
            return;
        System.out.println(root.data + " ");
        preorder(root.left);
        preorder(root.right);
    }
    static void inorder(Node root) {
        if (root == null)
            return;
        inorder(root.left);
        System.out.println(root.data + " ");
        inorder(root.right);
    }
    static void postorder(Node root) {
        if (root == null)
            return;
        postorder(root.left);
        postorder(root.right);
        System.out.println(root.data + " ");
    }
    static void levelorder(Node root){
        if(root == null)
            return;;
        Queue<Node> queue = new LinkedList<>();
        queue.add(root);
        queue.add(null);
        while(!queue.isEmpty()) {
            Node currentNode = queue.remove();
            if (currentNode == null) {
                System.out.println();
                if (queue.isEmpty())
                    break;
                else
                    queue.add(null);
            } else {
                System.out.print(currentNode.data + " ");
                if (currentNode.left != null)
                    queue.add(currentNode.left);
                if (currentNode.right != null)
                    queue.add(currentNode.right);
            }
        }
    }
    static boolean search(Node root, int key){
        if(root == null)
            return false;
        if((int)root.data == key)
            return true;
        if((int)root.data > key)
            return search( root.left, key);
        else
                return search(root.right , key);
    }
    static int countOfNodes(Node root){
        if(root == null)
            return 0;
        int leftNodes = countOfNodes(root.left);
        int rightNodes = countOfNodes(root.right);

        return leftNodes + rightNodes +1;
    }
    static int sumOfNodes(Node root){
        if(root == null)
            return 0;
        int leftsum = sumOfNodes(root.left);
        int rightsum = sumOfNodes(root.right);

        return leftsum + rightsum + (int)root.data;
    }
    static int height(Node root){
        if(root == null)
            return 0;
        int leftHeight = height(root.left);
        int rightHeight = height(root.right);

        int height = Math.max(leftHeight , rightHeight ) + 1;
        return height;
    }
    public boolean isLeaf(Node root){
        if(root.left == null && root.right == null)
            return true;
        return false;
    }
    static TreeInfo diameter2(Node root){
        if(root == null)
           return new TreeInfo( 0 ,0);
        TreeInfo left = diameter2(root.left);
        TreeInfo right = diameter2(root.right);

        int height = Math.max(left.height , right.height) + 1;

        int diameter1 = left.diameter;
        int diameter2 = right.diameter;
        int diameter3 = left.height + right.height + 1;

        int diameter = Math.max(Math.max(diameter1 , diameter2) , diameter3);
        return new TreeInfo(height, diameter);
    }
    static int diameter(Node root){
        if(root == null)
            return 0;
        int diameter1 = diameter(root.left);
        int diameter2 = diameter(root.right);
        int diameter3 = height(root.right) + height(root.left) +1;

        return Math.max(diameter3 , Math.max(diameter1 , diameter2));
    }
    static boolean isSubTree(Node root , Node subRoot){
        if(subRoot == null)
            return true;
        if(root == null)
            return false;
        if(root.data == subRoot.data)
            if(isIdentical(root , subRoot))
                return true;
        return isSubTree(root.left , subRoot) || isSubTree(root.right , subRoot);
    }
    static boolean isIdentical(Node root , Node subRoot){
        if(root == null && subRoot == null)
            return true;
        if(root == null || subRoot == null)
            return false;
        if(root.data == subRoot.data)
            return isIdentical(root.left , subRoot.left) && isIdentical(root.right , subRoot.right);
        return false;
    }
    static Node inorderSuccessor(Node root){
        while (root.left != null)
            root = root.left;
        return root;
    }
    static void printRange(Node root, int x , int y){
        if(root == null)
            return;
        if((int)root.data >= x && (int)root.data <= y){
            printRange(root.left , x, y);
            System.out.println(root.data + " ");
            printRange(root.right , x , y);
        }
        else if ((int)root.data >= y)
            printRange(root.left, x, y);
        else
            printRange(root.right , x, y);
    }
        static class Node {
        Object data;
        Node left;
        Node right;

        Node(Object data) {
            this.data = data;
            left = null;
            right = null;
        }
    }
    static class TreeInfo{
        int height;
        int diameter;
        TreeInfo(int height , int diameter){
            this.height = height;
            this.diameter = diameter;
        }
    }
}
