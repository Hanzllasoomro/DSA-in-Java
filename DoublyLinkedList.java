public class DoublyLinkedList<D> {
    Node head;
    Node tail;
    int length;

    class Node {
        D data;
        Node next;
        Node previous;

        Node(D data) {
            this.data = data;
        }
    }

    DoublyLinkedList() {
        head = null;
        tail = null;
        length = 0;
    }

    String isEmpty() {
        if (length == 0 || head == null)
            return "The List is Empty";
        return "The List isn't Empty";
    }

    int getLength() {
        return length;
    }
    D getHead(){
        return head.data;
    }
    D getTail(){
        return tail.data;
    }

    void add(D data, int position) {
        Node newNode = new Node(data);
        if (head == null)
            head = newNode;
        else if (position <= 1) {
            newNode.next = head;
            head = newNode;
        } else if (position >= length + 1) {
            Node temp = head;
            while (temp.next != null)
                temp = temp.next;
            temp.next = newNode;
        } else {
            Node temp = head;
            for (int i = 1; i < position - 1; i++)
                temp = temp.next;
            newNode.next = temp.next;
            temp.next = newNode;
        }
        length++;
    }

    void add(D data) {
        Node newNode = new Node(data);
        if (head == null)
            head = newNode;
        Node temp = head;
        while (temp.next != null)
            temp = temp.next;
        temp.next = newNode;
        length++;
    }

    void addFirst(D data) {
        Node newNode = new Node(data);
        if (head == null)
            head = newNode;
        newNode.next = head;
        head = newNode;
        length++;
    }

    void display() {
        Node temp = head;
        while (temp != null) {
            System.out.print("|" + temp.data + "|-->");
            temp = temp.next;
        }
        System.out.println("| null |");
    }

    public static void main(String[] args) {
        DoublyLinkedList list = new DoublyLinkedList();
        list.add(1, 1);
        list.add(2, 2);
        list.add(4, 4);
        list.add(3, 3);
        list.addFirst(0);
        System.out.println(list.getLength());
        list.display();
    }
}
