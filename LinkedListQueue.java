class LinkedListQueue {

    static class Node {
        Object data;
        Node next;

        public Node(Object data) {
            this.data = data;
            this.next = null;
        }
    }
    private Node front, rear;
    int size;

    public LinkedListQueue() {
        this.front = this.rear = null;
    }

    public void enqueue(Object data) {
        Node newNode = new Node(data);

        if (isEmpty()) {
            front = rear = newNode;
            size++;
            return;
        }

        rear.next = newNode;
        rear = newNode;
        size++;
    }

    public Node dequeue() {
        if (isEmpty()) {
            System.out.println("Queue is empty. Cannot dequeue.");
            return null;
        }

        front = front.next;
        size--;
        if (front == null) {
            rear = null;
            size--;
        }
        return null;
    }
    void deleteSecond(){
        if(isEmpty()) {
            System.out.println("Queue is empty!!");
            return;
        }
        front.next = front.next.next;
    }

    public boolean isEmpty() {
        return front == null;
    }
    int getSize(){
        return size;
    }
    Object pop(){
        if(isEmpty())
            throw  new IllegalStateException("Queue is empty!!!");
        Object oldFront = front.data;
        front =front.next;
        size--;
        return oldFront;
    }

    public Object peek() {
        if (isEmpty()) {
            throw new IllegalStateException("Queue is empty. Cannot peek.");
        }
        return front.data;
    }

    public void display() {
        Node current = front;
        while (current != null) {
            System.out.println(current.data + " ");
            current = current.next;
        }
        System.out.println();
    }

    void displayReverse(){
        LinkedListStack stack = new LinkedListStack();
        for(Node temp = front ; temp != null ; temp = temp.next)
            stack.push(temp.data);
        stack.display();
    }
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder("[");
        for (Node temp = front; temp != null; temp = temp.next)
            stringBuilder.append(temp.data + ", ");
        stringBuilder.append("null ]");
        return stringBuilder.toString();
    }
    Object[] toArray(){
        Object[] array = new Object[getSize()];
        Node temp = front;
        for(int i=0 ; i<getSize() ; i++) {
            array[i] = temp.data;
            temp = temp.next;
        }
        return array;
    }
    public static void divideQueue(LinkedListQueue originalQueue, LinkedListQueue firstHalf, LinkedListQueue secondHalf) {
        int size = originalQueue.getSize();

        for (int i = 0; i < size / 2; i++)
            firstHalf.enqueue(originalQueue.pop());

        while (!originalQueue.isEmpty())
            secondHalf.enqueue(originalQueue.pop());
    }
    Object findMiddle() {
        Node hare = front;
        Node turtle = front;
        while (hare.next != null && hare.next.next != null) {
            hare = hare.next.next;
            turtle = turtle.next;
        }
        return turtle.data;
    }
    void mergeQueue(LinkedListQueue... lists) {
        for (LinkedListQueue list : lists) {
            if (front == null)
                front = list.front;
            else {
                rear = front;
                while (rear.next != null)
                    rear = rear.next;
                rear.next = list.front;
            }
        }
    }

    public static void main(String[] args) {
        var name = new LinkedListQueue();
        name.enqueue("passenger1");
        name.enqueue("passenger2");
        name.enqueue("passenger3");
        name.enqueue("passenger4");

        var cast = new LinkedListQueue();
        cast.enqueue("Soomro");
        cast.enqueue("Brohi");
        cast.enqueue("Bhatti");
        cast.enqueue("Jamali");

        var collection = new LinkedListQueue();
        collection.mergeQueue(name , cast);
        collection.display();
    }
}
