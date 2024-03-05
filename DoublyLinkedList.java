public class DoublyLinkedList<D> {
    Node head;
    Node previous;
    Node next;
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
        previous = null;
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

    D getHead() {
        return head.data;
    }
    D getNext(){
        return next.data;
    }

    D getPrevious() {
        return previous.data;
    }

    void add(D data, int position) {
        var newNode = new Node(data);
        if (head == null)
            head = newNode;
        else if (position <= 1) {
            newNode.next = head;
            head.previous = newNode;
            head = newNode;
        } else if (position >= length + 1) {
            var temp = head;
            while (temp.next != null)
                temp = temp.next;
            temp.next = newNode;
            newNode.previous = temp;
            previous = newNode;
        } else {
            var temp = head;
            for (int i = 1; i < position - 1; i++)
                temp = temp.next;
            newNode.next = temp.next;
            temp.next.previous = newNode;
            temp.next = newNode;
            newNode.previous = temp;
        }
        length++;
    }

    void add(D data) {
        var newNode = new Node(data);
        if (head == null)
            head = newNode;
        else {
            var temp = head;
            while (temp.next != null)
                temp = temp.next;
            temp.next = newNode;
            newNode.previous = temp;
            previous = newNode;
            length++;
        }
    }

    void addFirst(D data) {
        var newNode = new Node(data);
        if (head == null)
            head = newNode;
        newNode.next = head;
        head.previous = newNode;
        head = newNode;
        length++;
    }

    void addLast(D data) {
        var newNode = new Node(data);
        if (head == null) {
            head = newNode;
            return;
        }
        var temp = head;
        while (temp.next != null)
            temp = temp.next;
        temp.next = newNode;
        newNode.previous = temp;
        previous = newNode;
        length++;
    }

    Node delete(D data){
        if (head == null)
            return head;
        if (head.data == data) {
            head = head.next;
            head.previous = null;
            return head;
        }
        for(Node temp = head ; temp.next != null ; temp = temp.next)
            if(temp.next.data == data) {
                temp.next = temp.next.next;
                temp.next.previous = temp;
                break;
            }
        length--;
        return head;
    }
    Node deleteFirst() {
        if (head == null)
            return null;
        head = head.next;
        head.previous = null;
        length--;
        return head;
    }
    Node deleteLast(){
        if (head == null)
            return null;
        if(head.next == null) {
            head = null;
            return head;
        }
        Node secondlast = head;
        Node lastNode = head.next;
        while(lastNode.next != null) {
            lastNode = lastNode.next;
            secondlast = secondlast.next;
        }
        secondlast.next = null;
        previous = lastNode.previous;
        length--;
        return head;
    }
    void displayForward() {
        for (Node temp = head; temp != null; temp = temp.next)
            System.out.print("|" + temp.data + "|-->");
        System.out.println("| null |");
    }

    void displayBackward() {
        for (Node temp = previous; temp != null; temp = temp.previous)
            System.out.print("|" + temp.data + "|-->");
        System.out.println("| null |");
    }

    int getPosition(D data) {
        int positon = 1;
        Node temp = head;
        if (head.data == data)
            return positon;
        for (; temp.next != null; temp = temp.next) {
            if (temp.data == data)
                return positon;
            positon++;
        }
        if (temp.data != data) {
            System.out.println("DATA NOT FOUND");
            positon = -1;
        }
        return positon;
    }
    void replace(D data , int position){
        int count = 1;
        for(Node temp = head ; temp.next != null; temp = temp.next) {
            if (count == position) {
                temp.data = data;
                break;
            }
            count++;
        }
    }

    public static void main(String[] args) {
        var list = new DoublyLinkedList();
        list.add(0, 1);
        list.add(1.5, 2);
        list.add("Zain", 3);
        list.add(4, 4);
        list.add(5);
        list.add(6, 1);
        list.displayForward();
        list.replace("hanzlla",3);
        list.displayForward();
        list.displayBackward();

    }
}
