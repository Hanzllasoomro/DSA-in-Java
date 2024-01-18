import java.io.FileReader;
import java.io.IOException;
import java.util.Random;

public class LinkedList<T> {
    Node head;
    Node last;
    int size = 0;

    LinkedList() {
        this.size = 0;
    }

    class Node {
        T data;
        Node nextNode;

        Node(T data) {
            this.data = data;
            nextNode = null;
            size++;
        }

        Node(T data, Node nextNode) {
            this.data = data;
            this.nextNode = nextNode;
            size++;
        }

    }

    int getSize() {
        Node temp = head;
        int count = 0;
        while (temp != null) {
            count++;
            temp = temp.nextNode;
        }
        return count;
    }

    boolean isEmpty() {
        return (size == 0 && head == null);
    }

    T getLast() {
        return last.data;
    }

    T getHead() {
        return head.data;
    }

    void add(T data, int pos) {
        var newNode = new Node(data);
        if (isEmpty())
            head = newNode;
        else if (pos <= 1) {
            newNode.nextNode = head;
            head = newNode;
            last = head.nextNode;
        } else if (pos >= size + 1) {
            Node tempNode = head;
            while (tempNode.nextNode != null)
                tempNode = tempNode.nextNode;
            tempNode.nextNode = newNode;
            last = newNode;
        } else {
            Node tempNode = head;
            for (int i = 1; i < pos - 1; i++)
                tempNode = tempNode.nextNode;
            newNode.nextNode = tempNode.nextNode;
            tempNode.nextNode = newNode;
            last = newNode;
        }
        size++;
    }

    void add(T value) {
        Node newNode = new Node(value);
        if (head == null)
            head = newNode;
        else {
            last = head;
            while (last.nextNode != null)
                last = last.nextNode;
            last.nextNode = newNode;
            last = newNode;
        }
    }

    void addFirst(T data) {
        var newNode = new Node(data);
        if (head == null) {
            head = newNode;
            return;
        }
        newNode.nextNode = head;
        head = newNode;
    }

    void addLast(T data) {
        var newNode = new Node(data);
        if (head == null) {
            head = newNode;
            return;
        }
        Node current = head;
        while (current.nextNode != null)
            current = current.nextNode;
        current.nextNode = newNode;
    }

    void display() {
        for (Node temp = head; temp != null; temp = temp.nextNode)
            System.out.print("|" + temp.data + "|-->");
        System.out.println("| null |");
    }

    Node delete(T data) {
        if (head == null)
            return head;
        if (head.data == data)
            return head.nextNode;
        for (Node p = head; p.nextNode != null; p = p.nextNode) {
            if (p.nextNode.data == data) {
                p.nextNode = p.nextNode.nextNode;
                break;
            }
        }
        return head;
    }

    void replace(T x, int position) {
        Node newNode = new Node(x);
        int count = 1;
        Node p = head;
        while (p.nextNode != null) {
            if (count == position) {
                newNode.nextNode = p.nextNode;
                p.data = newNode.data;
                break;
            }
            count++;
            p = p.nextNode;
        }
    }

    void deleteFirst() {
        if (head == null) {
            System.out.println("The list is empty!!!");
            return;
        }
        size--;
        head = head.nextNode;
    }

    void deleteLast() {
        if (head == null) {
            System.out.println("The list is empty!!!");
            return;
        }
        size--;
        if (head.nextNode == null) {
            head = null;
            return;
        }
        Node secondLast = head;
        Node lastNode = head.nextNode;
        while (lastNode.nextNode != null) {
            lastNode = lastNode.nextNode;
            secondLast = secondLast.nextNode;
        }
        secondLast.nextNode = null;
    }

    public void reverseIterate() {
        Node previous = head;
        Node current = head.nextNode;
        while (current != null) {
            if (head == null || head.nextNode == null)
                return;
            Node next = current.nextNode;
            current.nextNode = previous;
            previous = current;
            current = next;
        }
        head.nextNode = null;
        head = previous;
    }

    Node reverseRecursive(Node head) {
        if (head == null || head.nextNode == null)
            return head;
        Node newHead = reverseRecursive(head.nextNode);
        head.nextNode.nextNode = head;
        head.nextNode = null;
        return newHead;
    }

    Node removeFromEnd(int n) {
        if (head.nextNode == null)
            return null;
        int size = getSize();
        if (n == size) {
            head = head.nextNode;
            return head;
        }
        int index2Search = size - n;
        Node current = head;
        for (int i = 1; i < index2Search; i++)
            current = current.nextNode;
        current.nextNode = current.nextNode.nextNode;
        return head;
    }

    boolean isPalindrome() {
        if (head == null || head.nextNode == null)
            return true;
        Node middle = findMiddle(head);
        Node secondHalfStart = reverseRecursive(middle.nextNode);

        Node firstHalfStart = head;
        while (secondHalfStart != null) {
            if (firstHalfStart.data != secondHalfStart.data)
                return false;
            firstHalfStart = firstHalfStart.nextNode;
            secondHalfStart = secondHalfStart.nextNode;
        }
        return true;
    }

    Node findMiddle(Node head) {
        Node hare = head;
        Node turtle = head;
        while (hare.nextNode != null && hare.nextNode.nextNode != null) {
            hare = hare.nextNode.nextNode;
            turtle = turtle.nextNode;
        }
        return turtle;
    }

    boolean hasCycle(Node head) {
        if (head == null)
            return false;
        Node hare = head;
        Node turtle = head;

        while (hare.nextNode != null && hare != null) {
            hare = hare.nextNode;
            turtle = turtle.nextNode;
            if (hare == turtle)
                return false;
        }
        return true;
    }

    T[] toArray() {
        int size = getSize();
        Node p = head;
        T[] array = (T[]) new Object[size];
        for (int i = 0; i < size; i++) {
            array[i] = p.data;
            p = p.nextNode;
        }
        return array;
    }

    void swapNodes(Node node1, Node node2) {
        T temp = node1.data;
        node1.data = node2.data;
        node2.data = temp;
    }

    void shuffle() {
        if (head == null || head.nextNode == null)
            return;
        Random random = new Random();
        int size1 = getSize();
        for (int i = size - 1; i > 0; i--) {
            int j = random.nextInt(i + 1);
            Node node1 = getNodeAtIndex(i);
            Node node2 = getNodeAtIndex(j);
            swapNodes(node1, node2);
        }
    }

    Node getNodeAtIndex(int index) {
        Node current = head;
        for (int i = 0; i < index; i++)
            current = current.nextNode;
        return current;
    }

    /*void toLinkedList(int[] array) {
        for (int i = 0; i < array.length; i++)
            last.nextNode = new Node( array[i]);
    }*/
    void mergeLinkedList(LinkedList... lists) {
        for (LinkedList list : lists) {
            if (head == null)
                head = list.head;
            else {
                last = head;
                while (last.nextNode != null)
                    last = last.nextNode;
                last.nextNode = list.head;
            }
        }
    }

    Node duplicate(Node p) {
        Node q = new Node(p.data);
        Node temp = q;
        p = p.nextNode;
        while (p != null) {
            temp.nextNode = new Node(p.data);
            p = p.nextNode;
            temp = temp.nextNode;
        }
        return q;
    }

    char[] readFromFile(String fileName) throws IOException {
        FileReader fileReader = new FileReader(fileName);
        char[] array = null;
        int size = 0;
        int i = 0;
        int ch = fileReader.read();

        while (ch != -1)
            size++;
        array = new char[size];
        while (ch != -1) {
            array[i] = (char) ch;
            i++;
            ch = fileReader.read();
        }
        fileReader.close();
        return array;
    }

    String search(T searchKey) {
        Node current = head;
        while (current != null) {
            if (current == searchKey)
                return "Yes, This element is present in this list.";
            current = current.nextNode;
        }
        return "No, this element isn't present in this link.";
    }

    /* Node add(LinkedList l1 , LinkedList l2){
         Node dummy = new Node(0);
         Node tail = dummy;
         Node a = l1.head;
         Node b = l2.head;
         int carry = 0;
         while(a != null || b!= null){
         int x = ( a != null) ? a.val : 0;
         int y = ( b != null) ? b.val : 0;
         }
     }*/
    public static void main(String[] args) {
        LinkedList list = new LinkedList();
        list.add(5);
        list.add("Umar");
        list.add(8, 3);
        System.out.println(list.getLast());
    }
}